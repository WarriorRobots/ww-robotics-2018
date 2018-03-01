package frc.team2478.robot.commands.scoring;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2478.robot.commands.pneumatics.ClosePickup;
import frc.team2478.robot.commands.scoring.sequence.DetectOpenPickup;
import frc.team2478.robot.commands.scoring.sequence.LoadCubeToFire;
import frc.team2478.robot.commands.scoring.sequence.PullCubeIntoRobot;
import frc.team2478.robot.commands.scoring.sequence.RackCubeToFire;

public class PickupCubeFromGround extends CommandGroup {

	public PickupCubeFromGround() {
		addSequential(new DetectOpenPickup(new PullCubeIntoRobot())); // from ground to pickup if and only if pickup is opened
		addSequential(new ClosePickup()); // close pickup to lock cube in
		addSequential(new LoadCubeToFire()); // from pickup to top half of robot
		addSequential(new RackCubeToFire()); // from top half to bottom half (ready to fire)
	}
	
}
