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
import frc.team2478.robot.Constants;
import frc.team2478.robot.commands.drive.TankDriveTeleop;
import frc.team2478.robot.util.enums.Side;

/**
 * Instantiates drivetrain motors and provides methods for running WPILib drive functions.
 */
public class DrivetrainSubsystem extends Subsystem {

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
	
	private boolean isReversed = false;
	
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
	public void tankDriveSquared(double leftSpeed, double rightSpeed) {
		if(Constants.DISABLED_DRIVE) return;
		leftSpeed = invertIfReversed(leftSpeed);
		rightSpeed = invertIfReversed(rightSpeed);
		differentialDrive.tankDrive(leftSpeed, rightSpeed, true);
	}
	
	public void tankDriveRaw(double leftSpeed, double rightSpeed) {
		if(Constants.DISABLED_DRIVE) return;
		leftSpeed = invertIfReversed(leftSpeed);
		rightSpeed = invertIfReversed(rightSpeed);
		differentialDrive.tankDrive(leftSpeed, rightSpeed, false);
	}
	
	public void arcadeDriveSquared(double forwardSpeed, double turnSpeed) {
		if(Constants.DISABLED_DRIVE) return;
		forwardSpeed = invertIfReversed(forwardSpeed);
		differentialDrive.arcadeDrive(forwardSpeed, -turnSpeed, true);
	}
	
	public void arcadeDriveRaw(double forwardSpeed, double turnSpeed) {
		if(Constants.DISABLED_DRIVE) return;
		forwardSpeed = invertIfReversed(forwardSpeed);
		differentialDrive.arcadeDrive(forwardSpeed, -turnSpeed, false);
	}
	
	public void stopDrive() {
		differentialDrive.stopMotor();
	}
	
	public int getEncoderTicks(Side side) {
		switch(side) {
		case LEFT: return leftEnc.get();
		case RIGHT: return rightEnc.get();
		default: // compiler will throw error without a default statement
			throw new IllegalArgumentException("Invalid argument for getEncoderTicks()");
		}
	}

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

	public void resetEncoders() {
		this.resetEncoderTicks(Side.LEFT);
		this.resetEncoderTicks(Side.RIGHT);
	}
	
	/**
	 * {@code println()} the current counts of both encoders.
	 * <p> Formatted as: {@code LEFT: 00 RIGHT: 00}
	 */
	public void printEncoderData() {
		System.out.println("LEFT: " + Double.toString(getEncoderTicks(Side.LEFT)) + 
						  " RIGHT: " + Double.toString(getEncoderTicks(Side.RIGHT)));
	}

	public double getAccelX() {
		return navx.getWorldLinearAccelX();
	}

	public double getAccelY() {
		return navx.getWorldLinearAccelY();
	}

	public double getAccelZ() {
		return navx.getWorldLinearAccelZ();
	}

	public double getPitch() {
		return navx.getPitch();
	}

	public double getRoll() {
		return navx.getRoll();
	}

	public double getYaw() {
		return navx.getYaw();
	}

	public void resetYaw() {
		navx.zeroYaw();
	}

	public double getAngle() {
		return navx.getAngle();
	}

	public void resetAngle() {
		navx.zeroYaw();
	}
	
	/**
	 * {@code println()} the current angle of the gyroscope.
	 * <p> Formatted as: {@code ANGLE: 00}
	 */
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
	
	/**
	 * @param drive  A number to be reversed if the robot is in reverse.
	 * @return The same number that was input or its opposite.
	 * @see {@link frc.team2478.robot.commands.drive.ReverseDrive}
	 */
	public double invertIfReversed(double drive) {
		return (getReversed() == true) ? -drive : drive;
	}
	
	public boolean getReversed() {
		return isReversed;
	}
	
	public void setReversed(boolean isReversed) {
		this.isReversed = isReversed;
	}
	
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new TankDriveTeleop());
	}
}