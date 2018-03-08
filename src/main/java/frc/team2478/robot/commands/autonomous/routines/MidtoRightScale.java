package frc.team2478.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2478.robot.commands.autonomous.AutonomoDriveStraight;
import frc.team2478.robot.commands.autonomous.AutonomoDriveTurn;

public class MidtoRightScale extends CommandGroup{
	
	public MidtoRightScale() {
		addSequential(new AutonomoDriveStraight(30));
		addSequential(new AutonomoDriveTurn(90));
		addSequential(new AutonomoDriveStraight(200));
		addSequential(new AutonomoDriveTurn(-90));
		addSequential(new AutonomoDriveStraight(300));
		addSequential(new AutonomoDriveTurn(-45));
	}

}