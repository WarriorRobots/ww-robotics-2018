package frc.team2478.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.team2478.robot.Constants;
import frc.team2478.robot.commands.scoring.shooter.StopShooterPeriodic;
import frc.team2478.robot.interfaces.TandemMotorInterface;
import frc.team2478.robot.util.enums.ShotHeight;

/**
 * Contains the wheels that launch cubes out of the robot at scoring targets.
 */
public class ShooterSubsystem extends Subsystem implements TandemMotorInterface {

	private static final int SLAVE_MOTOR = 11; // right
	private static final int MASTER_MOTOR = 12; // left
	
	private static final int PROCESS_ID = 0;
	private static final int TIMEOUT_MS = 10;
	
	private WPI_TalonSRX masterMotor, slaveMotor;
	
	private ShotHeight currentTarget = ShotHeight.MID;
	
	public ShooterSubsystem() {
		masterMotor = new WPI_TalonSRX(MASTER_MOTOR);
		slaveMotor = new WPI_TalonSRX(SLAVE_MOTOR);
		masterMotor.setInverted(Constants.Inversions.SHOOTER_MASTER_REVERSED);
		slaveMotor.setInverted(Constants.Inversions.SHOOTER_SLAVE_REVERSED);
		slaveMotor.follow(masterMotor);
		
		masterMotor.configSelectedFeedbackSensor(
				FeedbackDevice.QuadEncoder,
				PROCESS_ID,
				TIMEOUT_MS);
		
		masterMotor.setSensorPhase(Constants.Inversions.SHOOTER_ENCODER_REVERSED);
		masterMotor.config_kP(PROCESS_ID, 0.1, TIMEOUT_MS); // 0.1prac
		masterMotor.config_kI(PROCESS_ID, 0.0, TIMEOUT_MS); // 0.0prac
		masterMotor.config_kD(PROCESS_ID, 1.0, TIMEOUT_MS); // 1.0prac
		masterMotor.config_kF(PROCESS_ID, 0.042, TIMEOUT_MS); // 0.042prac
	}
	
	/**
	 * Runs the shooter at a velocity value in native units per 100ms.
	 * @param velocity  Double value: native units per 100ms
	 */
	public void runAtNativeUnitVelocity(double velocity) {
		masterMotor.set(ControlMode.Velocity, velocity);
		slaveMotor.set(ControlMode.Follower, 12); // We set this because we weren't sure why there would be a Follower case if it didn't need to be explicitly stated.
		//System.out.println(Double.toString(velocity) + ":velocity ControlModeVel: ");
	}
	
	/**
	 * Sets the shooter motors to a predetermined velocity based on the Target currently chosen.
	 */
	public void shootForCurrentTarget() {
		switch (getCurrentTarget()) {
		case HIGH:
			runAtNativeUnitVelocity(Constants.ShooterRig.HIGH_SPEED);
			//System.out.println(Double.toString(Constants.ShooterRig.HIGH_SPEED) + ":HIGH");
			break;
		case MID:
			runAtNativeUnitVelocity(Constants.ShooterRig.MID_SPEED);
			//System.out.println(Double.toString(Constants.ShooterRig.MID_SPEED) + ":MID");
			break;
		case LOW:
			runAtNativeUnitVelocity(Constants.ShooterRig.LOW_SPEED);
			//System.out.println(Double.toString(Constants.ShooterRig.LOW_SPEED) + ":LOW");
			break;
		case SWITCH:
			runAtNativeUnitVelocity(Constants.ShooterRig.SWITCH_SPEED);
			break;
		}
	}

	// MAKE INTO A NEW METHOD
//	@Deprecated
//	public void shootForCurrentTarget() {
//		switch (getCurrentTarget()) {
//		case HIGH:
//			runAtPercentage(0.6);
//			break;
//		case MID:
//			runAtPercentage(0.55);
//			break;
//		case LOW:
//			runAtPercentage(0.5);
//			break;
//		}
//	}
	
	/**
	 * Checks the currently-selected target.
	 * @return  Enum {@code Target}, with values LOW, MID, or HIGH
	 */
	public ShotHeight getCurrentTarget() {
		return currentTarget;
	}
	
	public void setCurrentTarget(ShotHeight s) {
		currentTarget = s;
	}
	
	/**
	 * Increases the current shooter speed setting by one level.
	 */
	public void incrementTarget() {
		switch (getCurrentTarget()) {
		case HIGH:
			System.out.println("Target already at max");
			break;
		case MID:
			currentTarget = ShotHeight.HIGH;
			break;
		case LOW:
			currentTarget = ShotHeight.MID;
			break;
		case SWITCH:
			currentTarget = ShotHeight.LOW;
			break;
		}
	}
	
	/**
	 * Decreases the current shooter speed setting by one level.
	 */
	public void decrementTarget() {
		switch (getCurrentTarget()) {
		case HIGH:
			currentTarget = ShotHeight.MID;
			break;
		case MID:
			currentTarget = ShotHeight.LOW;
			break;
		case LOW:
			currentTarget = ShotHeight.SWITCH;
			break;
		case SWITCH:
			System.out.println("Target already at min");
			break;
		}
	}
	
	/**
	 * Gets the velocity of the shooter wheels in native units / 100ms.
	 * @return Double value representing velocity of shooter wheels.
	 */
	public double getNativeUnitVelocity() {
		return masterMotor.getSelectedSensorVelocity(PROCESS_ID);
	}
	
	@Override
	public void runAtPercentage(double percent) {
		masterMotor.set(ControlMode.PercentOutput, percent);
	}
	
	@Override
	public void stop() {
		masterMotor.stopMotor();
	}
	
	/**
	 * Sets the PID loop of the shooter motors to new values.
	 * @param p  Proportional value (measures current error)
	 * @param i  Integral value (measures error over time)
	 * @param d  Derivative value (measures rate of error change)
	 */
	public void setPID(double p, double i, double d) {
		masterMotor.config_kP(PROCESS_ID, p, TIMEOUT_MS);
		masterMotor.config_kI(PROCESS_ID, i, TIMEOUT_MS);
		masterMotor.config_kD(PROCESS_ID, d, TIMEOUT_MS);
	}
	
	/**
	 * Sets the feed-forward value of the shooter PID loop.
	 * @param f  Feed-forwards value (defines base speed without PID interference)
	 */
	public void setFeedForward(double f) {
		masterMotor.config_kF(PROCESS_ID, f, TIMEOUT_MS);		
	}

	/**
	 * Resets the shooter encoder to 0 ticks.
	 */
	public void resetEncoders() {
		masterMotor.setSelectedSensorPosition(0, PROCESS_ID, TIMEOUT_MS);
	}
	
	@Override
	public void initSendable(SendableBuilder builder) {
		builder.setSmartDashboardType("subsystem-shooter");
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new StopShooterPeriodic());
	}
}