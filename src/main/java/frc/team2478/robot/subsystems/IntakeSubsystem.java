package frc.team2478.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2478.robot.interfaces.MotorInterface;

/**
 * Instantiates intake motors, and provides methods for using them.
 */
public class IntakeSubsystem extends Subsystem implements MotorInterface {

	private final int MASTER_MOTOR = 9;
	private final int SLAVE_MOTOR = 10;

	private WPI_TalonSRX m_masterMotor, m_slaveMotor;
	
	public IntakeSubsystem() {
		m_masterMotor = new WPI_TalonSRX(MASTER_MOTOR);
		m_slaveMotor = new WPI_TalonSRX(SLAVE_MOTOR);
		
		//m_slaveMotor.setInverted(true);
		m_slaveMotor.follow(m_masterMotor);
	}
	
	@Override
	public void setPercentage(double percent) {
		m_masterMotor.set(ControlMode.PercentOutput, percent);
	}
	
	@Override
	public void stop() {
		m_masterMotor.stopMotor();
	}
	
	@Override
	protected void initDefaultCommand() {}
}
