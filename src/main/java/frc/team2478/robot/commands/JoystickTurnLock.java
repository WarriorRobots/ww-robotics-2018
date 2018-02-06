package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2478.robot.Constants;
import frc.team2478.robot.interfaces.DrivetrainInterface;
import frc.team2478.robot.util.ControlHandler;

/**
 * When called, robot will drive identically to {@link JoystickTeleop} unless the sticks are very close in position:
 * then the robot will drive straight and disregard minute turning movements.
 * @author avuong0922
 *
 */
public class JoystickTurnLock extends Command {
    
	private DrivetrainInterface drivetrain;
	private ControlHandler oi;
	
	public JoystickTurnLock(ControlHandler oi, DrivetrainInterface drivetrain) {
		requires((Subsystem) drivetrain);
        this.drivetrain = drivetrain;
		this.oi = oi;
    }

    protected void initialize() {}

    protected void execute() {
    	// how far apart are the joystick Y-axes?
		double difference = Math.abs(oi.getLeftY() - oi.getRightY());
		double average = (oi.getLeftY() + oi.getRightY()) / 2;
		
    	if (difference < Constants.DriveScalars.LOCKMODE_TOLERANCE) {
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
    	this.end();
    }
}