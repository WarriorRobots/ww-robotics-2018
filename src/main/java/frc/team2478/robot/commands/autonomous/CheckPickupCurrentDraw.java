package frc.team2478.robot.commands.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;

public class CheckPickupCurrentDraw extends Command {

	private static final double AMPS_THRESHOLD = 10;
	
	@Override
	protected boolean isFinished() {
		return Robot.pickup.getMotorMasterCurrent() > AMPS_THRESHOLD || Robot.pickup.getMotorSlaveCurrent() > AMPS_THRESHOLD;
	}
	
	@Override
	protected void end() {
		DriverStation.reportError("Current above 15A", false);
	}

}
