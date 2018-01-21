package frc.team2478.robot.commands;

import frc.team2478.robot.RobotMap;

public class JoystickAlignment extends CommandBase {

    public JoystickAlignment() {
    	requires(drivetrain);
    }

    protected void initialize() {
    }

    protected void execute() {
    	drivetrain.arcadeDrive(oi.getRightY(RobotMap.ARCADE_FORWARDSPEED),
    						   oi.getRightX(RobotMap.ARCADE_TURNSPEED));
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