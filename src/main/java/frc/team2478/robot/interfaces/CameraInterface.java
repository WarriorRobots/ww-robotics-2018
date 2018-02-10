package frc.team2478.robot.interfaces;

/**
 * Interface for camera devices.
 */
public interface CameraInterface {
	
	/**
	 * Returns true if the camera can see an object, and false otherwise.
	 */
	public boolean canSeeObject();
	
	/**
	 * Gets x-coordinate of the seen object.
	*/
	public double getObjectX();
	
	/**
	 * Gets y-coordinate of the seen object.
	 */
	public double getObjectY();
	
}
