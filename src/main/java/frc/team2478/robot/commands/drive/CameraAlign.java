package frc.team2478.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.ControlHandler;
import frc.team2478.robot.Robot;

/**
 * Aligns the robot with the most prominent vision target,
 * enabling Alignment mode if a target is not found.
 */
public class CameraAlign extends Command {
	
	private static final double P_VALUE = 0.03;
	private static final double TURN_JOYSTICK_THRESHOLD = 0.5;

	public CameraAlign() {
		requires(Robot.drivetrain);
		requires(Robot.vision);
	}
	
	@Override
	protected void execute() {
		if (Robot.vision.canSeeObject() && Math.abs(ControlHandler.getInstance().getRightX()) < TURN_JOYSTICK_THRESHOLD) {
			Robot.drivetrain.arcadeDriveRaw(
				(ControlHandler.getInstance().getRightY() > 0)
						? Math.pow(ControlHandler.getInstance().getRightY() * 0.75, 2)
						: -Math.pow(ControlHandler.getInstance().getRightY() * 0.75, 2),
				Robot.vision.getObjectX() * P_VALUE); // spins to line up camera with cube
		} else {
			Robot.drivetrain.arcadeDriveSquared(
    			ControlHandler.getInstance().getRightY() * 0.75,
    			ControlHandler.getInstance().getRightX() * 0.75);
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}