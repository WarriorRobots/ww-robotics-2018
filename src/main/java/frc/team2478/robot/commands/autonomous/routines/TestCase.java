package frc.team2478.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.team2478.robot.commands.autonomous.CameraAuto;
import frc.team2478.robot.commands.autonomous.DriveAuto;
import frc.team2478.robot.commands.autonomous.PickupAuto;
import frc.team2478.robot.commands.autonomous.RackCubeAuto;
import frc.team2478.robot.commands.pneumatics.ClosePickup;
import frc.team2478.robot.commands.pneumatics.LowerHood;
import frc.team2478.robot.commands.pneumatics.OpenPickup;
import frc.team2478.robot.commands.scoring.StopAllScoringMotors;

public class TestCase extends CommandGroup {

	public TestCase() {
		addSequential(new OpenPickup());
		addSequential(new LowerHood());
		addSequential(new DriveAuto(-100));
		addParallel(new PickupAuto());
		addSequential(new CameraAuto());
		addParallel(new ClosePickup());
		addSequential(new WaitCommand(1.5));
		addSequential(new RackCubeAuto());
		addParallel(new StopAllScoringMotors());
		
//		addSequential(new DriveAuto(60));
//		addSequential(new TurnAuto(90));
//		addSequential(new DriveAuto(60));
//		addSequential(new TurnAuto(90));
//		addSequential(new DriveAuto(60));
//		addSequential(new TurnAuto(90));
//		addSequential(new DriveAuto(60));
//		addSequential(new TurnAuto(90));
	}
	
	@Override
	protected void execute() {
		System.out.println("looping");
		System.out.println("loping");
		System.out.println("ooping");
		System.out.println("ping");
		System.out.println("ng");
		System.out.println("looping");
		System.out.println("ing");
		System.out.println("g");
		System.out.println("ping");
		System.out.println("lllping");
	}
	
}