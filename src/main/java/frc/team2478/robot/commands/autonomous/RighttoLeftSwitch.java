package frc.team2478.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2478.robot.commands.AutonomoDriveStraight;
import frc.team2478.robot.commands.AutonomoDriveTurn;

public class RighttoLeftSwitch extends CommandGroup{
	
	public RighttoLeftSwitch() {
		addSequential(new AutonomoDriveStraight(30));
		addSequential(new AutonomoDriveTurn(90));
		addSequential(new AutonomoDriveStraight(100));
		addSequential(new AutonomoDriveTurn(90));
	}

}