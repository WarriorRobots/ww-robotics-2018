package frc.team2478.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MidtoRightSwitch extends CommandGroup{
	
	public MidtoRightSwitch() {
		addSequential(new AutonomoDriveStraight(300));
	}

}
