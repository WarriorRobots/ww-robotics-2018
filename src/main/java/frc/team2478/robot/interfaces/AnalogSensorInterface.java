package frc.team2478.robot.interfaces;

/**
 * Interface for analog sensors (ie. those that do not return true/false).
 */
public interface AnalogSensorInterface {

	/**
	 * Returns a double representing the current sensor output.
	 */
	public double getSensorOutput();
	
	/**
	 * Resets sensor to 0.
	 */
	public void resetSensor();
	
}
