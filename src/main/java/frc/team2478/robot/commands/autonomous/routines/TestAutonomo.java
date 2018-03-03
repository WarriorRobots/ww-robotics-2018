package frc.team2478.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.team2478.robot.Constants;
import frc.team2478.robot.Robot;
import frc.team2478.robot.commands.autonomous.AutonomoDriveStraight;
import frc.team2478.robot.commands.pneumatics.LowerHood;
import frc.team2478.robot.commands.scoring.StopAllMotors;
import frc.team2478.robot.commands.scoring.feed.RunFeedAtPercentage;
import frc.team2478.robot.commands.scoring.shooter.RunShooterAtVelocity;

public class TestAutonomo extends CommandGroup {

	public TestAutonomo() {
		addParallel(new LowerHood());
		addParallel(new RunShooterAtVelocity(Robot.shooter.switchSpeed));
//		addSequential(new AutonomoDriveStraight(Constants.AutonomoDrive.InchesToClicks(105.5))); // switch
		addSequential(new AutonomoDriveStraight(Constants.AutonomoDrive.InchesToClicks(0))); // scale
		addParallel(new RunFeedAtPercentage(Constants.ShooterRig.FEED_PERCENT_SPEED));
		addSequential(new WaitCommand(2));
		addSequential(new StopAllMotors());
	}
	
}