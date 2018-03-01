package frc.team2478.robot.commands.scoring.sequence;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team2478.robot.Constants;
import frc.team2478.robot.commands.pneumatics.OpenPickup;
import frc.team2478.robot.commands.scoring.StopAllMotors;
import frc.team2478.robot.commands.scoring.feed.RunFeedAtPercentage;
import frc.team2478.robot.commands.scoring.pickup.RunPickupAtPercentage;
import frc.team2478.robot.commands.scoring.shooter.RunShooterAtPercentage;

public class EjectCube extends CommandGroup {
	
	public EjectCube() {
		addParallel(new OpenPickup());
		addParallel(new RunShooterAtPercentage(-Constants.ShooterRig.SHOOTER_PERCENT_SPEED));
		addParallel(new RunPickupAtPercentage(-Constants.ShooterRig.PICKUP_PERCENT_SPEED));
		addParallel(new RunFeedAtPercentage(-Constants.ShooterRig.FEED_PERCENT_SPEED));
	}
	
	@Override
	protected void end() {
		Scheduler.getInstance().add(new StopAllMotors());
	}
	
	@Override
	protected void interrupted() {
		this.end();
	}

}
