package frc.team2478.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2478.robot.commands.autonomous.DriveAuto;
import frc.team2478.robot.commands.pneumatics.LowerHood;

/**
 * Robot should NOT start in front of the stack of power cubes.
 */
public class CrossLine extends CommandGroup {

	public CrossLine() {
		addParallel(new LowerHood());
		addSequential(new DriveAuto(93)); // check to make sure this works (130 - length of robot)
	}
	
}