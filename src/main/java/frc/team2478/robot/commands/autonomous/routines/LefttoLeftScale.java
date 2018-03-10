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
public class LefttoLeftScale extends CommandGroup {
	
	public LefttoLeftScale() {
		addParallel(new LowerHood()); // hood starts raised, lower for safety
		addSequential(new DriveAuto(260.5)); // drive inside the nullzone
		addSequential(new TurnAuto(67.5)); // turn right, face the scale
		addParallel(new RunShooterAtVelocity(Constants.ShooterRig.AUTO_SPEED)); // rev shooter early, to reduce wasted time
		addParallel(new RaiseHood()); // raise hood (safe to do so because of low speeds)
		addSequential(new DriveAuto(-12)); // back up to aim shot properly
		addParallel(new RunFeedAtDefault()); // rev feed to launch cube
		addSequential(new WaitCommand(1.5)); // wait a short time to ensure cube has exited robot
		addParallel(new LowerHood()); // lower hood to allow safe turning
		addSequential(new StopAllScoringMotors()); // stop the shooter to prevent power drain
		addSequential(new TurnAuto(-110)); // turn towards the cubes near switch
		addSequential(new DriveAuto(-80)); // drive towards cubes, pickup facing out
	}

}