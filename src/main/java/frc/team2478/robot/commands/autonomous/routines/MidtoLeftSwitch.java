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

/**
 * Robot will start with left bumper touching the center line.
 */
public class MidtoLeftSwitch extends CommandGroup {
	
	public MidtoLeftSwitch() {
		addParallel(new LowerHood()); // lower hood for switch shot
		addSequential(new AutonomoDriveStraight(45)); // drive forward to center of starting zone
		addSequential(new AutonomoDriveTurn(-90)); // turn left
		addSequential(new AutonomoDriveStraight(61));
		addSequential(new AutonomoDriveTurn(90)); // turn right, facing left switch plate
		addParallel(new RunShooterAtVelocity(Constants.ShooterRig.SWITCH_SPEED)); // rev shooter early
		addSequential(new AutonomoDriveStraight(57)); // drive until bumper hits wall
		addParallel(new RunFeedAtDefault()); // launch cube
		addSequential(new WaitCommand(1.5));
		addSequential(new StopAllScoringMotors()); // stop motors to conserve power
	}

}