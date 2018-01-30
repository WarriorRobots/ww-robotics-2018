package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import frc.team2478.robot.RobotMap;
import frc.team2478.robot.util.DebugPrintLooper;
import frc.team2478.robot.util.SynchronousPIDF;

/**
 * When run, the robot will drive straight at the provided distance,
 * using a PID loop to stay on-course.
 */
public class AutonomoDriveStraight extends AutonomoBase {
	
	private double m_distanceTarget, m_output, m_leftCount, m_rightCount;
	private SynchronousPIDF m_pidAngle;
	private Timer m_timer;
	private DebugPrintLooper m_printLooper;
	private boolean m_stopAtSetpoint = true; // @debug
	
	/**
	 * Create a new instance of {@link AutonomoDriveStraight}.
	 * @param distance How many encoder clicks to travel.
	 * <b>TO-DO: Calculate encoder clicks per feet.</b>
	 */
	public AutonomoDriveStraight(double distance) {
		requires(drivetrain);
		requires(motionSensors);
		m_pidAngle = new SynchronousPIDF(RobotMap.ClosedLoop.TURNING_P,
									RobotMap.ClosedLoop.COURSECORRECTION_I,
									RobotMap.ClosedLoop.TURNING_D);
		m_timer = new Timer();
		m_distanceTarget = distance;
		m_printLooper = new DebugPrintLooper();
	}
	
	/**
	 * Set the internal PID constants to new values
	 * @param p  P gain
	 * @param i  I gain
	 * @param d  D gain
	 */
	public void setPID(double p, double i, double d) {
		m_pidAngle.setPID(p, i, d);
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
		m_pidAngle.reset();
		m_pidAngle.setSetpoint(0);
		m_timer.reset();
		m_timer.start();
	}
	
	protected void execute() {
		m_output = m_pidAngle.calculate(motionSensors.getNavxAngle(), m_timer.get());
		
		if (m_distanceTarget > 0) {
			drivetrain.arcadeDriveAutonomo(RobotMap.DriveScalars.AUTO_SPEED_FORWARDS, m_output);
		} else if (m_distanceTarget < 0) {
			drivetrain.arcadeDriveAutonomo(-RobotMap.DriveScalars.AUTO_SPEED_FORWARDS, m_output);
		} else {
			this.interrupted();
		}
		
		m_leftCount = motionSensors.getLeftEncCount();
		m_rightCount = motionSensors.getRightEncCount();
		m_printLooper.println(Double.toString(m_leftCount) + " " + Double.toString(m_rightCount));
	}

	protected boolean isFinished() {
		if ((Math.abs(m_leftCount) > Math.abs(m_distanceTarget) && Math.abs(m_rightCount) > Math.abs(m_distanceTarget)) &&
				m_stopAtSetpoint) {
			return true;
		} else {
			return false;
		}
	}
	
	protected void end() {
		super.end();
		m_timer.stop();
		m_pidAngle.reset();
	}
	
	protected void interrupted() {
		DriverStation.reportWarning("AutonomoDriveStraight interrupted", false);
    	this.end();
    }
}