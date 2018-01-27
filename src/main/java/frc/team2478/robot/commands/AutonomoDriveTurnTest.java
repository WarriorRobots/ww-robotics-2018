package frc.team2478.robot.commands;

import frc.team2478.robot.util.DebugPrintLooper;
import frc.team2478.robot.util.SynchronousPIDF;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * When run, the robot will turn to the provided angle,
 * using a PID loop to maintain accuracy and control.
 */
public class AutonomoDriveTurnTest extends AutonomoBase {
	
	private double m_output;
	private SynchronousPIDF m_pid;
	private Timer m_timer;
	private DebugPrintLooper m_printLooper;
	
	/**
	 * Create a new instance of {@link AutonomoDriveStraight}.
	 * @param angle What angle in degrees to turn towards.
	 */
	public AutonomoDriveTurnTest() {
		requires(drivetrain);
		requires(motionSensors);
		m_pid = new SynchronousPIDF();
		m_timer = new Timer();
		m_printLooper = new DebugPrintLooper();
		SmartDashboard.putNumber("P val", 0);
		SmartDashboard.putNumber("I val", 0);
		SmartDashboard.putNumber("D val", 0);
		SmartDashboard.putNumber("SETPOINT", 0);
	}
	
	protected void initialize() {
		super.initialize();
		m_timer.start();
	}
	
	protected void execute() {
		m_pid.setSetpoint(SmartDashboard.getNumber("SETPOINT", 0));
		
		m_pid.setPID(SmartDashboard.getNumber("P val", 0),
					 SmartDashboard.getNumber("I val", 0),
					 SmartDashboard.getNumber("D val", 0));
		
		m_output = m_pid.calculate(motionSensors.getNavxAngle(), m_timer.get());
		
		m_printLooper.println(Double.toString(motionSensors.getNavxAngle()), 3);
		drivetrain.arcadeDriveAutonomo(0, m_output);
	}

	protected boolean isFinished() {
//		if (m_pid.onTarget(0.5)) {
//			return true;
//		} else {
			return false;
//		}
	}
	
	protected void end() {
		super.end();
		m_timer.stop();
		m_pid.reset();
	}
}