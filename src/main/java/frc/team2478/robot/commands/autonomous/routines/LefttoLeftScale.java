package frc.team2478.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.team2478.robot.Constants;
import frc.team2478.robot.commands.autonomous.AutonomoDriveStraight;
import frc.team2478.robot.commands.autonomous.AutonomoDriveTurn;
import frc.team2478.robot.commands.pneumatics.LowerHood;
import frc.team2478.robot.commands.pneumatics.RaiseHood;
import frc.team2478.robot.commands.scoring.StopAllScoringMotors;
import frc.team2478.robot.commands.scoring.feed.RunFeedAtDefault;
import frc.team2478.robot.commands.scoring.shooter.RunShooterAtVelocity;

public class LefttoLeftScale extends CommandGroup {
	
	public LefttoLeftScale() {
		addParallel(new LowerHood());
		addSequential(new AutonomoDriveStraight(287.5));
		addSequential(new AutonomoDriveTurn(90));
		addParallel(new RunShooterAtVelocity(Constants.ShooterRig.AUTO_SPEED));
		addParallel(new RaiseHood());
		addSequential(new AutonomoDriveStraight(-12));
		addParallel(new RunFeedAtDefault());
		addSequential(new WaitCommand(1.5));
		addParallel(new LowerHood());
		addSequential(new StopAllScoringMotors());
		addSequential(new AutonomoDriveTurn(-110));
		addSequential(new AutonomoDriveStraight(-80));
	}

}