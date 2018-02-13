package frc.team2478.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2478.robot.commands.AutonomoDriveStraight;
import frc.team2478.robot.commands.AutonomoDriveTurn;

public class RighttoRightScale extends CommandGroup{
	
	public RighttoRightScale() {
		addSequential(new AutonomoDriveStraight(300));
		addSequential(new AutonomoDriveTurn(45));
		
	}

}