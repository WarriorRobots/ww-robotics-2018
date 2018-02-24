package frc.team2478.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2478.robot.commands.autonomous.AutonomoDriveStraight;

public class TestAutonomo extends CommandGroup{

	public TestAutonomo() {
		addSequential(new AutonomoDriveStraight(600));
	}
	
}
