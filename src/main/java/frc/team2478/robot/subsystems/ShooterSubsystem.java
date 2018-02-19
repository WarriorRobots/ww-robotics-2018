package frc.team2478.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2478.robot.interfaces.ShooterInterface;

/**
 * Instantiates shooter motors and sets up Talon PIDs,
 * and provides methods for using or altering them.
 */
public class ShooterSubsystem extends Subsystem implements ShooterInterface {

	public final int SLAVE_MOTOR = 11; // right
	public final int MASTER_MOTOR = 12; // left
	
	public final int PROCESS_ID = 0;
	public final int TIMEOUT_MS = 10;
	
	private WPI_TalonSRX masterMotor, slaveMotor;
	
	private Target currentTarget = Target.MID;
	private double lowSpeed = 1000;
	private double midSpeed = 1500;
	private double highSpeed = 2000;
	
	public ShooterSubsystem() {
		masterMotor = new WPI_TalonSRX(MASTER_MOTOR);
		slaveMotor = new WPI_TalonSRX(SLAVE_MOTOR);
		
		//slaveMotor.setInverted(true);
		slaveMotor.follow(masterMotor);
		
		masterMotor.configSelectedFeedbackSensor(
				FeedbackDevice.QuadEncoder,
				PROCESS_ID,
				TIMEOUT_MS);
		
		masterMotor.setSensorPhase(true);
	}
	
	public enum Target {
		LOW, MID, HIGH;
	}
	
	/**
	 * Set velocity of the shooter.
	 * @param velocity Velocity in clicks/100ms
	 */
	@Override
	public void setTargetVelocity(double velocity) {
		masterMotor.set(ControlMode.Velocity, velocity);
	}
	
	public void shootForCurrentTarget() {
		switch (getCurrentTarget()) {
		case HIGH:
			setTargetVelocity(highSpeed);
			break;
		case MID:
			setTargetVelocity(midSpeed);
			break;
		case LOW:
			setTargetVelocity(lowSpeed);
			break;
		}
	}
	
	public Target getCurrentTarget() {
		return currentTarget;
	}
	
	public void incrementTarget() {
		switch (getCurrentTarget()) {
		case HIGH:
			DriverStation.reportWarning("Target already at max", false);
			break;
		case MID:
			currentTarget = Target.HIGH;
			break;
		case LOW:
			currentTarget = Target.MID;
			break;
		}
	}
	
	public void decrementTarget() {
		switch (getCurrentTarget()) {
		case HIGH:
			currentTarget = Target.MID;
			break;
		case MID:
			currentTarget = Target.LOW;
			break;
		case LOW:
			DriverStation.reportWarning("Target already at min", false);
			break;
		}
	}
	
	/**
	 * Get velocity of the shooter.
	 * @return Velocity in clicks/100ms
	 */
	@Override
	public double getVelocity() {
		return masterMotor.getSelectedSensorVelocity(PROCESS_ID);
	}
	
	@Override
	public double getPosition() {
		return masterMotor.getSelectedSensorPosition(PROCESS_ID);
	}
	
	/**
	 * Set the percent moter speed.
	 * @param percent -1 to 1
	 */
	@Override
	public void setTargetPercentage(double percent) {
		masterMotor.set(ControlMode.PercentOutput, percent);
	}
	
	/**
	 * Stop the shooter.
	 */
	@Override
	public void stop() {
		masterMotor.stopMotor();
	}
	
	@Override
	public void setPID(double p, double i, double d) {
		masterMotor.config_kP(PROCESS_ID, p, TIMEOUT_MS);
		masterMotor.config_kI(PROCESS_ID, i, TIMEOUT_MS);
		masterMotor.config_kD(PROCESS_ID, d, TIMEOUT_MS);
	}
	
	@Override
	public void setFeedForward(double f) {
		masterMotor.config_kF(PROCESS_ID, f, TIMEOUT_MS);		
	}

	@Override
	public void resetEncoder() {
		masterMotor.setSelectedSensorPosition(0, PROCESS_ID, TIMEOUT_MS);
	}
	
	@Override
	@Deprecated
	public boolean isUsingPid() {
		return true;
	}
	
	@Override
	@Deprecated
	public void setUsingPid(boolean b) {}

	@Override
	protected void initDefaultCommand() {}
}