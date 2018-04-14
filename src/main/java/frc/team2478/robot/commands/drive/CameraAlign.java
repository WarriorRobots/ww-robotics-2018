package frc.team2478.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
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
		requires(Robot.sensors);
	}
	
	@Override
	protected void execute() {
		if (Robot.sensors.canSeeObject() && Math.abs(Robot.oi.getRightX()) < TURN_JOYSTICK_THRESHOLD) {
			Robot.drivetrain.arcadeDriveRaw(
				(Robot.oi.getRightY() > 0)
						? Math.pow(Robot.oi.getRightY() * 0.75, 2)
						: -Math.pow(Robot.oi.getRightY() * 0.75, 2),
				Robot.sensors.getObjectX() * P_VALUE); // spins to line up camera with cube
		} else {
			Robot.drivetrain.arcadeDriveSquared(
    			Robot.oi.getRightY() * 0.75,
    			Robot.oi.getRightX() * 0.75);
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}