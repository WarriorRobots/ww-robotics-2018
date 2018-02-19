package frc.team2478.robot.commands.scoring;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.team2478.robot.Robot;
import frc.team2478.robot.util.annotations.Debug;

public class ShooterFeedGroup extends CommandGroup {
	
	@Debug
	public ShooterFeedGroup() {
//		addParallel(new RunShooter());	
		addParallel(new RunShooterWithPercentage(0.5));
		addSequential(new WaitCommand(1.0));
		addSequential(new RunFeed());
	}

	@Override
	protected void end() {
		Robot.shooter.stop();
		Robot.feed.stop();
	}
}