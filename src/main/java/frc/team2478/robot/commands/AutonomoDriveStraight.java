package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import frc.team2478.robot.RobotMap;
import frc.team2478.robot.util.DebugPrintLooper;
import frc.team2478.robot.util.SynchronousPIDF;

/**
 * When run, the robot will drive straight at the provided distance,
 * using a PID loop to stay on-course.
 */
public class AutonomoDriveStraight extends CommandBase {
	
	private double m_distanceTarget, m_leftCount, m_rightCount;
	private double m_avgDistance, m_angleOutput, m_distanceOutput;
	private boolean m_stopsAtSetpoint = true; // @debug
	private SynchronousPIDF m_pidAngle, m_pidDistance;
	private Timer m_timer;
	private DebugPrintLooper m_printLooper;
	
	/**
	 * Create a new instance of {@link AutonomoDriveStraight}.
	 * @param distance How many encoder clicks to travel.
	 */
	public AutonomoDriveStraight(double distance) {
		requires(drivetrain);
		requires(motionSensors);
		
		m_distanceTarget = distance;

		m_pidAngle = new SynchronousPIDF( // default vals
			RobotMap.ClosedLoop.TURNING_P,
			RobotMap.ClosedLoop.COURSECORRECTION_I,
			RobotMap.ClosedLoop.TURNING_D);
		m_pidDistance = new SynchronousPIDF(0.01, 0, 0);

		m_timer = new Timer();
		m_printLooper = new DebugPrintLooper();
	}
	
	/**
	 * Set the internal angular PID constants to new values
	 * @param p  P gain
	 * @param i  I gain
	 * @param d  D gain
	 */
	public void setAngularPid(double p, double i, double d) {
		m_pidAngle.setPID(p, i, d);
	}
	
	/**
	 * Set the internal distance PID constants to new values
	 * @param p  P gain
	 * @param i  I gain
	 * @param d  D gain
	 */
	public void setDistancePid(double p, double i, double d) {
		m_pidDistance.setPID(p, i, d);
	}
	
	/**
	 * Sets whether Command ends itself after reaching the setpoint.
	 * <p>Used for debugging, if you want to tune a PID loop.
	 * @param stops  True if you want command to end; false if not
	 */
	public void willStopAtSetpoint(boolean stops) {
		m_stopsAtSetpoint = stops;
	}

	protected void initialize() {
		motionSensors.resetAllSensors();
		m_pidDistance.reset();
		m_pidDistance.setSetpoint(m_distanceTarget); // needs an iZone
		m_pidAngle.reset();
		m_pidAngle.setSetpoint(0);
		m_timer.reset();
		m_timer.start();
	}
	
	protected void execute() {
		m_leftCount = motionSensors.getLeftEncCount();
		m_rightCount = motionSensors.getRightEncCount();
		m_printLooper.println(motionSensors.debugAllSensors());
		
		m_avgDistance = (m_leftCount + m_rightCount) / 2;
		m_angleOutput = m_pidAngle.calculate(motionSensors.getNavxAngle(), m_timer.get());
		m_distanceOutput = m_pidDistance.calculate(m_avgDistance, m_timer.get());
		
		drivetrain.arcadeDriveAutonomo(m_distanceOutput, m_angleOutput);
	}

	protected boolean isFinished() {
		if (m_pidDistance.onTarget(0.5) && m_stopsAtSetpoint) {
			return true;
		} else {
			return false;
		}
	}
	
	protected void end() {
		motionSensors.resetAllSensors();
		m_timer.stop();
		m_pidDistance.reset();
		m_pidAngle.reset();
		drivetrain.stopDrive();
	}
}