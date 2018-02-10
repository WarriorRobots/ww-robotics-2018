package frc.team2478.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MidtoRightScale extends CommandGroup{
	
	public MidtoRightScale() {
		addSequential(new AutonomoDriveStraight(300));
	}

}
