package frc.team2478.robot.commands.pneumatics;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RaiseAndClose extends CommandGroup {
	
	public RaiseAndClose() {
		addSequential(new ClosePickup());
		addSequential(new RaiseHood());
	}

}
