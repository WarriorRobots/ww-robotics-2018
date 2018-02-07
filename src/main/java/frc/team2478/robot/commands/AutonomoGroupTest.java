package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomoGroupTest extends CommandGroup {
	
	public AutonomoGroupTest(int dist, double turn) {
		addSequential(new AutonomoDriveStraight(dist));
		addSequential(new AutonomoDriveTurn(turn));
	}

}