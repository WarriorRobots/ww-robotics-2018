package frc.team2478.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.team2478.robot.Constants;
import frc.team2478.robot.commands.autonomous.DriveAuto;
import frc.team2478.robot.commands.autonomous.TurnAuto;
import frc.team2478.robot.commands.pneumatics.RaiseHood;
import frc.team2478.robot.commands.scoring.StopAllScoringMotors;
import frc.team2478.robot.commands.scoring.feed.RunFeedAtPercentage;

public class TestAutonomo extends CommandGroup {

	public TestAutonomo() {
		addParallel(new RaiseHood());
//		addParallel(new RunShooterAtVelocity(19000));
//		addSequential(new AutonomoDriveStraight(Constants.AutonomoDrive.InchesToClicks(105.5))); // switch
//		addSequential(new AutonomoDriveStraight(Constants.AutonomoDrive.InchesToClicks(262.9))); // scale
//		addSequential(new AutonomoDriveTurn(45));
		
		addSequential(new DriveAuto(400));
		addSequential(new TurnAuto(90));
		addSequential(new DriveAuto(400));
		addSequential(new TurnAuto(90));
		addSequential(new DriveAuto(400));
		addSequential(new TurnAuto(90));
		addSequential(new DriveAuto(400));
		addSequential(new TurnAuto(90));
		
		
		addParallel(new RunFeedAtPercentage(Constants.ShooterRig.FEED_PERCENT_SPEED));
		addSequential(new WaitCommand(2));
		addSequential(new StopAllScoringMotors());
	}
	
}