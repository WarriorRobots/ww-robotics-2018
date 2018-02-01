package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.RobotMap;
import frc.team2478.robot.util.DebugPrintLooper;
import frc.team2478.robot.util.SynchronousPIDF;

/**
 * When run, the robot will turn to the provided angle,
 * using a PID loop to maintain accuracy and control.
 */
public class AutonomoDriveTurn extends Command implements CommandBase {
	
	private double m_angleTarget, m_output;
	private boolean m_stopsAtSetpoint = true; // @debug variable
	private SynchronousPIDF m_pidLoop;
	private Timer m_timer;
	private DebugPrintLooper m_printLooper;
	
	/**
	 * Create a new instance of {@link AutonomoDriveStraight}.
	 * @param angle  What angle in degrees to turn towards.
	 */
	public AutonomoDriveTurn(double angle) {
		requires(drivetrain);
		requires(motionSensors);

		m_angleTarget = angle;
		
		m_pidLoop = new SynchronousPIDF(
			RobotMap.ClosedLoop.TURNING_P,
			RobotMap.ClosedLoop.TURNING_I,
			RobotMap.ClosedLoop.TURNING_D);
		
		m_timer = new Timer();
		m_printLooper = new DebugPrintLooper();
	}

	/**
	 * Set the internal PID constants to new values
	 * @param p  P gain
	 * @param i  I gain
	 * @param d  D gain
	 */
	public void setPID(double p, double i, double d) {
		m_pidLoop.setPID(p, i, d);
	}
	
	/**
	 * Sets whether Command ends itself after reaching the setpoint
	 * @param stops  True if you want command to end; false if not
	 */
	public void willStopAtSetpoint(boolean stops) {
		m_stopsAtSetpoint = stops;
	}

	protected void initialize() {
		motionSensors.resetAllSensors();
		m_pidLoop.reset();
		m_pidLoop.setSetpoint(m_angleTarget);
		m_timer.reset();
		m_timer.start();
	}
	
	protected void execute() {
		m_printLooper.println(motionSensors.debugAllSensors());

		m_output = m_pidLoop.calculate(motionSensors.getNavxAngle(), m_timer.get());
		drivetrain.arcadeDriveAutonomo(0, m_output);
	}

	protected boolean isFinished() {
		if (m_pidLoop.onTarget(0.5) && m_stopsAtSetpoint) {
			return true;
		} else {
			return false;
		}
	}
	
	protected void end() {
		motionSensors.resetAllSensors();
		m_timer.stop();
		m_pidLoop.reset();
		drivetrain.stopDrive();
	}
}