package frc.team2478.robot.interfaces;

/**
 * Interface for controlling sets of motors that run in tandem (ie. shooters, intakes, climbers).
 */
public interface MotorInterface {
	
	/**
	 * Sets motor speed to percentage value, from -1.0 to 1.0.
	 */
	public void setTargetPercentage(double percentage);
	
	/**
	 * Stops motors from running.
	 */
	public void stop();

}