package frc.team2478.robot.commands.autonomous.routines.unfinished;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2478.robot.commands.autonomous.routines.CrossLine;

public class MidtoRightScale extends CommandGroup {
	
	public MidtoRightScale() {
		DriverStation.reportError("Unfinished Auto Case "
				+ this.getName()
				+ ", robot will CrossLine instead", false);
		new CrossLine().start();
	}

}