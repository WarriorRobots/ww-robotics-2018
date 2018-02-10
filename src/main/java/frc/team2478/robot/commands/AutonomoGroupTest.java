package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomoGroupTest extends CommandGroup {
	
	public AutonomoGroupTest() {
		addSequential(new AutonomoDriveStraight(600));
		addSequential(new AutonomoDriveTurn(90));
		addSequential(new AutonomoDriveStraight(600));
		addSequential(new AutonomoDriveTurn(-90));
		addSequential(new AutonomoDriveStraight(200));
		addSequential(new AutonomoDriveTurn(-90));
		addSequential(new AutonomoDriveTurn(90));
		addSequential(new AutonomoDriveStraight(-200));
		addSequential(new AutonomoDriveTurn(90));
		addSequential(new AutonomoDriveStraight(-600));
		addSequential(new AutonomoDriveTurn(-90));
		addSequential(new AutonomoDriveStraight(-600));
	}

}