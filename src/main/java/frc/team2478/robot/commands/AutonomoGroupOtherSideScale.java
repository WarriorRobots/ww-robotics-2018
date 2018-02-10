package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomoGroupOtherSideScale extends CommandGroup {
	
		//put dashboard there
	public AutonomoGroupOtherSideScale(double dist1, double turn1, double dist2, double turn2, double dist3) {

		addSequential(new AutonomoDriveStraight(dist1));
		addSequential(new AutonomoDriveTurn(turn1));
		addSequential(new AutonomoDriveStraight(dist2));
		addSequential(new AutonomoDriveTurn(turn2));
		addSequential(new AutonomoDriveStraight(dist3));
		addSequential(new AutonomoDriveTurn(turn3));

		//transit
		//shoot

		addSeqential(new AutonomoDriveTurn(-turn1));//opposite of orig turn
		//turn on camera mode
		//run pickup

		//if statment condition(when cube pickup))
		//then reverse and shoot
	}

}

