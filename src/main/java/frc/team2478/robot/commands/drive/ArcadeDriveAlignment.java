package frc.team2478.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Constants;
import frc.team2478.robot.Robot;

/**
 * When called, robot will drive in Alignment mode,
 * an implementation of Arcade Drive intended for use in tiny movements on the field.
 * <p>Alignment mode uses only the right joystick:
 * push vertically to drive forwards/backwards,
 * and push sideways to turn.
 */
public class ArcadeDriveAlignment extends Command {

    public ArcadeDriveAlignment() {
    	requires(Robot.drivetrain);
    }

    @Override
	protected void execute() {
    	Robot.drivetrain.arcadeDriveSquared(
			Robot.oi.getRightY(Constants.DriveScalars.ALIGNMENT_FORWARDSPEED),
			Robot.oi.getRightX(Constants.DriveScalars.ALIGNMENT_TURNSPEED));
    }

    @Override
	protected boolean isFinished() {
        return false;
    }
}