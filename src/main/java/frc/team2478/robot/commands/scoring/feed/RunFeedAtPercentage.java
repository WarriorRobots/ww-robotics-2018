package frc.team2478.robot.commands.scoring.feed;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;

public class RunFeedAtPercentage extends Command {

private double percentage;
	
	public RunFeedAtPercentage(double percentage) {
		requires(Robot.feed);
		this.percentage = percentage;
	}

	@Override
	protected void execute() {
		Robot.feed.runMotorAtPercentage(percentage);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
}
