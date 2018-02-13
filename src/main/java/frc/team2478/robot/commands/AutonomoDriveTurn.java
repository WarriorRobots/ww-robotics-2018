package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Constants;
import frc.team2478.robot.Robot;
import frc.team2478.robot.util.SynchronousPIDF;

/**
 * When run, the robot will turn to the provided angle,
 * using a PID loop to maintain accuracy and control.
 */
public class AutonomoDriveTurn extends Command {
	
	private double angleTarget, output;
	private boolean stopsAtSetpoint = true; // @debug variable
	private SynchronousPIDF pidLoop;
	private Timer timer;
	
	/**
	 * Create a new instance of {@link AutonomoDriveTurn}.
	 * @param angle  What angle in degrees to turn towards.
	 */
	public AutonomoDriveTurn(double angle) {
		requires(Robot.drivetrain);

		angleTarget = angle;
		
		pidLoop = new SynchronousPIDF(
			Constants.ClosedLoop.TURNING_P,
			Constants.ClosedLoop.TURNING_I,
			Constants.ClosedLoop.TURNING_D);
		
		timer = new Timer();
		System.out.println("angleTarget " + Double.toString(this.angleTarget));
	}

	/**
	 * Set the internal PID constants to new values
	 * @param p  P gain
	 * @param i  I gain
	 * @param d  D gain
	 */
	public void setPID(double p, double i, double d) {
		pidLoop.setPID(p, i, d);
	}
	
	/**
	 * Sets whether Command ends itself after reaching the setpoint
	 * @param stops  True if you want command to end; false if not
	 */
	public void willStopAtSetpoint(boolean stops) {
		stopsAtSetpoint = stops;
	}

	protected void initialize() {
		Robot.drivetrain.resetEncoders();
		Robot.drivetrain.resetAngle();
		pidLoop.setIzone(-0.2, 0.2);
		pidLoop.setSetpoint(angleTarget);
		timer.start();
	}
	
	protected void execute() {
		System.out.println(Robot.drivetrain.getAngle());
		output = pidLoop.calculate(Robot.drivetrain.getAngle(), timer.get());
		Robot.drivetrain.arcadeDriveRaw(0, output);
	}

	protected boolean isFinished() {
		if (pidLoop.onTarget(Constants.ClosedLoop.TURNING_TOLERANCE) && stopsAtSetpoint) {
//		if (stopsAtSetpoint) {
			return true;
		} else {
			return false;
		}
	}
	
	protected void end() {
		Robot.drivetrain.resetEncoders();
		timer.stop();
		pidLoop.reset();
		Robot.drivetrain.stopDrive();
	}
}