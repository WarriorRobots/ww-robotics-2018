package frc.team2478.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2478.robot.commands.AutonomoDriveStraight;
import frc.team2478.robot.commands.AutonomoDriveTurn;

public class MidtoRightScale extends CommandGroup{
	
	public MidtoRightScale() {
		addSequential(new AutonomoDriveStraight(30));
		addSequential(new AutonomoDriveTurn(90));
		addSequential(new AutonomoDriveStraight(200));
		addSequential(new AutonomoDriveTurn(-90));
		addSequential(new AutonomoDriveStraight(300));
		addSequential(new AutonomoDriveTurn(-45));
	}

}
