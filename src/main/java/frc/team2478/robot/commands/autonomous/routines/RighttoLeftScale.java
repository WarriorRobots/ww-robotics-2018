package frc.team2478.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2478.robot.commands.autonomous.AutonomoDriveStraight;

public class RighttoLeftScale extends CommandGroup{
	
	public RighttoLeftScale() {
		addSequential(new AutonomoDriveStraight(300));
//		addSequential(new AutonomoDriveTurn(90));
//		addSequential(new AutonomoDriveStraight(300));
//		addSequential(new AutonomoDriveTurn(-90));
//		addSequential(new AutonomoDriveStraight(200));
//		addSequential(new AutonomoDriveTurn(45));
//		addSequential(new AutonomoDriveTurn(-45));
//		addSequential(new AutonomoDriveStraight(-200));
	}

}