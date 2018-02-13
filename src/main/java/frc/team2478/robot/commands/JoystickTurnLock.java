package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Constants;
import frc.team2478.robot.Robot;

/**
 * When called, robot will drive identically to {@link JoystickTeleop} unless the sticks are very close in position:
 * then the robot will drive straight and disregard minute turning movements.
 */
public class JoystickTurnLock extends Command {
    
	public JoystickTurnLock() {
		requires(Robot.drivetrain);
    }

    protected void initialize() {}

    protected void execute() {
    	// how far apart are the joystick Y-axes?
		double difference = Math.abs(Robot.oi.getLeftY() - Robot.oi.getRightY());
		double average = (Robot.oi.getLeftY() + Robot.oi.getRightY()) / 2;
		
    	if (difference < Constants.DriveScalars.LOCKMODE_TOLERANCE) {
    		Robot.drivetrain.tankDriveSquared(average, average);
    	} else {
    		Robot.drivetrain.tankDriveSquared(Robot.oi.getLeftY(), Robot.oi.getRightY());
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