package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2478.robot.Constants;
import frc.team2478.robot.interfaces.CameraInterface;
import frc.team2478.robot.interfaces.DrivetrainInterface;
import frc.team2478.robot.util.ControlHandler;

/**
 * Aligns the robot with the most prominent vision target,
 * enabling Alignment mode if a target is not found.
 */
public class CameraAlign extends Command {
	
	private static final double SCALING_FACTOR = 0.02;
	private static final double TURN_JOYSTICK_THRESHOLD = 0.75;

	private DrivetrainInterface drivetrain;
	private CameraInterface limelight;
	private ControlHandler oi;
	
	public CameraAlign(ControlHandler oi, DrivetrainInterface drivetrain, CameraInterface limelight) {
		requires((Subsystem) drivetrain);
		requires((Subsystem) limelight);
		this.drivetrain = drivetrain;
		this.limelight = limelight;
		this.oi = oi;
	}
	
	@Override
	protected void execute() {
		if (limelight.canSeeObject() && Math.abs(oi.getRightX()) < TURN_JOYSTICK_THRESHOLD) {
			drivetrain.arcadeDriveRaw(
				oi.getRightY(Constants.DriveScalars.ARCADE_FORWARDSPEED),
				limelight.getObjectX() * SCALING_FACTOR); // spins to line up camera with cube
		} else {
			drivetrain.arcadeDriveSquared(
    			oi.getRightY(Constants.DriveScalars.ARCADE_FORWARDSPEED),
    			oi.getRightX(Constants.DriveScalars.ARCADE_TURNSPEED));
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}