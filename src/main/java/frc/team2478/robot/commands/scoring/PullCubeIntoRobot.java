package frc.team2478.robot.commands.scoring;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2478.robot.Constants;
import frc.team2478.robot.Robot;
import frc.team2478.robot.commands.pneumatics.ClosePickup;
import frc.team2478.robot.commands.scoring.feed.RunFeedAtPercentage;
import frc.team2478.robot.commands.scoring.pickup.RunPickupAtPercentage;

public class PullCubeIntoRobot extends CommandGroup {

	public PullCubeIntoRobot() {
		addParallel(new RunPickupAtPercentage(Constants.ShooterRig.PICKUP_PERCENT_SPEED));
		addParallel(new RunFeedAtPercentage(Constants.ShooterRig.FEED_PERCENT_SPEED));
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.feed.isCubeLoaded();
	}
	
	@Override
	protected void end() {
		new ClosePickup().start();
		Robot.pickup.stop();
		Robot.feed.stop();
	}
	
}
