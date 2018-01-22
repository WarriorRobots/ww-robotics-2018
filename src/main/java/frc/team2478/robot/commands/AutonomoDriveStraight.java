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
	private SynchronousPIDF m_pid;
	private Timer m_timer;
	private DebugPrintLooper m_printLooper;
	
	/**
	 * Create a new instance of {@link AutonomoDriveStraight}.
	 * @param distance How many encoder clicks to travel.
	 * <b>TO-DO: Calculate encoder clicks per feet.</b>
	 */
	public AutonomoDriveStraight(double distance) {
		requires(drivetrain);
		requires(motionSensors);
		m_pid = new SynchronousPIDF(RobotMap.ClosedLoop.TURNING_P,
									RobotMap.ClosedLoop.COURSECORRECTION_I,
									RobotMap.ClosedLoop.TURNING_D);
		m_timer = new Timer();
		m_distanceTarget = distance;
		m_printLooper = new DebugPrintLooper();
	}
	
	protected void initialize() {
		super.initialize();
		m_pid.reset();
		m_pid.setSetpoint(0);
		m_timer.reset();
		m_timer.start();
	}
	
	protected void execute() {
		m_output = m_pid.calculate(motionSensors.getNavxAngle(), m_timer.get());
		drivetrain.arcadeDriveAutonomo(RobotMap.DriveScalars.AUTO_SPEED_FORWARDS, m_output);
		m_leftCount = motionSensors.getLeftEncCount();
		m_rightCount = motionSensors.getRightEncCount();
		m_printLooper.println(Double.toString(m_leftCount) + " " + Double.toString(m_rightCount));
	}

	protected boolean isFinished() {
		if (m_leftCount > m_distanceTarget && m_rightCount > m_distanceTarget) {
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
	
	protected void interrupted() {
		DriverStation.reportWarning("AutonomoDriveStraight interrupted", false);
    	this.end();
    }
}