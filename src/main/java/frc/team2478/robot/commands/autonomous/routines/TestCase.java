package frc.team2478.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2478.robot.commands.autonomous.TurnAuto;

public class TestCase extends CommandGroup {

	public TestCase() {
//		addSequential(new OpenPickup());
//		addSequential(new LowerHood());
//		addSequential(new PickupAuto());
//		addParallel(new ClosePickup());
//		addSequential(new WaitCommand(1.5));
//		addSequential(new RackCubeAuto());
//		addParallel(new StopAllScoringMotors());
//		addSequential(new LefttoLeftSwitch());
		
		addSequential(new TurnAuto(90));
		addSequential(new TurnAuto(-90));
		addSequential(new TurnAuto(-180));
		addSequential(new TurnAuto(270));
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