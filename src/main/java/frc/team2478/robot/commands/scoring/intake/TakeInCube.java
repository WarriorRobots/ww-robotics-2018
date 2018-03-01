package frc.team2478.robot.commands.scoring.intake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2478.robot.commands.pneumatics.OpenIntake;
import frc.team2478.robot.commands.scoring.LoadCubeToFire;
import frc.team2478.robot.commands.scoring.RackCubeToFire;

public class TakeInCube extends CommandGroup {

	public TakeInCube() {
		addParallel(new OpenIntake());
		addSequential(new LoadCubeToFire());
		addSequential(new RackCubeToFire());
	}
	
}
