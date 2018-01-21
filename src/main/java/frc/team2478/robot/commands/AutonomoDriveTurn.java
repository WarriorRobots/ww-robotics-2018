package frc.team2478.robot.commands;

import frc.team2478.robot.RobotMap;
import frc.team2478.robot.control.SynchronousPIDF;

import edu.wpi.first.wpilibj.Timer;

public class AutonomoDriveTurn extends CommandBase {
	
	private double m_angle, m_output;
	private SynchronousPIDF m_pid;
	private Timer m_timer;
	
	public AutonomoDriveTurn(double angle) {
		requires(drivetrain);
		requires(motionSensors);
		m_pid = new SynchronousPIDF(RobotMap.ANGULAR_P, RobotMap.ANGULAR_I, RobotMap.ANGULAR_D);
		m_timer = new Timer();
		m_angle = angle;
	}
	
	protected void initialize() {
		motionSensors.resetEncoders();
		motionSensors.resetNavx();
		m_pid.reset();
		m_pid.setSetpoint(m_angle);
		m_timer.start();
	}
	
	protected void execute() {
		m_output = m_pid.calculate(motionSensors.getAngle(), m_timer.get());
		drivetrain.tankDriveAutonomo(m_output, -m_output);
	}

	protected boolean isFinished() {
		if (m_pid.onTarget(0.5)) {
			return true;
		} else {
			return false;
		}
	}
}
