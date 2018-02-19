package frc.team2478.robot.commands.scoring;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;

public class RunFeed extends Command {

	private double percentage;
	
	public RunFeed() {
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
	
}
