package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2478.robot.Constants;
import frc.team2478.robot.interfaces.DriveInterface;
import frc.team2478.robot.util.ControlHandler;

/**
 * When called, robot will drive in Alignment mode, an implementation of Arcade Drive intended for use in tiny movements on the field.
 * <p>Alignment mode uses only the right joystick: push vertically to drive forwards/backwards, and push sideways to turn.
 */
public class JoystickAlignment extends Command {

	private DriveInterface m_drivetrain;
	private ControlHandler m_oi;
	
    public JoystickAlignment(ControlHandler oi, DriveInterface drivetrain) {
    	m_drivetrain = drivetrain;
    	requires((Subsystem) m_drivetrain);
    	m_oi = oi;
    }

    protected void initialize() {
    }

    protected void execute() {
    	m_drivetrain.arcadeDriveSquared(
			m_oi.getRightY(Constants.DriveScalars.ARCADE_FORWARDSPEED),
			m_oi.getRightX(Constants.DriveScalars.ARCADE_TURNSPEED));
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