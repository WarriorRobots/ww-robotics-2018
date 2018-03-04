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

public class MidtoRightSwitch extends CommandGroup {
	
	public MidtoRightSwitch() {
		addParallel(new LowerHood());
		addSequential(new AutonomoDriveStraight(48));
		addSequential(new AutonomoDriveTurn(45));
		addSequential(new AutonomoDriveStraight(24));
		addSequential(new AutonomoDriveTurn(-45));
		addParallel(new RunShooterAtVelocity(Constants.ShooterRig.SWITCH_SPEED));
		addSequential(new AutonomoDriveStraight(60));
		addParallel(new RunFeedAtDefault());
		addSequential(new WaitCommand(2));
		addSequential(new StopAllScoringMotors());
	}

}