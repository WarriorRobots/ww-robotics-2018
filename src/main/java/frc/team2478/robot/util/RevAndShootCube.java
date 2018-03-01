package frc.team2478.robot.util;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.team2478.robot.Constants;
import frc.team2478.robot.commands.scoring.feed.RunFeedAtPercentage;
import frc.team2478.robot.commands.scoring.shooter.RunShooterAtTargetSpeed;

public class RevAndShootCube extends CommandGroup {

	public RevAndShootCube() {
		addParallel(new RunShooterAtTargetSpeed());
		addSequential(new WaitCommand(0.5)); // needs to be short to preserve points (time-based game)
		addSequential(new RunFeedAtPercentage(Constants.ShooterRig.FEED_PERCENT_SPEED));
	}
	
}
