package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;
import frc.team2478.robot.RobotMap;

/**
 * Aligns the robot with the most prominent vision target,
 * enabling Alignment mode if a target is not found.
 */
public class CameraAlign extends Command {
	
	private static final double SCALING_FACTOR = 0.02;
	
	public CameraAlign() {
		requires(Robot.drivetrain);
		requires(Robot.limelight);
	}
	
	@Override
	protected void execute() {
		if (Robot.limelight.doesTargetExist()) {
			Robot.drivetrain.arcadeDriveAutonomo(
				Math.pow(Robot.oi.getRightY(RobotMap.DriveScalars.ARCADE_FORWARDSPEED), 2),
				Robot.limelight.getHorizontalOffset() * SCALING_FACTOR); // spins to line up camera with cube
		} else {
			// TO-DO: make consistent with JoystickAligment
			Robot.drivetrain.arcadeDriveTeleop(
	    			Robot.oi.getRightY(RobotMap.DriveScalars.ARCADE_FORWARDSPEED),
	    			Robot.oi.getRightX(RobotMap.DriveScalars.ARCADE_TURNSPEED));
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
