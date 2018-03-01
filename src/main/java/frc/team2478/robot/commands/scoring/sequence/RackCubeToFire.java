package frc.team2478.robot.commands.scoring.sequence;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2478.robot.Constants;
import frc.team2478.robot.Robot;
import frc.team2478.robot.commands.scoring.feed.RunFeedAtPercentage;
import frc.team2478.robot.commands.scoring.pickup.RunPickupAtPercentage;
import frc.team2478.robot.commands.scoring.shooter.RunShooterAtPercentage;

/**
 * Step 3 of preparing to fire a cube
 */
public class RackCubeToFire extends CommandGroup {

	public RackCubeToFire() {
		addParallel(new RunShooterAtPercentage(-Constants.ShooterRig.SHOOTER_PERCENT_SPEED));
		addParallel(new RunFeedAtPercentage(-Constants.ShooterRig.FEED_PERCENT_SPEED));
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.feed.isCubeLoaded();
	}
	
	@Override
	protected void end() {
		Robot.shooter.stop();
		Robot.pickup.stop();
		Robot.feed.stop();
	}
	
	@Override
	protected void interrupted() {
		this.end();
	}

}
