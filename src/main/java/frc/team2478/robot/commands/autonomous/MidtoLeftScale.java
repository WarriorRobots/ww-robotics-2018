package frc.team2478.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MidtoLeftScale extends CommandGroup{
	
	public MidtoLeftScale() {
		addSequential(new AutonomoDriveStraight(300));
	}

}
