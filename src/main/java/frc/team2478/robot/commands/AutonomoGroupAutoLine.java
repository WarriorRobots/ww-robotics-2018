package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomoGroupAutoLine extends CommandGroup {
		//put dashboard there
	public AutonomoGroupAutoLine(double dist1) {
		addSequential(new AutonomoDriveStraight(dist1));
	}
}