package frc.team2478.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2478.robot.Constants;
import frc.team2478.robot.Robot;
import frc.team2478.robot.util.SynchronousPIDF;

/**
 * When run, the robot will turn to the provided angle,
 * using a PID loop to maintain accuracy and control.
 */
public class TurnAuto extends Command {
	
	private double angleTarget, output;
	
	private boolean stopsAtSetpoint = true;
	
	private SynchronousPIDF pidLoop;
	private Timer timer;
	
	/**
	 * Create a new instance of {@link TurnAuto}.
	 * @param angle  What angle in degrees to turn towards.
	 */
	public TurnAuto(double angle) {
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
		Robot.drivetrain.resetEncoders();
		Robot.drivetrain.resetAngle();
		pidLoop.setIzone(-0.2, 0.2);
		pidLoop.setOutputRange(-0.8, 0.8);
		pidLoop.setSetpoint(angleTarget);
		timer.start();
	}
	
	@Override
	protected void execute() {
		output = pidLoop.calculate(Robot.drivetrain.getAngle(), timer.get());
		SmartDashboard.putNumber("pid gain", output);
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
		Robot.drivetrain.resetEncoders();
		timer.stop();
		pidLoop.reset();
		Robot.drivetrain.stopDrive();
	}
}