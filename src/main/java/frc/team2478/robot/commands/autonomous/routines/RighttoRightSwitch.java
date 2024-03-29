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

public class RighttoRightSwitch extends CommandGroup {
	
	public RighttoRightSwitch() {
		addParallel(new LowerHood());
		addSequential(new DriveAuto(142.25)); //was 142.25 //was also 102 for straight
		addSequential(new TurnAuto(-90));
		addParallel(new RunShooterAtVelocity(Constants.ShooterRig.SWITCH_SPEED)); // rev shooter early, to reduce wasted time
		addSequential(new DriveAuto(30.75), 2);
		addParallel(new RunFeedAtDefault());
		addSequential(new WaitCommand(0.5));
		addSequential(new StopAllScoringMotors()); 
	}

}