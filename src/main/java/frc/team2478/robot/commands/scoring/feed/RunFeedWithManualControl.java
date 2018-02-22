package frc.team2478.robot.commands.scoring.feed;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;

public class RunFeedWithManualControl extends Command {
		
	private double percentage;
	
	/**
	 * Runs the feed using the right joystick of the xbox controller.
	 */
	public RunFeedWithManualControl() {
		requires(Robot.feed);
	}
	
	@Override
	protected void execute() {
		percentage = Robot.oi.getXboxRightY();
		if(Math.abs(percentage) > 0.3) {
			Robot.feed.setTargetPercentage(Robot.oi.getXboxRightY());
		} else {
			Robot.feed.stop();
		}
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		Robot.feed.stop();
	}
	
	@Override
	protected void interrupted() {
		this.end();
	}
}
