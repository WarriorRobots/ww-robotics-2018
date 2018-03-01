package frc.team2478.robot.commands.scoring;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2478.robot.Robot;
import frc.team2478.robot.commands.pneumatics.ClosePickup;
import frc.team2478.robot.commands.scoring.sequence.RackCubeToFire;

public class PickupCubeFromPortal extends CommandGroup {

	public PickupCubeFromPortal() {
		addSequential(new ClosePickup()); // close pickup to lock cube in
		addSequential(new RackCubeToFire());
	}
	
	@Override
	protected void end() {
		Robot.shooter.stop();
		Robot.pickup.stop();
		Robot.feed.stop();
	}
	
	@Override
	protected void interrupted() {
		this.end();
	}
	
}
