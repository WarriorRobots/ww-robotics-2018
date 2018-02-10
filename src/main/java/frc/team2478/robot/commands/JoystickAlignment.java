package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Constants;
import frc.team2478.robot.Robot;

/**
 * When called, robot will drive in Alignment mode, an implementation of Arcade Drive intended for use in tiny movements on the field.
 * <p>Alignment mode uses only the right joystick: push vertically to drive forwards/backwards, and push sideways to turn.
 */
public class JoystickAlignment extends Command {

    public JoystickAlignment() {
    	requires(Robot.drivetrain);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.drivetrain.arcadeDriveSquared(
			Robot.oi.getRightY(Constants.DriveScalars.ARCADE_FORWARDSPEED),
			Robot.oi.getRightX(Constants.DriveScalars.ARCADE_TURNSPEED));
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    	this.end();
    }
}