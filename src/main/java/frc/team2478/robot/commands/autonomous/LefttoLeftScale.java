package frc.team2478.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2478.robot.commands.AutonomoDriveStraight;

public class LefttoLeftScale extends CommandGroup{
	
	public LefttoLeftScale() {
		addSequential(new AutonomoDriveStraight(300));
	}

}