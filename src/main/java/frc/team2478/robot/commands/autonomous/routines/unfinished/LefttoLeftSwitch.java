package frc.team2478.robot.commands.autonomous.routines.unfinished;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2478.robot.commands.autonomous.AutonomoDriveStraight;
import frc.team2478.robot.commands.autonomous.AutonomoDriveTurn;

@Deprecated
public class LefttoLeftSwitch extends CommandGroup {

	public LefttoLeftSwitch() {
		addSequential(new AutonomoDriveStraight(300));
		addSequential(new AutonomoDriveTurn(45));
	}
	
}