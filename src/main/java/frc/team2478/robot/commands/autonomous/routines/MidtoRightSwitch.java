package frc.team2478.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2478.robot.commands.autonomous.AutonomoDriveStraight;
import frc.team2478.robot.commands.autonomous.AutonomoDriveTurn;

public class MidtoRightSwitch extends CommandGroup{
	
	public MidtoRightSwitch() {
		addSequential(new AutonomoDriveStraight(30));
		addSequential(new AutonomoDriveTurn(90));
		addSequential(new AutonomoDriveStraight(50));
		addSequential(new AutonomoDriveTurn(-90));
	}

}