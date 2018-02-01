package frc.team2478.robot.commands;

import frc.team2478.robot.RobotMap;

/**
 * Aligns the robot with the most prominent vision target,
 * enabling Alignment mode if a target is not found.
 */
public class CameraAlign extends CommandBase {
	
	private static final double SCALING_FACTOR = 0.02;
	
	public CameraAlign() {
		requires(drivetrain);
		requires(limelight);
	}
	
	@Override
	protected void execute() {
		if (limelight.doesTargetExist() && Math.abs(oi.getRightX()) < 0.75) {
			drivetrain.arcadeDriveAutonomo(
				oi.getRightY(RobotMap.DriveScalars.ARCADE_FORWARDSPEED),
				limelight.getHorizontalOffset() * SCALING_FACTOR); // spins to line up camera with cube
		} else {
			drivetrain.arcadeDriveTeleop(
	    			oi.getRightY(RobotMap.DriveScalars.ARCADE_FORWARDSPEED),
	    			oi.getRightX(RobotMap.DriveScalars.ARCADE_TURNSPEED));
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
