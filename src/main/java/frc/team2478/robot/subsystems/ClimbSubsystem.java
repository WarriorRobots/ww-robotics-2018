package frc.team2478.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.team2478.robot.Constants;

/**
 * Components that lift the robot onto a climbing bar during the last 15 seconds of the match.
 */
public class ClimbSubsystem extends Subsystem {

	private static final int MASTER_MOTOR = 13;

	private WPI_TalonSRX masterMotor;
	
	public ClimbSubsystem() {
		masterMotor = new WPI_TalonSRX(MASTER_MOTOR);
		masterMotor.setInverted(Constants.Inversions.CLIMB_MOTOR_REVERSED);
	}
	
	/**
	 * Sets the climb motor to a percentage of its maximum power.
	 * @param percent  Percentage in decimal format, -1 to 1.
	 */
	public void runAtPercentage(double percent) {
		masterMotor.set(ControlMode.PercentOutput, percent);
	}
	
	/**
	 * Shuts off the motor and feeds the watchdog.
	 */
	public void stop() {
		masterMotor.stopMotor();
	}
	
	@Override
	public void initSendable(SendableBuilder builder) {
		builder.setSmartDashboardType("subsystem-climb");
		builder.addDoubleProperty("currentdraw", () -> masterMotor.getOutputCurrent(), null);
	}
	
	@Override
	protected void initDefaultCommand() {}
}