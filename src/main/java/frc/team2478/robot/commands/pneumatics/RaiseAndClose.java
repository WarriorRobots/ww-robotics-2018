package frc.team2478.robot.commands.pneumatics;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Combination of raising the hood and closing the pickup.
 * <p>Reduces driver input required.
 */
public class RaiseAndClose extends CommandGroup {
	
	public RaiseAndClose() {
		addSequential(new ClosePickup());
		addSequential(new RaiseHood());
	}

}
