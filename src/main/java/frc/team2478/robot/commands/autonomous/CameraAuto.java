package frc.team2478.robot.commands.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;

public class CameraAuto extends Command {

	private double currentAngle = 0;
	
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
		}
		currentAngle = Robot.drivetrain.getAngle();
	}
	
	@Override
	protected void end() {
		Robot.drivetrain.stopDrive();
		DriverStation.reportWarning(Double.toString(currentAngle), false);
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.feed.isCubeLoaded();
	}
	
}
