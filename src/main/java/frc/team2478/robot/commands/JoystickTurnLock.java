package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;
import frc.team2478.robot.RobotMap;

/**
 * When called, robot will drive identically to {@link JoystickTeleop} unless the sticks are very close in position:
 * then the robot will drive straight and disregard minute turning movements.
 * @author avuong0922
 *
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
		
    	if (difference < RobotMap.DriveScalars.LOCKMODE_TOLERANCE) {
    		Robot.drivetrain.tankDriveTeleop(average, average);
    	} else {
    		Robot.drivetrain.tankDriveTeleop(Robot.oi.getLeftY(), Robot.oi.getRightY());
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