package frc.team2478.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.team2478.robot.commands.scoring.shooter.RunShooterWithManualControl;

/**
 * Instantiates shooter motors and sets up Talon PIDs,
 * and provides methods for using or altering them.
 */
public class ShooterSubsystem extends Subsystem {

	public final int SLAVE_MOTOR = 11; // right
	public final int MASTER_MOTOR = 12; // left
	
	public final int PROCESS_ID = 0;
	public final int TIMEOUT_MS = 10;
	
	private WPI_TalonSRX masterMotor, slaveMotor;
	
	private Target currentTarget = Target.MID;
//	private double lowSpeed = 1000;
//	private double midSpeed = 1500;
//	private double highSpeed = 2000;
	
	public ShooterSubsystem() {
		DriverStation.reportError("FIX ENCODER ASSIGNMENT", false);
		masterMotor = new WPI_TalonSRX(MASTER_MOTOR);
		slaveMotor = new WPI_TalonSRX(SLAVE_MOTOR);
		masterMotor.setInverted(true);
		slaveMotor.setInverted(true);
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
	 * @param velocity  Velocity in clicks/100ms
	 */
	public void setTargetVelocity(double velocity) {
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
			setTargetPercentage(0.7);
			break;
		case MID:
			setTargetPercentage(0.5);
			break;
		case LOW:
			setTargetPercentage(0.2);
			break;
		}
	}
	
	public Target getCurrentTarget() {
		return currentTarget;
	}
	
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
	 * Get velocity of the shooter.
	 * @return Velocity in clicks/100ms
	 */
	public double getVelocity() {
		return masterMotor.getSelectedSensorVelocity(PROCESS_ID);
	}
	
	public double getPosition() {
		return masterMotor.getSelectedSensorPosition(PROCESS_ID);
	}
	
	/**
	 * Set the percent motor speed.
	 * @param percent -1 to 1
	 */
	public void setTargetPercentage(double percent) {
		masterMotor.set(ControlMode.PercentOutput, percent);
	}
	
	/**
	 * Stop the shooter.
	 */
	public void stop() {
		masterMotor.stopMotor();
	}
	
	public void setPID(double p, double i, double d) {
		masterMotor.config_kP(PROCESS_ID, p, TIMEOUT_MS);
		masterMotor.config_kI(PROCESS_ID, i, TIMEOUT_MS);
		masterMotor.config_kD(PROCESS_ID, d, TIMEOUT_MS);
	}
	
	public void setFeedForward(double f) {
		masterMotor.config_kF(PROCESS_ID, f, TIMEOUT_MS);		
	}

	public void resetEncoder() {
		masterMotor.setSelectedSensorPosition(0, PROCESS_ID, TIMEOUT_MS);
	}
	
	/**
	 * Dashboard setup for shooter.
	 * @author Josh (borrowed from Alex)
	 */
	@Override
	public void initSendable(SendableBuilder builder) {
		builder.setSmartDashboardType("subsystem-shooter");
		builder.addDoubleArrayProperty("currentdraw", () -> {
			double[] currentDraw = new double[2];
			currentDraw[0] = masterMotor.getOutputCurrent();
			currentDraw[1] = slaveMotor.getOutputCurrent();
			return currentDraw;
		}, null);
//		builder.addDoubleArrayProperty("velocity", () -> {
//			double[] encoderTicks = new double[1];
//			encoderTicks[0] = getVelocity();
////			encoderTicks[1] = getEncoderTicks(Side.RIGHT);
//			return encoderTicks;
//		}, null);
		builder.addDoubleProperty("velocity", () -> getVelocity(), null);
	}
	
	@Deprecated
	public boolean isUsingPid() {
		return false;
	}
	
	@Deprecated
	public void setUsingPid(/*boolean b*/) {}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new RunShooterWithManualControl());
	}
}