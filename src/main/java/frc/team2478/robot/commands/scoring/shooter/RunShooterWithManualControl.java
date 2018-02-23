package frc.team2478.robot.commands.scoring.shooter;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;

public class RunShooterWithManualControl extends Command{
	
	private double percentage;
	
	/**
	 * Runs the shooter using the left joystick of the xbox controller.
	 */
	public RunShooterWithManualControl() {
		requires(Robot.shooter);
	}

	@Override
	protected void execute() {
		percentage = Robot.oi.getXboxLeftY();
		if(Math.abs(percentage) > 0.1) {
			Robot.shooter.setTargetPercentage(Robot.oi.getXboxLeftY());
		} else {
			Robot.shooter.stop();
		}
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		Robot.shooter.stop();
	}
	
	@Override
	protected void interrupted() {
		this.end();
	}

}
