package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomoGroupTest extends CommandGroup {
	
	// put dashboard there  vv
	public AutonomoGroupTest() {
		addSequential(new AutonomoDriveStraight(500));
		addSequential(new AutonomoDriveTurn(-90));
	}
}