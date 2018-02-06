package frc.team2478.robot.interfaces;

/**
 * Interface for accelerometers (devices that detect motion or change in acceleration).
 */
public interface AccelerometerInterface {

	/**
	 * Gets the accelerometer speed of the x-axis.
	 */
	public double getAccelX();

	/**
	 * Gets the accelerometer speed of the y-axis.
	 */
	public double getAccelY();
	
	/**
	 * Gets the accelerometer speed of the z-axis.
	 */
	public double getAccelZ();
	
}
