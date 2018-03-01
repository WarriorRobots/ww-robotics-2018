package frc.team2478.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.team2478.robot.Constants;
import frc.team2478.robot.util.enums.Target;

/**
 * Components that involve sending the cube airborne, out of the robot.
 */
public class ShooterSubsystem extends Subsystem {

	private static final int SLAVE_MOTOR = 11; // right
	private static final int MASTER_MOTOR = 12; // left
	
	private static final int PROCESS_ID = 0;
	private static final int TIMEOUT_MS = 10;
	
	private WPI_TalonSRX masterMotor, slaveMotor;
	
	private Target currentTarget = Target.MID;
//	private double lowSpeed = 1000;
//	private double midSpeed = 1500;
//	private double highSpeed = 2000;
	
	public ShooterSubsystem() {
		masterMotor = new WPI_TalonSRX(MASTER_MOTOR);
		slaveMotor = new WPI_TalonSRX(SLAVE_MOTOR);
		masterMotor.setInverted(Constants.Inversions.SHOOTER_MASTER_REVERSED);
		slaveMotor.setInverted(Constants.Inversions.SHOOTER_SLAVE_REVERSED);
		slaveMotor.follow(masterMotor);
		
		DriverStation.reportError("FIX ENCODER ASSIGNMENT", false);
		masterMotor.configSelectedFeedbackSensor(
				FeedbackDevice.QuadEncoder,
				PROCESS_ID,
				TIMEOUT_MS);
		
		masterMotor.setSensorPhase(true);
	}
	
	/**
	 * Runs the shooter at a velocity value in native units per 100ms.
	 * @param velocity  Double value: native units per 100ms
	 */
	public void runAtNativeUnitVelocity(double velocity) {
		masterMotor.set(ControlMode.Velocity, velocity);
	}
	
//	public void shootForCurrentTarget() {
//		switch (getCurrentTarget()) {
//		case HIGH:
//			setTargetVelocity(highSpeed);
//			break;
//		case MID:
//			setTargetVelocity(midSpeed);
//			break;
//		case LOW:
//			setTargetVelocity(lowSpeed);
//			break;
//		}
//	}

	@Deprecated
	public void shootForCurrentTarget() {
		switch (getCurrentTarget()) {
		case HIGH:
			runAtPercentage(0.7);
			break;
		case MID:
			runAtPercentage(0.5);
			break;
		case LOW:
			runAtPercentage(0.2);
			break;
		}
	}
	
	public Target getCurrentTarget() {
		return currentTarget;
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
			currentTarget = Target.HIGH;
			break;
		case LOW:
			currentTarget = Target.MID;
			break;
		}
	}
	
	/**
	 * Decreases the current shooter speed setting by one level.
	 */
	public void decrementTarget() {
		switch (getCurrentTarget()) {
		case HIGH:
			currentTarget = Target.MID;
			break;
		case MID:
			currentTarget = Target.LOW;
			break;
		case LOW:
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
	
	public void runAtPercentage(double percent) {
		masterMotor.set(ControlMode.PercentOutput, percent);
	}
	
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
		builder.addDoubleArrayProperty("currentdraw", () -> {
			double[] currentDraw = new double[2];
			currentDraw[0] = masterMotor.getOutputCurrent();
			currentDraw[1] = slaveMotor.getOutputCurrent();
			return currentDraw;
		}, null);
		builder.addDoubleProperty("velocity-nativeunits", () -> getNativeUnitVelocity(), null);
	}
	
	@Deprecated
	public boolean isUsingPid() {
		return false;
	}
	
	@Deprecated
	public void setUsingPid(/*boolean b*/) {}

	@Deprecated
	@Override
	protected void initDefaultCommand() {
//		setDefaultCommand(new RunShooterWithManualControl());
	}
}