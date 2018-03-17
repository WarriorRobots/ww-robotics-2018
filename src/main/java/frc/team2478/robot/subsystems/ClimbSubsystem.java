package frc.team2478.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.team2478.robot.Constants;

/**
 * Components that lift the robot onto a climbing bar during the last 15 seconds of the match.
 */
public class ClimbSubsystem extends Subsystem{

	private static final int WINCH_MOTOR = 13;
	private static final int HOOK_MOTOR = 14;

	private WPI_TalonSRX winchMotor, hookMotor;
	
	public ClimbSubsystem() {
		winchMotor = new WPI_TalonSRX(WINCH_MOTOR);
		winchMotor.setInverted(Constants.Inversions.WINCH_MOTOR_REVERSED);
		hookMotor = new WPI_TalonSRX(HOOK_MOTOR);
		hookMotor.setInverted(Constants.Inversions.HOOK_MOTOR_REVERSED);
	}
	public void runWinchAtPercentage(double percent) {
		winchMotor.set(ControlMode.PercentOutput, percent);
	}
	public void runHookAtPercentage(double percent) {
		hookMotor.set(ControlMode.PercentOutput, percent);
	}
	public void winchstop() {
		winchMotor.stopMotor();
	}
	public void hookstop() {
		hookMotor.stopMotor();
	}
	@Override
	public void initSendable(SendableBuilder builder) {
		builder.setSmartDashboardType("subsystem-climb");
	}
	
	@Override
	protected void initDefaultCommand() {}
}