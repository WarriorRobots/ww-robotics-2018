package frc.team2478.robot.commands;

import frc.team2478.robot.RobotMap;

/**
 * When called, robot will drive in Alignment mode, an implementation of Arcade Drive intended for use in tiny movements on the field.
 * <p>Alignment mode uses only the right joystick: push vertically to drive forwards/backwards, and push sideways to turn.
 */
public class JoystickAlignment extends CommandBase {

    public JoystickAlignment() {
    	requires(drivetrain);
    }

    protected void initialize() {
    }

    protected void execute() {
    	drivetrain.arcadeDriveTeleop(oi.getRightY(RobotMap.DriveScalars.ARCADE_FORWARDSPEED),
    						   		 oi.getRightX(RobotMap.DriveScalars.ARCADE_TURNSPEED));
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