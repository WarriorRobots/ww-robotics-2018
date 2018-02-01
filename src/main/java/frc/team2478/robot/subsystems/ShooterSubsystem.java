package frc.team2478.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Instantiates shooter motors and sets up Talon PIDs,
 * and provides methods for using or altering them.
 */
public class ShooterSubsystem extends Subsystem {

	public WPI_TalonSRX m_masterMotor, m_slaveMotor;
	
	public final int MASTER_MOTOR = 7;
	public final int SLAVE_MOTOR = 8;
	
	public final int PROCESS_ID = 0;
	public final int TIMEOUT_MS = 10;
	
	public ShooterSubsystem() {
		m_masterMotor = new WPI_TalonSRX(MASTER_MOTOR);
		m_slaveMotor = new WPI_TalonSRX(SLAVE_MOTOR);
		
		//m_slaveMotor.setInverted(true);
		m_slaveMotor.follow(m_masterMotor);
		
		m_masterMotor.configSelectedFeedbackSensor(
				FeedbackDevice.QuadEncoder,
				PROCESS_ID,
				TIMEOUT_MS);
		
		m_masterMotor.setSensorPhase(true);
	}
	
	public void setVelocity(double velocity) {
		m_masterMotor.set(ControlMode.Velocity, velocity);
	}
	
	@Deprecated
	public void setPercentage(double percent) {
		m_masterMotor.set(ControlMode.PercentOutput, percent);
	}
	
	public void stop() {
		m_masterMotor.stopMotor();
	}
	
	public void setPID(double p, double i, double d, double f) {
		m_masterMotor.config_kP(PROCESS_ID, p, TIMEOUT_MS);
		m_masterMotor.config_kI(PROCESS_ID, i, TIMEOUT_MS);
		m_masterMotor.config_kD(PROCESS_ID, d, TIMEOUT_MS); 
		m_masterMotor.config_kF(PROCESS_ID, f, TIMEOUT_MS);
	}
	
	@Override
	protected void initDefaultCommand() {}
}