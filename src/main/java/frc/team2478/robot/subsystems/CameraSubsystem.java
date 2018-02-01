package frc.team2478.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Instantiates Limelight camera and related Network Table keys,
 * and provides methods for accessing their data.
 */
public class CameraSubsystem extends Subsystem {

	// TO-DO: getTargetArea and getTargetSkew
	// - write Javadoc documentation
	
	private NetworkTable m_visionTable;
	private NetworkTableInstance m_defaultTable;
	
	public CameraSubsystem() {
		m_defaultTable = NetworkTableInstance.getDefault();
		m_visionTable = m_defaultTable.getTable("limelight");
	}
	
	/**
	 * Returns true if the Limelight has picked up a vision target.
	 * @return True if target found, false by default.
	 */
	public boolean doesTargetExist() {
		// if network table returns 1, vision target exists
		// else (should be 0) there is no target
		return (m_visionTable.getEntry("tv").getDouble(0) == 1)
				? true : false;
	}
	
	/**
	 * Gets the horizontal distance between the camera's center and current vision target.
	 * @return Double representing vision target's offset from the Y axis.
	 */
	public double getHorizontalOffset() {
		return m_visionTable.getEntry("tx").getDouble(0);
	}
	
	/**
	 * Gets the vertical distance between the camera's center and current vision target.
	 * @return Double representing vision target's offset from the X axis.
	 */
	public double getVerticalOffset() {
		return m_visionTable.getEntry("ty").getDouble(0);
	}
	
	@Override
    public void initDefaultCommand() {}
}

