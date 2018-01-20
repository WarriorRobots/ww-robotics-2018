package org.usfirst.frc.team2478.robot.commands;

import org.usfirst.frc.team2478.robot.RobotMap;

public class JoystickTurnLock extends CommandBase {
    
	public JoystickTurnLock() {
        requires(drivetrain);
    }

    protected void initialize() {}

    protected void execute() {
    	
    	// how far apart are the joystick Y-axes?
		double difference = Math.abs(oi.getLeftY() - oi.getRightY());
		double average = (oi.getLeftY() + oi.getRightY()) / 2;
		
    	if (difference < RobotMap.LOCKMODE_THRESHOLD) {
    		drivetrain.tankDriveSquared(average, average);
    	} else {
    		drivetrain.tankDriveSquared(oi.getLeftY(), oi.getRightY());
    	}
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {
    	System.out.println("LockMode interrupted+++++++++++++++++++++++++++++++++++++++");
    	this.end();
    }
}