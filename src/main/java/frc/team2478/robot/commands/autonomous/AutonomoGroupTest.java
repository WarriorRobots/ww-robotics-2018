package frc.team2478.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomoGroupTest extends CommandGroup {
	
	public AutonomoGroupTest() {
		addSequential(new AutonomoDriveStraight(800));
		addSequential(new AutonomoDriveTurn(90));
		addSequential(new AutonomoDriveStraight(600));
		addSequential(new AutonomoDriveTurn(90));
		addSequential(new AutonomoDriveStraight(-300));
		addSequential(new AutonomoDriveTurn(90));
	}

}