package frc.team2478.robot.commands;

import frc.team2478.robot.Robot;
import frc.team2478.robot.RobotMap;
import frc.team2478.robot.util.DebugPrintLooper;
import frc.team2478.robot.util.SynchronousPIDF;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

/**
 * When run, the robot will turn to the provided angle,
 * using a PID loop to maintain accuracy and control.
 */
public class AutonomoDriveTurn extends AutonomoBase {
	
	private double m_angleTarget, m_output;
	private SynchronousPIDF m_pid;
	private Timer m_timer;
	private DebugPrintLooper m_printLooper;
	private boolean m_stopAtSetpoint = true; // @debug variable
	
	/**
	 * Create a new instance of {@link AutonomoDriveStraight}.
	 * @param angle  What angle in degrees to turn towards.
	 */
	public AutonomoDriveTurn(double angle) {
		requires(Robot.drivetrain);
		requires(Robot.motionSensors);
		m_pid = new SynchronousPIDF(RobotMap.ClosedLoop.TURNING_P,
				 RobotMap.ClosedLoop.TURNING_I,
				 RobotMap.ClosedLoop.TURNING_D);
		m_timer = new Timer();
		m_angleTarget = angle;
		m_printLooper = new DebugPrintLooper();
	}

	/**
	 * Set the internal PID constants to new values
	 * @param p  P gain
	 * @param i  I gain
	 * @param d  D gain
	 */
	public void setPID(double p, double i, double d) {
		m_pid.setPID(p, i, d);
	}
	
	/**
	 * Sets whether Command ends itself after reaching the setpoint
	 * @param stops  True if you want command to end; false if not
	 */
	public void stopAtSetpoint(boolean stops) {
		m_stopAtSetpoint = stops;
	}

	protected void initialize() {
		super.initialize();
		m_pid.reset();
		m_pid.setSetpoint(m_angleTarget);
		m_timer.reset();
		m_timer.start();
	}
	
	protected void execute() {
		if(!(m_angleTarget > 0) && !(m_angleTarget < 0)) {
			DriverStation.reportError("Robot cannot drive a distance of " + Double.toString(m_angleTarget), false);
			this.end();
		}
		m_output = m_pid.calculate(Robot.motionSensors.getNavxAngle(), m_timer.get());
		m_printLooper.println(Double.toString(Robot.motionSensors.getNavxAngle()));
		Robot.drivetrain.arcadeDriveAutonomo(0, m_output);
	}

	protected boolean isFinished() {
		if (m_pid.onTarget(0.5) && m_stopAtSetpoint) {
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