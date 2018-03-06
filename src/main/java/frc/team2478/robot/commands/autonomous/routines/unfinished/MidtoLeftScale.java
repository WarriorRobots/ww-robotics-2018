package frc.team2478.robot.commands.autonomous.routines.unfinished;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2478.robot.commands.autonomous.routines.CrossLine;

@Deprecated
public class MidtoLeftScale extends CommandGroup {
	
	public MidtoLeftScale() {
		DriverStation.reportError("Unfinished Auto Case "
				+ this.getName()
				+ ", robot will CrossLine instead", false);
		new CrossLine().start();
	}

}