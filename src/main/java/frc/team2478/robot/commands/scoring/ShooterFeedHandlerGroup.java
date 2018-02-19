package frc.team2478.robot.commands.scoring;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ShooterFeedHandlerGroup extends CommandGroup {
	
	public ShooterFeedHandlerGroup() {
		addParallel(new RunShooter());
		addSequential(new WaitCommand(1.0)); // 1 second
		addSequential(new RunFeed());
	}
}