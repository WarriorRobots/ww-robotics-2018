package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2478.robot.RobotMap;
import frc.team2478.robot.interfaces.DoubleEncoderInterface;
import frc.team2478.robot.interfaces.DriveInterface;
import frc.team2478.robot.interfaces.GyroscopeInterface;
import frc.team2478.robot.util.SynchronousPIDF;

/**
 * When run, the robot will turn to the provided angle,
 * using a PID loop to maintain accuracy and control.
 */
public class AutonomoDriveTurn extends Command {
	
	private DriveInterface m_drivetrain;
	private DoubleEncoderInterface m_encoders;
	private GyroscopeInterface m_gyro;
	
	private double m_angleTarget, m_output;
	private boolean m_stopsAtSetpoint = true; // @debug variable
	private SynchronousPIDF m_pidLoop;
	private Timer m_timer;
	
	/**
	 * Create a new instance of {@link AutonomoDriveStraight}.
	 * @param angle  What angle in degrees to turn towards.
	 */
	public AutonomoDriveTurn(DriveInterface drivetrain, DoubleEncoderInterface encoders, GyroscopeInterface gyro,
							 double angle) {
		m_drivetrain = drivetrain;
		m_encoders = encoders;
		m_gyro = gyro;
		requires((Subsystem) m_drivetrain);
		requires((Subsystem) m_encoders);
		requires((Subsystem) m_gyro);
		
		m_angleTarget = angle;
		
		m_pidLoop = new SynchronousPIDF(
			RobotMap.ClosedLoop.TURNING_P,
			RobotMap.ClosedLoop.TURNING_I,
			RobotMap.ClosedLoop.TURNING_D);
		
		m_timer = new Timer();
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
		m_encoders.resetEncoders();
		m_pidLoop.reset();
		m_pidLoop.setSetpoint(m_angleTarget);
		m_timer.reset();
		m_timer.start();
	}
	
	protected void execute() {
		m_encoders.printEncoderData();
		m_output = m_pidLoop.calculate(m_gyro.getAngle(), m_timer.get());
		m_drivetrain.arcadeDriveRaw(0, m_output);
	}

	protected boolean isFinished() {
		if (m_pidLoop.onTarget(0.5) && m_stopsAtSetpoint) {
			return true;
		} else {
			return false;
		}
	}
	
	protected void end() {
		m_encoders.resetEncoders();
		m_timer.stop();
		m_pidLoop.reset();
		m_drivetrain.stopDrive();
	}
}