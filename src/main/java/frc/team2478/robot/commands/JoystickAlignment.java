package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2478.robot.Constants;
import frc.team2478.robot.interfaces.DrivetrainInterface;
import frc.team2478.robot.util.ControlHandler;

/**
 * When called, robot will drive in Alignment mode, an implementation of Arcade Drive intended for use in tiny movements on the field.
 * <p>Alignment mode uses only the right joystick: push vertically to drive forwards/backwards, and push sideways to turn.
 */
public class JoystickAlignment extends Command {

	private DrivetrainInterface drivetrain;
	private ControlHandler oi;
	
    public JoystickAlignment(ControlHandler oi, DrivetrainInterface drivetrain) {
    	requires((Subsystem) drivetrain);
        this.drivetrain = drivetrain;
    	this.oi = oi;
    }

    protected void initialize() {
    }

    protected void execute() {
    	drivetrain.arcadeDriveSquared(
			oi.getRightY(Constants.DriveScalars.ARCADE_FORWARDSPEED),
			oi.getRightX(Constants.DriveScalars.ARCADE_TURNSPEED));
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