package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoSwitch extends CommandGroup {
	
	// put dashboard there
	public AutoSwitch(double dist1, double turn1, double dist1, double turn2, double dist3) {
		addSequential(new AutonomoDriveStraight(dist1));
		addSequential(new AutonomoDriveTurn(turn1));
		addSequential(new AutonomoDriveStraight(dist2));
		addSequential(new AutonomoDriveTurn(turn2));
		addSequential(new AutonomoDriveStraight(dist3))

		//transit Cube
		//Shoot cube
	}

}