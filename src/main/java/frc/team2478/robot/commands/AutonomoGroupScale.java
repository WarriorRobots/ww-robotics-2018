package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomoGroupScale extends CommandGroup {
	
		//put dashboard there
	public AutonomoGroupScale(double dist1, double turn1, double dist2, double turn2, double dist3) {
		addSequential(new AutonomoDriveStraight(dist1));
		addSequential(new AutonomoDriveTurn(turn1));
		//transit
		//shoot

		addSeqential(new AutonomoDriveTurn((100-turn1));//opposite of orig turn
		//turn on camera mode
		//run pickup

		//if statement condition(when cube pickup))
		//then reverse and shoot
	}

}

