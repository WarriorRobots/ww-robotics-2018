package frc.team2478.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.team2478.robot.Constants;
import frc.team2478.robot.commands.drive.TankDriveTeleop;
import frc.team2478.robot.util.enums.RobotSide;

/**
 * Components that move the robot wheels or sense its position.
 */
public class DrivetrainSubsystem extends Subsystem {

	private static final int LEFT_FRONT = 4;
	private static final int LEFT_MIDDLE = 5;
	private static final int LEFT_BACK = 6;
	private static final int RIGHT_FRONT = 1;
	private static final int RIGHT_MIDDLE = 2;
	private static final int RIGHT_BACK = 3;
	
	private static final int LEFT_ENCODER_PORTA = 2;
	private static final int LEFT_ENCODER_PORTB = 3;
	private static final int RIGHT_ENCODER_PORTA = 0;
	private static final int RIGHT_ENCODER_PORTB = 1;
	
	private static final double RAMPRATE_SECONDS = 0.15;
	private static final int TIMEOUT_MS = 10;
	
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
		differentialDrive.setSafetyEnabled(false);
		
		// if NavX is missing, this code will handle errors and prevent a crash
		try {
			navx = new AHRS(I2C.Port.kMXP);
		} catch (Exception ex) {
			DriverStation.reportError(ex.getMessage(), true);
		}

