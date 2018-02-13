package frc.team2478.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2478.robot.commands.AutonomoDriveStraight;

public class RighttoRightSwitch extends CommandGroup{
	
	public RighttoRightSwitch() {
		addSequential(new AutonomoDriveStraight(300));
	}

}
