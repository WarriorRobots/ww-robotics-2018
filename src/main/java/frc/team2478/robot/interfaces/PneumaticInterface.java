package frc.team2478.robot.interfaces;

/**
 * Interface for controlling pneumatic solenoids.
 */
public interface PneumaticInterface {
	
	/**
	 * Represents possible states of a solenoid.
	 */
	public enum Mode {
		FORWARD,
		REVERSE,
		OFF
	}
	
	/**
	 * Sets piston to the provided state.
	 * @param mode  FORWARD, REVERSE, or OFF
	 */
	public void setPistonState(Mode mode);
}