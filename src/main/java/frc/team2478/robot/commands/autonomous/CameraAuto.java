package frc.team2478.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;

/**
 * Line up to a cube in the camera vision and drive towards it.
 */
public class CameraAuto extends Command {

	public CameraAuto() {
		requires(Robot.drivetrain);
		requires(Robot.vision);
	}

	@Override
	protected void initialize() {
		Robot.drivetrain.resetAngle();
	}
	
	@Override
	protected void execute() {
		if (Robot.vision.canSeeObject()) {
			Robot.drivetrain.arcadeDriveRaw(.25, Robot.vision.getObjectX() * 0.04);
		} else {
			Robot.drivetrain.arcadeDriveRaw(0, 0.5);
		}
	}
	
	@Override
	protected void end() {
		Robot.drivetrain.stopDrive();
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.feed.isCubeLoaded();
	}
	
}
