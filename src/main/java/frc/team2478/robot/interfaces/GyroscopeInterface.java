package frc.team2478.robot.interfaces;

/**
 * Interface for getting data from a gyroscope (sensor that measures rotational angles).
 */
public interface GyroscopeInterface {
	
	/**
	 * Returns currently-detected pitch (angle of forward tilt).
	 */
	public double getPitch();
	
	/**
	 * Returns currently-detected roll (angle of horizontal tilt).
	 */
	public double getRoll();
		
	/**
	 * Returns currently-detected yaw (angle of horizontal spin).
	 */
	public double getYaw();
	
	/**
	 * Resets current yaw position to 0, as a reference point.
	 */
	public void resetYaw();
	
	/**
	 * Returns currently-detected yaw in degrees, wrapping around 360 to continue to 361+.
	 */
	public double getAngle();
	
	/**
	 * Resets current angle to 0.
	 */
	public void resetAngle();
	
	/**
	 * Prints angle data to console.
	 */
	public void printAngleData();

}
