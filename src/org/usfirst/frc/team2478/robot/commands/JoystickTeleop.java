package org.usfirst.frc.team2478.robot.commands;

public class JoystickTeleop extends CommandBase {
	
    public JoystickTeleop() {
    	requires(drivetrain);
    }

    protected void initialize() {
    }

    protected void execute() {
    	drivetrain.tankDrive(oi.getLeftY(), oi.getRightY());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    	System.out.println("NormalDrive interrupted+++++++++++++++++++++++++++++++++++++++");
    	this.end();
    }
}