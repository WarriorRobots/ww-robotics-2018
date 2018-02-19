package frc.team2478.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2478.robot.commands.autonomous.AutonomoDriveStraight;

public class RighttoRightSwitch extends CommandGroup{
	
	public RighttoRightSwitch() {
		addSequential(new AutonomoDriveStraight(300));
	}

}