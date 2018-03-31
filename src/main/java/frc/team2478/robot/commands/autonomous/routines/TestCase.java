package frc.team2478.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.team2478.robot.Constants;
import frc.team2478.robot.Robot;
import frc.team2478.robot.commands.autonomous.CameraAuto;
import frc.team2478.robot.commands.autonomous.CheckIfCubeLoaded;
import frc.team2478.robot.commands.autonomous.CheckPickupCurrentDraw;
import frc.team2478.robot.commands.autonomous.DriveAuto;
import frc.team2478.robot.commands.autonomous.PickupAuto;
import frc.team2478.robot.commands.autonomous.RackCubeAuto;
import frc.team2478.robot.commands.autonomous.TurnAuto;
import frc.team2478.robot.commands.drive.StopDrive;
import frc.team2478.robot.commands.pneumatics.ClosePickup;
import frc.team2478.robot.commands.pneumatics.OpenPickup;
import frc.team2478.robot.commands.scoring.StopAllScoringMotors;
import frc.team2478.robot.commands.scoring.feed.RunFeedAtDefault;
import frc.team2478.robot.commands.scoring.pickup.RunPickupAtPercentage;
import frc.team2478.robot.commands.scoring.shooter.RunShooterAtVelocity;

public class TestCase extends CommandGroup {

	public TestCase() {
//		PICKUP CUBE IN AUTO
//		addParallel(new RunPickupAtPercentage(Constants.ShooterRig.PICKUP_PERCENT_SPEED));
//		addSequential(new OpenPickup());
//		addSequential(new LowerHood());
//		addSequential(new WaitCommand(0.5));
//		addParallel(new PickupAuto());
//		addParallel(new CameraAuto());
//		addSequential(new CheckPickupCurrentDraw());
//		addParallel(new ClosePickup());
//		addParallel(new StopDrive());
//		addSequential(new CheckIfCubeLoaded());
//		addSequential(new WaitCommand(0.5));
//		addSequential(new RackCubeAuto());
//		addParallel(new StopAllScoringMotors());
//		addSequential(new TurnAutoNoReset(0));
//		addSequential(new DriveAuto(30));
		
//		addSequential(new LefttoLeftSwitch());
//		addSequential(new DriveAuto(-30));
//		addSequential(new TurnAuto(-90));
//		addSequential(new DriveAuto(30));
		addParallel(new RunPickupAtPercentage(Constants.ShooterRig.PICKUP_PERCENT_SPEED));
		addParallel(new OpenPickup());
		addSequential(new WaitCommand(0.5));
		addParallel(new PickupAuto());
		addParallel(new CameraAuto());
		addSequential(new CheckPickupCurrentDraw());
		addParallel(new ClosePickup());
		addParallel(new StopDrive());
		addSequential(new CheckIfCubeLoaded());
		addSequential(new WaitCommand(0.5));
		addSequential(new RackCubeAuto());
		addParallel(new StopAllScoringMotors());
//		addSequential(new TurnAuto(10));
		addSequential(new DriveAuto(30));
		addSequential(new TurnAuto(180));
		addParallel(new RunShooterAtVelocity(Constants.ShooterRig.SWITCH_SPEED)); // rev shooter early, to reduce wasted time
		addSequential(new DriveAuto(30.75), 2);
		addParallel(new RunFeedAtDefault());
		addSequential(new WaitCommand(1));
		addSequential(new StopAllScoringMotors());
		
//		addSequential(new TurnAutoNoReset(0));
		
//		addSequential(new LowerHood());
//		addSequential(new ClosePickup());
//		addSequential(new DriveAuto(125));
//		addSequential(new TurnAuto(90));
				
	}
	
//	@Override
////	protected void execute() {
//		System.out.println("looping");
//		System.out.println("loping");
//		System.out.println("ooping");
//		System.out.println("ping");
//		System.out.println("ng");
//		System.out.println("looping");
//		System.out.println("ing");
//		System.out.println("g");
//		System.out.println("ping");
//		System.out.println("lllping");
//	}
	
	@Override
	protected void end() {
		Robot.drivetrain.resetAngle();
		Robot.drivetrain.stopDrive();
		Robot.shooter.stop();
	}
	
}