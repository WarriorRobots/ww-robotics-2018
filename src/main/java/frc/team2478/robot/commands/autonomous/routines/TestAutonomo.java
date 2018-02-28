package frc.team2478.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2478.robot.commands.autonomous.AutonomoDriveStraight;
import frc.team2478.robot.commands.autonomous.AutonomoDriveTurn;
import frc.team2478.robot.commands.autonomous.TestShooterRig;
import frc.team2478.robot.commands.scoring.hood.LowerHood;

public class TestAutonomo extends CommandGroup {

	public TestAutonomo() {
		addParallel(new LowerHood());
		addSequential(new AutonomoDriveStraight(200));
		addSequential(new AutonomoDriveTurn(90));
		addSequential(new AutonomoDriveStraight(200));
		addSequential(new AutonomoDriveTurn(90));
		addSequential(new AutonomoDriveStraight(200));
		addSequential(new AutonomoDriveTurn(90));
		addSequential(new AutonomoDriveStraight(200));
		addSequential(new AutonomoDriveTurn(90));
		addSequential(new TestShooterRig());
	}
	
}