package frc.team2478.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MidtoLeftSwitch extends CommandGroup{
	
	public MidtoLeftSwitch() {
		addSequential(new AutonomoDriveStraight(300));
	}

}
