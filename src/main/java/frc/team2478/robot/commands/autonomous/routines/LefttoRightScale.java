package frc.team2478.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.team2478.robot.Constants;
import frc.team2478.robot.commands.autonomous.DriveAuto;
import frc.team2478.robot.commands.autonomous.TurnAuto;
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
		addSequential(new DriveAuto(207.75)); // drive forward, adjacent to alley between switch and scale -3
		addSequential(new TurnAuto(90)); // turn right, face down alley
		addSequential(new DriveAuto(238.25)); // drive through alley, ending up on rightmost side of field
		// added 6 to above
		addSequential(new TurnAuto(-90)); // turn left, face forwards
		addSequential(new DriveAuto(65)); // drive into nullzone
		addParallel(new RunShooterAtVelocity(Constants.ShooterRig.AUTO_SPEED)); // rev shooter to save time
		
		// if the robot shakes while turning, raiseHood needs to go after AutonomoDriveTurn
		addParallel(new RaiseHood()); // raise hood to save time
		addSequential(new TurnAuto(-67.5)); // turn left, face the scale
		addSequential(new DriveAuto(-9));
		addParallel(new RunFeedAtDefault()); // rev feed and launch cube
		addSequential(new WaitCommand(0.5)); // wait for cube to fire
		addSequential(new StopAllScoringMotors()); // kill motors to save power
		
	}

}