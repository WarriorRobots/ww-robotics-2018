package frc.team2478.robot.interfaces;

/**
 * This interface is for subsystems that use two or more motors running in the same direction.
 * @author westwood
 *
 */
public interface TandemMotorInterface {
	
	/**
	 * Sets the motors to a percentage of their maximum speed.
	 * @param percent  Percentage in decimal format, -1 to 1.
	 */
	public void runAtPercentage(double percent);
	
	/**
	 * Shuts off the motors and safely handles watchdog process.
	 */
	public void stop(); 
	
}
