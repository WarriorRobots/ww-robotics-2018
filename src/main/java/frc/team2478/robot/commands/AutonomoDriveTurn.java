package frc.team2478.robot.commands;

import frc.team2478.robot.RobotMap;
import frc.team2478.robot.util.DebugPrintLooper;
import frc.team2478.robot.util.SynchronousPIDF;
import edu.wpi.first.wpilibj.Timer;

/**
 * When run, the robot will turn to the provided angle,
 * using a PID loop to maintain accuracy and control.
 */
public class AutonomoDriveTurn extends AutonomoBase {
	
	private double m_angle, m_output;
	private SynchronousPIDF m_pid;
	private Timer m_timer;
	private DebugPrintLooper m_printLooper;
	
	/**
	 * Create a new instance of {@link AutonomoDriveStraight}.
	 * @param angle What angle in degrees to turn towards.
	 */
	public AutonomoDriveTurn(double angle) {
		requires(drivetrain);
		requires(motionSensors);
//		m_pid = new SynchronousPIDF(RobotMap.ClosedLoop.TURNING_P,
//				 RobotMap.ClosedLoop.TURNING_I,
//				 RobotMap.ClosedLoop.TURNING_D);
		m_pid = new SynchronousPIDF(0.02325, 0.0005, 0.02225);
		m_timer = new Timer();
		m_angle = angle;
		m_printLooper = new DebugPrintLooper();
	}
	
	protected void initialize() {
		super.initialize();
		m_pid.reset();
		m_pid.setSetpoint(m_angle);
		m_timer.reset();
		m_timer.start();
	}
	
	protected void execute() {
		m_output = m_pid.calculate(motionSensors.getNavxAngle(), m_timer.get());
		m_printLooper.println(Double.toString(m_angle));
		drivetrain.arcadeDriveAutonomo(0, m_output);
	}

	protected boolean isFinished() {
		if (m_pid.onTarget(0.5) || m_timer.get() > 10) {
			return true;
		} else {
			return false;
		}
	}
	
	protected void end() {
		super.end();
		m_timer.stop();
		m_pid.reset();
	}
}