package frc.team2478.robot.commands.scoring.sequence;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team2478.robot.Constants;
import frc.team2478.robot.commands.scoring.StopAllScoringMotors;
import frc.team2478.robot.commands.scoring.feed.RunFeedAtPercentage;
import frc.team2478.robot.commands.scoring.pickup.RunPickupAtPercentage;

/**
 * Step 1 of preparing to fire a cube
 */
public class PickupCube extends CommandGroup {

	public PickupCube() {
		addParallel(new RunPickupAtPercentage(Constants.ShooterRig.PICKUP_PERCENT_SPEED));
		addParallel(new RunFeedAtPercentage(Constants.ShooterRig.FEED_PERCENT_SPEED));
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		Scheduler.getInstance().add(new StopAllScoringMotors());
	}
	
	@Override
	protected void interrupted() {
		this.end();
	}
	
}
