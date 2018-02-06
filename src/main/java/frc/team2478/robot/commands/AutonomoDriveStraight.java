package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2478.robot.Constants;
import frc.team2478.robot.interfaces.DoubleEncoderInterface;
import frc.team2478.robot.interfaces.DoubleEncoderInterface.Side;
import frc.team2478.robot.interfaces.DriveInterface;
import frc.team2478.robot.interfaces.GyroscopeInterface;
import frc.team2478.robot.util.SynchronousPIDF;

/**
 * When run, the robot will drive straight at the provided distance,
 * using a PID loop to stay on-course.
 */
public class AutonomoDriveStraight extends Command {
	
	private DriveInterface m_drivetrain;
	private DoubleEncoderInterface m_encoders;
	private GyroscopeInterface m_gyro;
	
	private double m_distanceTarget, m_leftCount, m_rightCount;
	private double m_avgDistance, m_angleOutput, m_distanceOutput;
	private boolean m_stopsAtSetpoint = true; // @debug

	private SynchronousPIDF m_pidAngle, m_pidDistance;
	private Timer m_timer;
	
	/**
	 * Create a new instance of {@link AutonomoDriveStraight}.
	 * @param distance How many encoder clicks to travel.
	 */
	public AutonomoDriveStraight(DriveInterface drivetrain, DoubleEncoderInterface encoders, GyroscopeInterface gyro,
								 double distance) {
		m_drivetrain = drivetrain;
		m_encoders = encoders;
		m_gyro = gyro;
		requires((Subsystem) m_drivetrain);
		requires((Subsystem) m_encoders);
		requires((Subsystem) m_gyro);
		
		m_distanceTarget = distance;

		m_pidAngle = new SynchronousPIDF( // default vals
			Constants.ClosedLoop.TURNING_P,
			Constants.ClosedLoop.COURSECORRECTION_I,
			Constants.ClosedLoop.TURNING_D);
		m_pidDistance = new SynchronousPIDF(
			Constants.ClosedLoop.DISTANCE_P,
			Constants.ClosedLoop.DISTANCE_I,
			Constants.ClosedLoop.DISTANCE_D);

		m_timer = new Timer();
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
		m_encoders.resetEncoders();
		m_pidDistance.reset();
		m_pidDistance.setSetpoint(m_distanceTarget); // needs an iZone
		m_pidAngle.reset();
		m_pidAngle.setSetpoint(0);
		m_timer.reset();
		m_timer.start();
	}
	
	protected void execute() {
		m_leftCount = m_encoders.getEncoderTicks(Side.LEFT);
		m_rightCount = m_encoders.getEncoderTicks(Side.RIGHT);
		m_encoders.printEncoderData();
		
		m_avgDistance = (m_leftCount + m_rightCount) / 2;
		m_angleOutput = m_pidAngle.calculate(m_gyro.getAngle(), m_timer.get());
		m_distanceOutput = m_pidDistance.calculate(m_avgDistance, m_timer.get());
		
		m_drivetrain.arcadeDriveRaw(m_distanceOutput, m_angleOutput);
	}

	protected boolean isFinished() {
		if (m_pidDistance.onTarget(0.5) && m_stopsAtSetpoint) {
			return true;
		} else {
			return false;
		}
	}
	
	protected void end() {
		m_drivetrain.stopDrive();
		m_encoders.resetEncoders();
		m_timer.stop();
		m_pidDistance.reset();
		m_pidAngle.reset();
	}
}