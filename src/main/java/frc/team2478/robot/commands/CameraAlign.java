package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2478.robot.Constants;
import frc.team2478.robot.interfaces.CameraInterface;
import frc.team2478.robot.interfaces.DriveInterface;
import frc.team2478.robot.util.ControlHandler;

/**
 * Aligns the robot with the most prominent vision target,
 * enabling Alignment mode if a target is not found.
 */
public class CameraAlign extends Command {
	
	private static final double SCALING_FACTOR = 0.02;
	private static final double TURN_JOYSTICK_THRESHOLD = 0.75;

	private DriveInterface m_drivetrain;
	private CameraInterface m_limelight;
	private ControlHandler m_oi;
	
	public CameraAlign(ControlHandler oi, DriveInterface drivetrain, CameraInterface limelight) {
		m_drivetrain = drivetrain;
		m_limelight = limelight;
		requires((Subsystem) drivetrain);
		requires((Subsystem) limelight);
		m_oi = oi;
	}
	
	@Override
	protected void execute() {
		if (m_limelight.doesObjectExist() && Math.abs(m_oi.getRightX()) < TURN_JOYSTICK_THRESHOLD) {
			m_drivetrain.arcadeDriveRaw(
				m_oi.getRightY(Constants.DriveScalars.ARCADE_FORWARDSPEED),
				m_limelight.getObjectX() * SCALING_FACTOR); // spins to line up camera with cube
		} else {
			m_drivetrain.arcadeDriveSquared(
    			m_oi.getRightY(Constants.DriveScalars.ARCADE_FORWARDSPEED),
    			m_oi.getRightX(Constants.DriveScalars.ARCADE_TURNSPEED));
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}