package frc.team2478.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Constants;
import frc.team2478.robot.Robot;

public class CameraAuto extends Command {

	public CameraAuto() {
		requires(Robot.drivetrain);
		requires(Robot.vision);
	}

	@Override
	protected void initialize() {
	}
	
	@Override
	protected void execute() {
		if (Robot.vision.canSeeObject()) {
			Robot.drivetrain.arcadeDriveRaw(Constants.DriveScalars.ALIGNMENT_FORWARDSPEED, Robot.vision.getObjectX() * 0.03);
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
