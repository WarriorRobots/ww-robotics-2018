package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * This class provides shorthand methods for end() and interrupted().
 * All autonomous Commands must extend this class.
 * For non-autonomous commands, extend {@link CommandBase}.
 */
public abstract class AutonomoBase extends CommandBase {
	
	/**
	 * Resets sensors. Call as super.
	 */
	protected void initialize() {
		motionSensors.resetAllSensors();
	}
	
	/**
	 * Stops drive and resets sensors. Call as super.
	 */
	protected void end() {
		drivetrain.stopDrive();
		motionSensors.resetAllSensors();
	}
	
	/**
	 * Reports interruption to Driver Station.
	 */
	protected void interrupted() {
		DriverStation.reportWarning("Autonomous sequence interrupted", false);
	}
}
