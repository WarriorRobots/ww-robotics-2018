package frc.team2478.robot.commands;

import frc.team2478.robot.RobotMap;

/**
 * When called, robot will drive identically to {@link JoystickTeleop} unless the sticks are very close in position:
 * then the robot will drive straight and disregard minute turning movements.
 * @author avuong0922
 *
 */
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