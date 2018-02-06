package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2478.robot.Constants;
import frc.team2478.robot.interfaces.DriveInterface;
import frc.team2478.robot.util.ControlHandler;

/**
 * When called, robot will drive identically to {@link JoystickTeleop} unless the sticks are very close in position:
 * then the robot will drive straight and disregard minute turning movements.
 * @author avuong0922
 *
 */
public class JoystickTurnLock extends Command {
    
	private DriveInterface m_drivetrain;
	private ControlHandler m_oi;
	
	public JoystickTurnLock(ControlHandler oi, DriveInterface drivetrain) {
        m_drivetrain = drivetrain;
		requires((Subsystem) drivetrain);
		m_oi = oi;
    }

    protected void initialize() {}

    protected void execute() {
    	
    	// how far apart are the joystick Y-axes?
		double difference = Math.abs(m_oi.getLeftY() - m_oi.getRightY());
		double average = (m_oi.getLeftY() + m_oi.getRightY()) / 2;
		
    	if (difference < Constants.DriveScalars.LOCKMODE_TOLERANCE) {
    		m_drivetrain.tankDriveSquared(average, average);
    	} else {
    		m_drivetrain.tankDriveSquared(m_oi.getLeftY(), m_oi.getRightY());
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