package frc.team2478.robot.commands.scoring;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team2478.robot.commands.pneumatics.ClosePickup;
import frc.team2478.robot.commands.scoring.sequence.TopLoadCube;

public class PickupCubeFromPortal extends CommandGroup {

	public PickupCubeFromPortal() {
		addParallel(new ClosePickup()); // close pickup to lock cube in
		addSequential(new TopLoadCube());
	}
	
	@Override
	protected void end() {
		Scheduler.getInstance().add(new StopAllScoringMotors());
	}
	
	@Override
	protected void interrupted() {
		this.end();
	}
	
}
