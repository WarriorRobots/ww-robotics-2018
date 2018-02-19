package frc.team2478.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2478.robot.commands.autonomous.AutonomoDriveStraight;

public class Stright1kencoder extends CommandGroup{

	public Stright1kencoder() {
		addSequential(new AutonomoDriveStraight(1000));
	}
	
}
