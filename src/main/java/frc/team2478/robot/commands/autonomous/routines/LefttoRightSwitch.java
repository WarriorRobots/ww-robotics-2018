package frc.team2478.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2478.robot.commands.autonomous.AutonomoDriveStraight;
import frc.team2478.robot.commands.autonomous.AutonomoDriveTurn;

public class LefttoRightSwitch extends CommandGroup{
	
	public LefttoRightSwitch() {
		addSequential(new AutonomoDriveStraight(30));
		addSequential(new AutonomoDriveTurn(90));
		addSequential(new AutonomoDriveStraight(100));
		addSequential(new AutonomoDriveTurn(-90));
	}

}