package frc.team2478.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.team2478.robot.Constants;
import frc.team2478.robot.commands.autonomous.DriveAuto;
import frc.team2478.robot.commands.autonomous.TurnAuto;
import frc.team2478.robot.commands.autonomous.untested.CameraAuto;
import frc.team2478.robot.commands.autonomous.untested.PickupAuto;
import frc.team2478.robot.commands.pneumatics.ClosePickup;
import frc.team2478.robot.commands.pneumatics.LowerHood;
import frc.team2478.robot.commands.pneumatics.OpenPickup;
import frc.team2478.robot.commands.scoring.StopAllScoringMotors;
import frc.team2478.robot.commands.scoring.feed.RunFeedAtDefault;
import frc.team2478.robot.commands.scoring.shooter.RunShooterAtVelocity;

/**
 * Robot will start with left bumper touching the center line.
 */
public class MidtoLeftSwitchDouble extends CommandGroup {
	
	public MidtoLeftSwitchDouble() {
		addParallel(new LowerHood()); // lower hood for switch shot
		addSequential(new DriveAuto(50)); // drive forward to center of starting zone
		addSequential(new TurnAuto(-90)); // turn left
		addSequential(new DriveAuto(72)); // drive across
		addSequential(new TurnAuto(90)); // turn right, facing left switch plate
		addParallel(new RunShooterAtVelocity(Constants.ShooterRig.SWITCH_SPEED)); // rev shooter early
		addSequential(new DriveAuto(52), 2.0); // drive until bumper hits wall
		addParallel(new RunFeedAtDefault()); // launch cube
		addSequential(new WaitCommand(0.5));
		addSequential(new StopAllScoringMotors()); // stop motors to conserve power
		
		addSequential(new DriveAuto(-36));
		addSequential(new TurnAuto(-50));
		addSequential(new DriveAuto(-48));
		addSequential(new TurnAuto(-100));

		addParallel(new OpenPickup());
		addParallel(new PickupAuto());
		addSequential(new CameraAuto());
		addParallel(new ClosePickup());
		addParallel(new StopAllScoringMotors());
		
		addSequential(new TurnAuto(135));
		addParallel(new RunShooterAtVelocity(Constants.ShooterRig.SWITCH_SPEED)); // rev shooter early
		addSequential(new DriveAuto(46));
		addParallel(new RunFeedAtDefault()); // launch cube
		addSequential(new WaitCommand(0.5));
		addSequential(new StopAllScoringMotors()); // stop motors to conserve power
	}

}