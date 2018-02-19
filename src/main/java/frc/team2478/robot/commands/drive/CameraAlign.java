package frc.team2478.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Constants;
import frc.team2478.robot.Robot;

/**
 * Aligns the robot with the most prominent vision target,
 * enabling Alignment mode if a target is not found.
 */
public class CameraAlign extends Command {
	
	private static final double SCALING_FACTOR = 0.02;
	private static final double TURN_JOYSTICK_THRESHOLD = 0.75;

	public CameraAlign() {
		requires(Robot.drivetrain);
		requires(Robot.limelight);
	}
	
	@Override
	protected void execute() {
		if (Robot.limelight.canSeeObject() && Math.abs(Robot.oi.getRightX()) < TURN_JOYSTICK_THRESHOLD) {
			Robot.drivetrain.arcadeDriveRaw(
				Robot.oi.getRightY(Constants.DriveScalars.ALIGNMENT_FORWARDSPEED),
				Robot.limelight.getObjectX() * SCALING_FACTOR); // spins to line up camera with cube
		} else {
			Robot.drivetrain.arcadeDriveSquared(
    			Robot.oi.getRightY(Constants.DriveScalars.ALIGNMENT_FORWARDSPEED),
    			Robot.oi.getRightX(Constants.DriveScalars.ALIGNMENT_TURNSPEED));
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}