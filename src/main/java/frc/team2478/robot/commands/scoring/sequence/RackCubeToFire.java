package frc.team2478.robot.commands.scoring.sequence;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team2478.robot.Constants;
import frc.team2478.robot.Robot;
import frc.team2478.robot.commands.scoring.StopAllScoringMotors;
import frc.team2478.robot.commands.scoring.feed.RunFeedAtPercentage;
import frc.team2478.robot.commands.scoring.shooter.RunShooterAtPercentage;

/**
 * Step 3 of preparing to fire a cube
 */
public class RackCubeToFire extends CommandGroup {

	public RackCubeToFire() {
//		addParallel(new RunShooterAtPercentage(-Constants.ShooterRig.SHOOTER_PERCENT_SPEED));
		addParallel(new DetectCube(new RunShooterAtPercentage(-Constants.ShooterRig.SHOOTER_PERCENT_SPEED),
					new RunShooterAtPercentage(-Constants.ShooterRig.SHOOTER_PERCENT_SPEED*0.5)));
		addParallel(new RunFeedAtPercentage(-Constants.ShooterRig.FEED_PERCENT_SPEED));
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.feed.isCubeLoaded();
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
