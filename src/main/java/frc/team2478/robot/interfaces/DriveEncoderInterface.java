package frc.team2478.robot.interfaces;

/**
 * Interface for a set of 2 encoders connected to the wheels of a differential-drive robot.
 */
public interface DriveEncoderInterface {
	
	/**
	 * Represents left and right side of drive encoders.
	 */
	public enum Side {
		LEFT,
		RIGHT
	}

	/**
	 * Returns encoder ticks of the side specified.
	 * @param side  LEFT or RIGHT
	 */
	public int getEncoderTicks(Side side);
	
	/**
	 * Resets encoder on specified side.
	 * @param side  LEFT or RIGHT
	 */
	public void resetEncoderTicks(Side side);

	/**
	 * Resets both encoders.
	 */
	public void resetEncoders();
	
	/**
	 * Prints out formatted string containing current encoder data.
	 */
	public void printEncoderData();

}