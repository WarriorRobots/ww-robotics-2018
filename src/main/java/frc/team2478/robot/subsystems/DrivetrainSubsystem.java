package frc.team2478.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2478.robot.commands.JoystickTeleop;
import frc.team2478.robot.interfaces.DrivetrainInterface;

/**
 * Instantiates drivetrain motors and provides methods for running WPILib drive functions.
 */
public class DrivetrainSubsystem extends Subsystem implements DrivetrainInterface {

	public static final int LEFT_FRONT = 4;
	public static final int LEFT_MIDDLE = 5;
	public static final int LEFT_BACK = 6;
	public static final int RIGHT_FRONT = 1;
	public static final int RIGHT_MIDDLE = 2;
	public static final int RIGHT_BACK = 3;
	
	public static final int LEFT_ENCODER_PORTA = 2;
	public static final int LEFT_ENCODER_PORTB = 3;
	public static final int RIGHT_ENCODER_PORTA = 0;
	public static final int RIGHT_ENCODER_PORTB = 1;
	
	public static final double RAMPRATE_SECONDS = 0.15;
	public static final int TIMEOUT_MS = 10;
	
	private Encoder leftEnc, rightEnc;
	private AHRS navx;
	
	private WPI_TalonSRX leftFront, leftMiddle, leftBack, rightFront, rightMiddle, rightBack;
	private SpeedControllerGroup leftGroup, rightGroup;
	private DifferentialDrive differentialDrive;
	
	public DrivetrainSubsystem() {
		leftFront = new WPI_TalonSRX(LEFT_FRONT);
		leftMiddle = new WPI_TalonSRX(LEFT_MIDDLE);
		leftBack = new WPI_TalonSRX(LEFT_BACK);
		leftFront.configOpenloopRamp(RAMPRATE_SECONDS, TIMEOUT_MS);
		leftMiddle.configOpenloopRamp(RAMPRATE_SECONDS, TIMEOUT_MS);
		leftBack.configOpenloopRamp(RAMPRATE_SECONDS, TIMEOUT_MS);
		
		rightFront = new WPI_TalonSRX(RIGHT_FRONT);
		rightMiddle = new WPI_TalonSRX(RIGHT_MIDDLE);
		rightBack = new WPI_TalonSRX(RIGHT_BACK);
		rightFront.configOpenloopRamp(RAMPRATE_SECONDS, TIMEOUT_MS);
		rightMiddle.configOpenloopRamp(RAMPRATE_SECONDS, TIMEOUT_MS);
		rightBack.configOpenloopRamp(RAMPRATE_SECONDS, TIMEOUT_MS);

		leftGroup = new SpeedControllerGroup(leftFront, leftMiddle, leftBack);
		leftGroup.setInverted(true);
		rightGroup = new SpeedControllerGroup(rightFront, rightMiddle, rightBack);
		rightGroup.setInverted(true);

		differentialDrive = new DifferentialDrive(leftGroup, rightGroup);
		
		try {
			navx = new AHRS(I2C.Port.kMXP);
		} catch (Exception ex) {
			DriverStation.reportError(ex.getMessage(), true);
		}

		leftEnc = new Encoder(LEFT_ENCODER_PORTA, LEFT_ENCODER_PORTB);
		leftEnc.setReverseDirection(false);
		rightEnc = new Encoder(RIGHT_ENCODER_PORTA, RIGHT_ENCODER_PORTB);
		rightEnc.setReverseDirection(true);
	}
	
	/**
	 * Drives the left and right sides separately, with inputs squared for ease of driver use.
	 * <p> DO NOT USE WITH PID.
	 * @param leftSpeed  Percentage speed of left side, from -1 to 1.
	 * @param rightSpeed  Percentage speed of right side, from -1 to 1.
	 */
	@Override
	public void tankDriveSquared(double leftSpeed, double rightSpeed) {
		differentialDrive.tankDrive(leftSpeed, rightSpeed, true);
	}
	
	@Override
	public void tankDriveRaw(double leftSpeed, double rightSpeed) {
		differentialDrive.tankDrive(leftSpeed, rightSpeed, false);
	}
	
	@Override
	public void arcadeDriveSquared(double forwardSpeed, double turnSpeed) {
		differentialDrive.arcadeDrive(forwardSpeed, -turnSpeed, true);
	}
	
	@Override
	public void arcadeDriveRaw(double forwardSpeed, double turnSpeed) {
		differentialDrive.arcadeDrive(forwardSpeed, -turnSpeed, false);
	}
	
	@Override
	public void stopDrive() {
		differentialDrive.stopMotor();
	}
	
	@Override
	public int getEncoderTicks(Side side) {
		switch(side) {
		case LEFT: return leftEnc.get();
		case RIGHT: return rightEnc.get();
		default: // compiler will throw error without a default statement
			throw new IllegalArgumentException("Invalid argument for getEncoderTicks()");
		}
	}

	@Override
	public void resetEncoderTicks(Side side) {
		switch(side) {
		case LEFT:
			leftEnc.reset();
			break;
		case RIGHT:
			rightEnc.reset();
			break;
		}
	}

	@Override
	public void resetEncoders() {
		this.resetEncoderTicks(Side.LEFT);
		this.resetEncoderTicks(Side.RIGHT);
	}
	
	/**
	 * {@code println()} the current counts of both encoders.
	 * <p> Formatted as: {@code LEFT: 00 RIGHT: 00}
	 */
	@Override
	public void printEncoderData() {
		System.out.println("LEFT: " + Double.toString(getEncoderTicks(Side.LEFT)) + 
						  " RIGHT: " + Double.toString(getEncoderTicks(Side.RIGHT)));
	}

	@Override
	public double getAccelX() {
		return navx.getWorldLinearAccelX();
	}

	@Override
	public double getAccelY() {
		return navx.getWorldLinearAccelY();
	}

	@Override
	public double getAccelZ() {
		return navx.getWorldLinearAccelZ();
	}

	@Override
	public double getPitch() {
		return navx.getPitch();
	}

	@Override
	public double getRoll() {
		return navx.getRoll();
	}

	@Override
	public double getYaw() {
		return navx.getYaw();
	}

	@Override
	public void resetYaw() {
		navx.zeroYaw();
	}

	@Override
	public double getAngle() {
		return navx.getAngle();
	}

	@Override
	public void resetAngle() {
		navx.zeroYaw();
	}
	
	/**
	 * {@code println()} the current angle of the gyroscope.
	 * <p> Formatted as: {@code ANGLE: 00}
	 */
	@Override
	public void printAngleData() {
		System.out.println("ANGLE: " + Double.toString(getAngle()));
	}
	
	public void currentToDashboard() {
		SmartDashboard.putNumber("LEFTFRONT", leftFront.getOutputCurrent());
		SmartDashboard.putNumber("LEFTMIDDLE", leftMiddle.getOutputCurrent());
		SmartDashboard.putNumber("LEFTBACK", leftBack.getOutputCurrent());
		SmartDashboard.putNumber("RIGHTFRONT", rightFront.getOutputCurrent());
		SmartDashboard.putNumber("RIGHTMIDDLE", rightMiddle.getOutputCurrent());
		SmartDashboard.putNumber("RIGHTBACK", rightBack.getOutputCurrent());
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new JoystickTeleop());
	}
}