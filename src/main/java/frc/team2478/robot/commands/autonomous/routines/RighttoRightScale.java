package frc.team2478.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.team2478.robot.Constants;
import frc.team2478.robot.commands.autonomous.CameraAuto;
import frc.team2478.robot.commands.autonomous.DriveAuto;
import frc.team2478.robot.commands.autonomous.PickupAuto;
import frc.team2478.robot.commands.autonomous.RackCubeAuto;
import frc.team2478.robot.commands.autonomous.TurnAuto;
import frc.team2478.robot.commands.autonomous.TurnAutoNoReset;
import frc.team2478.robot.commands.drive.StopDrive;
import frc.team2478.robot.commands.pneumatics.ClosePickup;
import frc.team2478.robot.commands.pneumatics.LowerHood;
import frc.team2478.robot.commands.pneumatics.OpenPickup;
import frc.team2478.robot.commands.pneumatics.RaiseHood;
import frc.team2478.robot.commands.scoring.StopAllScoringMotors;
import frc.team2478.robot.commands.scoring.feed.RunFeedAtDefault;
import frc.team2478.robot.commands.scoring.shooter.RunShooterAtVelocity;

/**
 * Robot will start touching the 45-degree corner on the right.
 */
public class RighttoRightScale extends CommandGroup {
	
	public RighttoRightScale() {
		addParallel(new LowerHood()); // hood starts raised, lower for safety
		addSequential(new DriveAuto(266.5)); // drive inside the nullzone
		addSequential(new TurnAuto(-67.5)); // turn left, face the scale
		addParallel(new RunShooterAtVelocity(Constants.ShooterRig.AUTO_SPEED)); // rev shooter early, to reduce wasted time
		addParallel(new RaiseHood()); // raise hood (safe to do so because of low speeds)
		addSequential(new DriveAuto(-12), 1.5); // back up to aim shot properly
		addParallel(new RunFeedAtDefault()); // rev feed to launch cube
		addSequential(new WaitCommand(0.5)); // wait a short time to ensure cube has exited robot
		addParallel(new LowerHood()); // lower hood to allow safe turning
		addSequential(new StopAllScoringMotors()); // stop the shooter to prevent power drain
		addSequential(new TurnAuto(110)); // turn towards the cubes near switch
		addSequential(new DriveAuto(-60)); // drive towards cubes, pickup facing out
		
		addParallel(new OpenPickup());
		addParallel(new PickupAuto());
		addSequential(new CameraAuto());
		addParallel(new StopAllScoringMotors());
		addParallel(new StopDrive());
		addParallel(new ClosePickup());
		addParallel(new RaiseHood());
		addParallel(new TurnAutoNoReset(0));
		addSequential(new WaitCommand(1));
		addSequential(new RackCubeAuto());
		addParallel(new StopAllScoringMotors());
		addSequential(new DriveAuto(50));
		
//		addParallel(new RunShooterAtVelocity(Constants.ShooterRig.AUTO_SPEED)); // rev shooter early, to reduce wasted time
//		addSequential(new TurnAuto(-110));
//		addParallel(new RunFeedAtDefault()); // rev feed to launch cube
//		addSequential(new WaitCommand(1.5)); // wait a short time to ensure cube has exited robot
//		addParallel(new LowerHood()); // lower hood to allow safe turning
//		addSequential(new StopAllScoringMotors()); // stop the shooter to prevent power drain
	}

}