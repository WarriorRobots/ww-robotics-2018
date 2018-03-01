package frc.team2478.robot.commands.scoring;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2478.robot.commands.pneumatics.OpenPickup;

public class TakeInCube extends CommandGroup {

	public TakeInCube() {
		addParallel(new OpenPickup());
		addSequential(new LoadCubeToFire());
		addSequential(new RackCubeToFire());
	}
	
}
