package frc.team2478.robot.commands;

import frc.team2478.robot.RobotMap;
import frc.team2478.robot.util.DebugPrintLooper;
import frc.team2478.robot.util.SynchronousPIDF;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

public class AutonomoDriveTurn extends CommandBase {
	
	private double m_angle, m_output;
	private SynchronousPIDF m_pid;
	private Timer m_timer;
	private DebugPrintLooper m_printLooper;
	
	public AutonomoDriveTurn(double angle) {
		requires(drivetrain);
		requires(motionSensors);
		m_pid = new SynchronousPIDF(RobotMap.ClosedLoop.TURNING_P,
									RobotMap.ClosedLoop.TURNING_I,
									RobotMap.ClosedLoop.TURNING_D);
		m_timer = new Timer();
		m_angle = angle;
		m_printLooper = new DebugPrintLooper();
	}
	
	protected void initialize() {
		motionSensors.resetAllSensors();
		m_pid.reset();
		m_pid.setSetpoint(m_angle);
		m_timer.reset();
		m_timer.start();
	}
	
	protected void execute() {
		m_output = m_pid.calculate(motionSensors.getAngle(), m_timer.get());
		m_printLooper.println(Double.toString(m_angle));
		drivetrain.arcadeDriveAutonomo(0, m_output);
	}

	protected boolean isFinished() {
		if (m_pid.onTarget(0.5)) {
			return true;
		} else {
			return false;
		}
	}
	
	protected void end() {
		drivetrain.stopDrive();
		motionSensors.resetAllSensors();
		m_timer.stop();
		m_pid.reset();
	}
	
	protected void interrupted() {
		DriverStation.reportWarning("AutonomoDriveTurn interrupted", false);
    	this.end();
    }
}