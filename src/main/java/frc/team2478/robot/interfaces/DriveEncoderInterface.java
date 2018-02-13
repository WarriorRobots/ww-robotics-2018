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
	 * Returns current ticks of encoder on side specified.
	 * @param side  Side to retrieve ticks from, {@code Side.LEFT} or {@code Side.RIGHT}
	 */
	public int getEncoderTicks(Side side);
	
	/**
	 * Resets encoder ticks on specified side to 0.
	 * @param side  Side to reset ticks on, {@code Side.LEFT} or {@code Side.RIGHT}
	 */
	public void resetEncoderTicks(Side side);

	/**
	 * Resets both encoders to 0.
	 */
	public void resetEncoders();
	
	/**
	 * Prints out formatted string containing current encoder data.
	 */
	public void printEncoderData();

}