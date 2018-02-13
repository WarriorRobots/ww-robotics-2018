package frc.team2478.robot.interfaces;

/**
 * Interface for controlling a differential drivetrain.
 */
public interface DrivetrainInterface extends DriveEncoderInterface, GyroscopeInterface, AccelerometerInterface {

	/**
	 * Drives robot in tank drive mode, with inputs squared for ease of human control.
	 * @param leftSpeed  Percentage value of left wheel speed.
	 * @param rightSpeed  Percentage value of right wheel speed.
	 */
	public void tankDriveSquared(double leftSpeed, double rightSpeed);

	/**
	 * Drives robot in tank drive mode, with inputs not squared for use in closed-loop control.
	 * @param leftSpeed  Percentage value of left wheel speed.
	 * @param rightSpeed  Percentage value of right wheel speed.
	 */
	public void tankDriveRaw(double leftSpeed, double rightSpeed);
	
	/**
	 * Drives robot in arcade mode, with inputs squared for ease of human control.
	 * @param forward  Percentage value of forwards/backwards speed.
	 * @param turn  Percentage value of turning speed.
	 */
	public void arcadeDriveSquared(double forward, double turn);
	
	/**
	 * Drives robot in arcade mode, with inputs not squared for use in closed-loop control
	 * @param forward  Percentage value of forwards/backwards speed.
	 * @param turn  Percentage value of turning speed.
	 */
	public void arcadeDriveRaw(double forward, double turn);
	
	/**
	 * Stops all drive motors, halting the robot.
	 */
	public void stopDrive();
	
}