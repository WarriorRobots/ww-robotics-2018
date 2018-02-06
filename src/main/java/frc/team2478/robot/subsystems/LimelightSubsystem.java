package frc.team2478.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2478.robot.interfaces.CameraInterface;

/**
 * Instantiates Limelight camera and related Network Table keys,
 * and provides methods for accessing their data.
 */
public class LimelightSubsystem extends Subsystem implements CameraInterface {

	// TO-DO: getTargetArea and getTargetSkew
	// - write Javadoc documentation
	
	private NetworkTable visionTable;
	private NetworkTableInstance defaultTable;
	
	public LimelightSubsystem() {
		defaultTable = NetworkTableInstance.getDefault();
		visionTable = defaultTable.getTable("limelight");
	}
	
	/**
	 * Returns true if the Limelight has picked up a vision target.
	 * @return True if target found, false by default.
	 */
	public boolean doesObjectExist() {
		// if network table returns 1, vision target exists
		// else (should be 0) there is no target
		return (visionTable.getEntry("tv").getDouble(0) == 1)
				? true : false;
	}
	
	/**
	 * Gets the horizontal distance between the camera's center and current vision target.
	 * @return Double representing vision target's offset from the Y axis.
	 */
	public double getObjectX() {
		return visionTable.getEntry("tx").getDouble(0);
	}
	
	/**
	 * Gets the vertical distance between the camera's center and current vision target.
	 * @return Double representing vision target's offset from the X axis.
	 */
	public double getObjectY() {
		return visionTable.getEntry("ty").getDouble(0);
	}
	
	@Override
    public void initDefaultCommand() {}
}

