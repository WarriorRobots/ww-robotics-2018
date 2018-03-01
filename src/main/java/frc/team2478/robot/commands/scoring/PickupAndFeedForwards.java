package frc.team2478.robot.commands.scoring;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Constants;
import frc.team2478.robot.Robot;

public class PickupAndFeedForwards extends Command {

	public PickupAndFeedForwards() {
		requires(Robot.feed);
		requires(Robot.pickup);
	}
	
	@Override
	protected void execute() {
		Robot.feed.runAtPercentage(Constants.ShooterRig.FEED_PERCENT_SPEED);
		Robot.pickup.runAtPercentage(Constants.ShooterRig.PICKUP_PERCENT_SPEED);
	}
	
	@Override
	protected void end() {
		Robot.feed.stop();
		Robot.pickup.stop();
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
