package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import frc.team2478.robot.RobotMap;
import frc.team2478.robot.control.SynchronousPIDF;

public class AutonomoDriveStraight extends CommandBase {
	
	private double m_distance, m_output;
	private SynchronousPIDF m_pid;
	private Timer m_timer;
	
	public AutonomoDriveStraight(double distance) {
		requires(drivetrain);
		requires(motionSensors);
		m_pid = new SynchronousPIDF(RobotMap.ANGULAR_P, RobotMap.COURSECORRECTION_I, RobotMap.ANGULAR_D);
		m_timer = new Timer();
		m_distance = distance;
	}
	
	protected void initialize() {
		motionSensors.resetAllSensors();
		m_pid.reset();
		m_pid.setSetpoint(0);
		m_timer.reset();
		m_timer.start();
	}
	
	protected void execute() {
		m_output = m_pid.calculate(motionSensors.getAngle(), m_timer.get());
		drivetrain.tankDriveAutonomo(RobotMap.AUTO_SPEED_FORWARDS + m_output, RobotMap.AUTO_SPEED_FORWARDS - m_output);
	}

	protected boolean isFinished() {
		if (motionSensors.getLeftEncCount() > m_distance && motionSensors.getRightEncCount() > m_distance) {
			return true;
		} else {
			return false;
		}
	}
}
