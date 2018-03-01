package frc.team2478.robot.commands.scoring;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2478.robot.commands.scoring.feed.StopFeed;
import frc.team2478.robot.commands.scoring.intake.StopIntake;
import frc.team2478.robot.commands.scoring.shooter.StopShooter;

public class StopAllScoringMotors extends CommandGroup {

	public StopAllScoringMotors() {
		addParallel(new StopIntake());
		addParallel(new StopFeed());
		addParallel(new StopShooter());
	}

}
