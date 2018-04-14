package frc.team2478.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Constants;
import frc.team2478.robot.Robot;
import frc.team2478.robot.util.SynchronousPIDF;

/**
 * Identical to {@link TurnAuto}, but the gyroscope is not reset before running.
 * This allows returning to a previously-measured angle.
 */
public class TurnAutoNoReset extends Command {
	
	private double angleTarget, output;
	
	private boolean stopsAtSetpoint = true;
	
	private SynchronousPIDF pidLoop;
	private Timer timer;
	
	/**
	 * Create a new instance of {@link TurnAutoNoReset}.
	 * @param angle  What angle in degrees to turn towards.
	 */
	public TurnAutoNoReset(double angle) {
		requires(Robot.drivetrain);

		angleTarget = angle;
		
		pidLoop = new SynchronousPIDF(
			Constants.AutonomoDrive.TURNING_P,
			Constants.AutonomoDrive.TURNING_I,
			Constants.AutonomoDrive.TURNING_D);
		
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

	@Override
	protected void initialize() {
//		Robot.drivetrain.resetEncoders();
		pidLoop.setIzone(-0.2, 0.2);
		pidLoop.setSetpoint(angleTarget);
		timer.start();
	}
	
	@Override
	protected void execute() {
		output = pidLoop.calculate(Robot.drivetrain.getAngle(), timer.get());
		Robot.drivetrain.arcadeDriveRaw(0, output);
	}

	@Override
	protected boolean isFinished() {
		if (pidLoop.onTarget(Constants.AutonomoDrive.TURNING_TOLERANCE) && stopsAtSetpoint) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	protected void end() {
//		Robot.drivetrain.resetEncoders();
		timer.stop();
		pidLoop.reset();
		Robot.drivetrain.stopDrive();
	}
}