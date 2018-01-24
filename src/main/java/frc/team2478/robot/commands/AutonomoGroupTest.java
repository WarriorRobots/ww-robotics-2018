package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomoGroupTest extends CommandGroup {
	
	// put dashboard there  vv
	public AutonomoGroupTest(double dist1, double turn1) {
		addSequential(new AutonomoDriveStraight(dist1));
		addSequential(new AutonomoDriveTurn(turn1));
	}
}