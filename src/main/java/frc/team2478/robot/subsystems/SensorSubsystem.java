package frc.team2478.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

/**
 * Contains methods to receive data from the Limelight vision camera and MB1013 sonar sensor, which are tuned to detect cubes.
 */
public class SensorSubsystem extends Subsystem {

	private static final String LIMELIGHT_NETWORK_TABLE = "limelight";
	private static final String TARGET_EXISTS = "tv";
	private static final String TARGET_X = "tx";
	private static final String TARGET_Y = "ty";
	private static final String TARGET_AREA = "ta";
	private static final String TARGET_SKEW = "ts";
	
	private static final int SONAR_ID = 0;
	
	private NetworkTable visionTable;
	private AnalogInput sonar;
	
	public SensorSubsystem() {
		visionTable = NetworkTableInstance.getDefault().getTable(LIMELIGHT_NETWORK_TABLE);
		sonar = new AnalogInput(SONAR_ID);
	}
	
	public double getSonarDistanceInches() {
		return (sonar.getVoltage() / 0.000977) * 0.0393701;
	}
	
	public double getSonarDistanceRaw() {
		return sonar.getVoltage();
	}
	
	/**
	 * Checks if there is an object in the camera's view.
	 * @return  True if present, false otherwise
	 */
	public boolean canSeeObject() {
		// if network table returns 1, vision target exists
		// else (should be 0) there is no target visible
		return (visionTable.getEntry(TARGET_EXISTS).getDouble(0) == 1)
				? true : false;
	}
	
	/**
	 * Gets x-coordinate of current object on screen.
	 * @return X position of object in pixels
	 */
	public double getObjectX() {
		return visionTable.getEntry(TARGET_X).getDouble(0);
	}
	
	/**
	 * Gets y-coordinate of current object on screen.
	 * @return Y position of object in pixels
	 */
	public double getObjectY() {
		return visionTable.getEntry(TARGET_Y).getDouble(0);
	}
	
	/**
	 * Gets the percentage area of the currently-seen object relative to the image size. 
	 * @return Decimal representing percentage of image taken up by object, 0 to 1.
	 */
	public double getObjectArea() {
		return visionTable.getEntry(TARGET_AREA).getDouble(0);
	}
	
	/**
	 * Gets the rotation angle of the currently-seen object.
	 * @return -90 degrees to 90 degrees
	 */
	public double getObjectRotationAngle() {
		return visionTable.getEntry(TARGET_SKEW).getDouble(0);
	}
	
	@Override
	public void initSendable(SendableBuilder builder) {
		builder.setSmartDashboardType("subsystem-camera");
		builder.addDoubleProperty("sonar", () -> getSonarDistanceRaw(), null);
		builder.addBooleanProperty("object-exists", () -> canSeeObject(), null);
		builder.addDoubleArrayProperty("object-coords", () -> {
			double[] coords = new double[2];
			coords[0] = getObjectX();
			coords[1] = getObjectY();
			return coords;
		}, null);
	}

	@Override
	public void initDefaultCommand() {}
}

