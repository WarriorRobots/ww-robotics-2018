package frc.team2478.robot.commands.scoring.sequence;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2478.robot.Constants;
import frc.team2478.robot.Robot;
import frc.team2478.robot.commands.scoring.feed.RunFeedAtPercentage;
import frc.team2478.robot.commands.scoring.pickup.RunPickupAtPercentage;

/**
 * Step 2 of preparing to fire a cube
 * @author avuong0922
 *
 */
public class LoadCubeToFire extends CommandGroup {

	public LoadCubeToFire() {
		addParallel(new RunPickupAtPercentage(Constants.ShooterRig.PICKUP_PERCENT_SPEED));
		addParallel(new RunFeedAtPercentage(Constants.ShooterRig.FEED_PERCENT_SPEED));
	}
	
	@Override
	protected boolean isFinished() {
		return !Robot.feed.isCubeLoaded();
	}
	
	@Override
	protected void end() {
		Robot.shooter.stop();
		Robot.feed.stop();
	}
	
}
