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
	
	public boolean onEdge = true;
	public RighttoRightSwitch() {
		addParallel(new LowerHood());
		if (onEdge = true) {
		addSequential(new DriveAuto(142.25)); //was 142.25 //was also 102 for straight
		addSequential(new TurnAuto(-90));
		} else {
			addSequential(new DriveAuto(102));
		}
		addParallel(new RunShooterAtVelocity(Constants.ShooterRig.SWITCH_SPEED)); // rev shooter early, to reduce wasted time
		if (onEdge = true) {
			addSequential(new DriveAuto(20.75));
		} else {
			addSequential(new DriveAuto(0));
		}
		addParallel(new RunFeedAtDefault());
		addSequential(new WaitCommand(1.5));
		addSequential(new StopAllScoringMotors()); 
	}

}