package frc.team2478.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Instantiates feed motors on the robot, and provides methods for using them.
 */
public class FeedSubsystem extends Subsystem {

	private WPI_TalonSRX m_masterMotor, m_slaveMotor;
	private final int MASTER_MOTOR = 11;
	private final int SLAVE_MOTOR = 12;
	
	public FeedSubsystem() {
		m_masterMotor = new WPI_TalonSRX(MASTER_MOTOR);
		m_slaveMotor = new WPI_TalonSRX(SLAVE_MOTOR);
		
		//m_slaveMotor.setInverted(true);
		m_slaveMotor.follow(m_masterMotor);
	}
	
	public void set(double percent) {
		m_masterMotor.set(ControlMode.PercentOutput, percent);
	}
	
	public void stop() {
		m_masterMotor.stopMotor();
	}
	
	@Override
	protected void initDefaultCommand() {}
}
