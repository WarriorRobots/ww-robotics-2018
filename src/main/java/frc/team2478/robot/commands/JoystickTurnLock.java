package frc.team2478.robot.commands;

import frc.team2478.robot.RobotMap;

public class JoystickTurnLock extends CommandBase {
    
	public JoystickTurnLock() {
        requires(drivetrain);
    }

    protected void initialize() {}

    protected void execute() {
    	
    	// how far apart are the joystick Y-axes?
		double difference = Math.abs(oi.getLeftY() - oi.getRightY());
		double average = (oi.getLeftY() + oi.getRightY()) / 2;
		
    	if (difference < RobotMap.DriveScalars.LOCKMODE_TOLERANCE) {
    		drivetrain.tankDriveTeleop(average, average);
    	} else {
    		drivetrain.tankDriveTeleop(oi.getLeftY(), oi.getRightY());
    	}
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {
    	this.end();
    }
}