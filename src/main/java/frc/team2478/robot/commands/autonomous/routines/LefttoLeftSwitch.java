package frc.team2478.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.team2478.robot.Constants;
import frc.team2478.robot.commands.autonomous.AutonomoDriveStraight;
import frc.team2478.robot.commands.autonomous.AutonomoDriveTurn;
import frc.team2478.robot.commands.pneumatics.LowerHood;
import frc.team2478.robot.commands.scoring.StopAllScoringMotors;
import frc.team2478.robot.commands.scoring.feed.RunFeedAtDefault;
import frc.team2478.robot.commands.scoring.shooter.RunShooterAtVelocity;

public class LefttoLeftSwitch extends CommandGroup {

	public LefttoLeftSwitch() {
		addParallel(new LowerHood());
		addSequential(new AutonomoDriveStraight(149.25));
		addSequential(new AutonomoDriveTurn(90));
		addParallel(new RunShooterAtVelocity(Constants.ShooterRig.SWITCH_SPEED)); // rev shooter early, to reduce wasted time
		addSequential(new AutonomoDriveStraight(20.75));
		addParallel(new RunFeedAtDefault());
		addSequential(new WaitCommand(1.5));
		addSequential(new StopAllScoringMotors());
	}
	
}