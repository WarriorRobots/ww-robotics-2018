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

/**
 * Robot will start touching the 45-degree corner on the left.
 */
public class LefttoRightScale extends CommandGroup {
	
	public LefttoRightScale() {
		addParallel(new LowerHood()); // hood starts raised, lower for safety
		addSequential(new AutonomoDriveStraight(216.75)); // drive forward, adjacent to alley between switch and scale
		addSequential(new AutonomoDriveTurn(90)); // turn right, face down alley
		addSequential(new AutonomoDriveStraight(242.25)); // drive through alley, ending up on rightmost side of field
		addSequential(new AutonomoDriveTurn(-90)); // turn left, face forwards
		addSequential(new AutonomoDriveStraight(88.5)); // drive into nullzone
		addParallel(new RunShooterAtVelocity(Constants.ShooterRig.AUTO_SPEED)); // rev shooter to save time
		
		// if the robot shakes while turning, raiseHood needs to go after AutonomoDriveTurn
		addParallel(new RaiseHood()); // raise hood to save time
		addSequential(new AutonomoDriveTurn(-90)); // turn left, face the scale
		addParallel(new RunFeedAtDefault()); // rev feed and launch cube
		addSequential(new WaitCommand(1.5)); // wait for cube to fire
		addSequential(new StopAllScoringMotors()); // kill motors to save power
	}

}