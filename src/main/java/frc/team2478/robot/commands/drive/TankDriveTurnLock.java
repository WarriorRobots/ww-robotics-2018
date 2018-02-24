package frc.team2478.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Constants;
import frc.team2478.robot.Robot;

/**
 * When called, robot will drive identically to {@link TankDriveTeleop} unless the sticks are very close in position:
 * then the robot will drive straight and disregard tiny differences that would otherwise cause turning.
 */
public class TankDriveTurnLock extends Command {
    
	public TankDriveTurnLock() {
		requires(Robot.drivetrain);
    }

    @Override
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

    @Override
	protected boolean isFinished() {
        return false;
    }
}