package frc.team2478.robot.commands.scoring;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;

public class RunShooterWithJoy extends Command{
	
	private double percentage;
	
	/**
	 * Runs the shooter defaultly and off of the xbox right joystick.
	 */
	public RunShooterWithJoy() {
		requires(Robot.shooter);
	}

	@Override
	protected void execute() {
		percentage = Robot.oi.getXboxRightY();
		if(Math.abs(percentage) > 0.3) {
			Robot.shooter.setTargetPercentage(Robot.oi.getXboxRightY());
		} else {
			Robot.shooter.stop();
		}
//		Robot.shooter.setTargetPercentage(0.3);
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
