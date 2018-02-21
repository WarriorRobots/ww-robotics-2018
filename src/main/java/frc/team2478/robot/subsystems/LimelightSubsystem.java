package frc.team2478.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Instantiates Limelight camera and related Network Table keys,
 * and provides methods for accessing their data.
 */
public class LimelightSubsystem extends Subsystem {

	private NetworkTable visionTable;
	private NetworkTableInstance defaultTable;
	
	public LimelightSubsystem() {
		defaultTable = NetworkTableInstance.getDefault();
		visionTable = defaultTable.getTable("limelight");
	}
	
	public boolean canSeeObject() {
		// if network table returns 1, vision target exists
		// else (should be 0) there is no target visible
		return (visionTable.getEntry("tv").getDouble(0) == 1)
				? true : false;
	}
	
	public double getObjectX() {
		return visionTable.getEntry("tx").getDouble(0);
	}
	
	public double getObjectY() {
		return visionTable.getEntry("ty").getDouble(0);
	}
	
	/**
	 * Gets the percentage area of the currently-seen object relative to the image size. 
	 * @return Decimal representing percentage of image taken up by object, -1 to 1.
	 */
	public double getObjectArea() {
		return visionTable.getEntry("ta").getDouble(0);
	}
	
	/**
	 * Gets the rotation angle of the currently-seen object.
	 * @return -90 degrees to 90 degrees
	 */
	public double getObjectRotationAngle() {
		return visionTable.getEntry("ts").getDouble(0);
	}
	
    @Override
	public void initDefaultCommand() {}
}

