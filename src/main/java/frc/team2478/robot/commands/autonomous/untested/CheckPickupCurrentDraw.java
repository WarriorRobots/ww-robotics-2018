package frc.team2478.robot.commands.autonomous.untested;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;

/**
 * Command will terminate when pickup wheels register a high current.
 * This indicates that a cube is likely in contact with the wheels.
 */
public class CheckPickupCurrentDraw extends Command {

	private static final double AMPS_THRESHOLD = 3;
	
	@Override
	protected boolean isFinished() {
		return Robot.pickup.getMotorMasterCurrent() > AMPS_THRESHOLD || Robot.pickup.getMotorSlaveCurrent() > AMPS_THRESHOLD;
	}
	
}