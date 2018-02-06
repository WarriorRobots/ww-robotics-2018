package frc.team2478.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2478.robot.interfaces.MotorInterface;

/**
 * Instantiates feed motors on the robot, and provides methods for using them.
 */
public class FeedSubsystem extends Subsystem implements MotorInterface {

	private final int MASTER_MOTOR = 11;
	private final int SLAVE_MOTOR = 12;

	private WPI_TalonSRX masterMotor, slaveMotor;
	
	public FeedSubsystem() {
		masterMotor = new WPI_TalonSRX(MASTER_MOTOR);
		slaveMotor = new WPI_TalonSRX(SLAVE_MOTOR);
		
		//slaveMotor.setInverted(true);
		slaveMotor.follow(masterMotor);
	}
	
	@Override
	public void setPercentage(double percent) {
		masterMotor.set(ControlMode.PercentOutput, percent);
	}
	
	@Override
	public void stop() {
		masterMotor.stopMotor();
	}
	
	@Override
	protected void initDefaultCommand() {}
}