		leftEnc = new Encoder(LEFT_ENCODER_PORTA, LEFT_ENCODER_PORTB);
		rightEnc = new Encoder(RIGHT_ENCODER_PORTA, RIGHT_ENCODER_PORTB);
		leftEnc.setReverseDirection(Constants.Inversions.LEFT_ENCODER_REVERSED);
		rightEnc.setReverseDirection(Constants.Inversions.RIGHT_ENCODER_REVERSED);
	}
	
	/**
	 * Drives the left and right sides of the robot independently. DO NOT USE WITH PID.
	 * <p> The arguments provided are squared to create a more intuitive control sensitivity.
	 * @param leftSpeed  Percentage speed of left side, from -1 to 1.
	 * @param rightSpeed  Percentage speed of right side, from -1 to 1.
	 */
	public void tankDriveSquared(double leftSpeed, double rightSpeed) {
		if(Constants.DISABLED_DRIVE) {this.stopDrive(); return;}
		// The reason for the right input being fed into the left output and vice versa is when
		// the robot sides have to be reversed as well or else the robot turns the opposite direction
		if (getReversed()) {
			differentialDrive.tankDrive(-rightSpeed, -leftSpeed, true);
		} else {
			differentialDrive.tankDrive(leftSpeed, rightSpeed, true);
		}
	}
	
	/**
	 * Drives the left and right sides of the robot independently. USE WITH PID ONLY.
	 * <p> The arguments provided are not squared to prevent PID overcompensation.
	 * @param leftSpeed  Percentage speed of left side, from -1 to 1.
	 * @param rightSpeed  Percentage speed of right side, from -1 to 1.
	 */
	public void tankDriveRaw(double leftSpeed, double rightSpeed) {
		if(Constants.DISABLED_DRIVE) {this.stopDrive(); return;}
		// see above
		if (getReversed()) {
			differentialDrive.tankDrive(-rightSpeed, -leftSpeed, false);
		} else {
			differentialDrive.tankDrive(leftSpeed, rightSpeed, false);
		}
	}
	
	/**
	 * Sets the forward and turning speeds of the robot independently. DO NOT USE WITH PID.
	 * <p> The arguments provided are squared to create a more intuitive control sensitivity.
	 * @param forwardSpeed  Percentage speed for driving forwards or backwards, from -1 to 1.
	 * @param turnSpeed  Percentage speed for turning, from -1 (left) to 1 (right).
	 */
	public void arcadeDriveSquared(double forwardSpeed, double turnSpeed) {
		turnSpeed = -turnSpeed; // turning is inverted on the robot
		if(Constants.DISABLED_DRIVE) {this.stopDrive(); return;}
		if (getReversed()) {
			differentialDrive.arcadeDrive(-forwardSpeed, turnSpeed, true);
		} else {
			differentialDrive.arcadeDrive(forwardSpeed, turnSpeed, true);
		}
	}
	
	/**
	 * Sets the forward and turning speeds of the robot independently. USE WITH PID ONLY.
	 * <p> The arguments provided are not squared to prevent PID overcompensation.
	 * @param forwardSpeed  Percentage speed for driving forwards or backwards, from -1 to 1.
	 * @param turnSpeed  Percentage speed for turning, from -1 (left) to 1 (right).
	 */
	public void arcadeDriveRaw(double forwardSpeed, double turnSpeed) {
		turnSpeed = -turnSpeed; // turning is inverted on the robot
		if(Constants.DISABLED_DRIVE) {this.stopDrive(); return;}
		if (getReversed()) {
			differentialDrive.arcadeDrive(-forwardSpeed, turnSpeed, false);
		} else {
			differentialDrive.arcadeDrive(forwardSpeed, turnSpeed, false);
		}
	}
	
	/**
	 * Shuts off all drive motors and feeds watchdog timer.
	 */
	public void stopDrive() {
		differentialDrive.stopMotor();
	}
	
	/**
	 * Returns the current encoder value in ticks (128 per rotation).
	 * <p> Providing an invalid argument will raise an IllegalArgumentException().
	 * @param side  Side.LEFT or Side.RIGHT
	 * @return Current encoder count as an integer value
	 */
	public int getEncoderTicks(RobotSide side) {
		switch(side) {
		case LEFT: return leftEnc.get();
		case RIGHT: return rightEnc.get();
		default: // compiler will throw error without a default statement
			throw new IllegalArgumentException("Invalid argument for getEncoderTicks()");
		}
	}

	/**
	 * Resets the encoder specified to 0 ticks.
	 * @param side  Side.LEFT or Side.RIGHT
	 */
	public void resetEncoderTicks(RobotSide side) {
		switch(side) {
		case LEFT:
			leftEnc.reset();
			break;
		case RIGHT:
			rightEnc.reset();
			break;
		}
	}

	/**
	 * Resets all drive encoders to 0 ticks.
	 * <p> Shorthand for {@code resetEncoderTicks(Side.LEFT)} and {@code resetEncoderTicks(Side.RIGHT)}.
	 */
	public void resetEncoders() {
		this.resetEncoderTicks(RobotSide.LEFT);
		this.resetEncoderTicks(RobotSide.RIGHT);
	}

	/**
	 * Gets current angle (yaw) that the robot is facing.
	 * @return  Double value representing angle in degrees, can fall outside the set [0,360].
	 */
	public double getAngle() {
		return navx.getAngle();
	}

	/**
	 * Sets current robot angle to 0 degrees.
	 */
	public void resetAngle() {
		navx.zeroYaw();
	}
	
	/**
	 * Check if the robot drivetrain is reversed.
	 * <p> While in reverse, the robot will behave as if the back end is the front.
	 * @return  Boolean value; true if reversed, false if not
	 */
	public boolean getReversed() {
		return isReversed;
	}
	
	/**
	 * Set the robot drivetrain into either forwards or reversed mode.
	 * <p> While in reverse, the robot will behave as if the back end is the front.
	 * @param isReversed  True to reverse, false to reset
	 */
	public void setReversed(boolean isReversed) {
		this.isReversed = isReversed;
	}

	@Override
	public void initSendable(SendableBuilder builder) {
		builder.setSmartDashboardType("subsystem-drivetrain");
		builder.addDoubleArrayProperty("currentdraw", () -> {
			if (DriverStation.getInstance().isEnabled()) {
				double[] currentDraw = new double[6];
				currentDraw[0] = leftFront.getOutputCurrent();
				currentDraw[1] = leftMiddle.getOutputCurrent();
				currentDraw[2] = leftBack.getOutputCurrent();
				currentDraw[3] = rightFront.getOutputCurrent();
				currentDraw[4] = rightMiddle.getOutputCurrent();
				currentDraw[5] = rightBack.getOutputCurrent();
				return currentDraw;
			} else {
				double[] temp = new double[1];
				temp[0] = 0;
				return temp;
			}
		}, null);
		builder.addBooleanProperty("inverted", () -> getReversed(), null);
		builder.addStringProperty("encoder-ticks", () -> {
			return (Integer.toString(getEncoderTicks(RobotSide.LEFT))
					+ " " 
					+ Integer.toString(getEncoderTicks(RobotSide.RIGHT)));
		}, null);
		builder.addDoubleProperty("angle", () -> getAngle(), null);
	}
	
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new TankDriveTeleop());
	}
}