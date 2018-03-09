package frc.team2478.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.team2478.robot.Constants;
import frc.team2478.robot.commands.autonomous.DriveAuto;
import frc.team2478.robot.commands.autonomous.TurnAuto;
import frc.team2478.robot.commands.pneumatics.LowerHood;
import frc.team2478.robot.commands.scoring.StopAllScoringMotors;
import frc.team2478.robot.commands.scoring.feed.RunFeedAtDefault;
import frc.team2478.robot.commands.scoring.shooter.RunShooterAtVelocity;

public class LefttoLeftSwitch extends CommandGroup {

	public LefttoLeftSwitch() {
		addParallel(new LowerHood());
		addSequential(new WaitCommand(5));
		addSequential(new DriveAuto(149.25));
		addSequential(new TurnAuto(90));
		addParallel(new RunShooterAtVelocity(Constants.ShooterRig.SWITCH_SPEED)); // rev shooter early, to reduce wasted time
		addSequential(new DriveAuto(30.75), 2);
		addParallel(new RunFeedAtDefault());
		addSequential(new WaitCommand(1.5));
		addSequential(new StopAllScoringMotors());
	}
	
}