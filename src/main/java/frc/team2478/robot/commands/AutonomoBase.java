package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;

/**
 * This class provides shorthand methods for end() and interrupted().
 * All autonomous Commands must extend this class.
 */
public abstract class AutonomoBase extends Command {
	
	/**
	 * Resets sensors. Call as super.
	 */
	protected void initialize() {
		Robot.motionSensors.resetAllSensors();
	}
	
	/**
	 * Stops drive and resets sensors. Call as super.
	 */
	protected void end() {
		Robot.drivetrain.stopDrive();
		Robot.motionSensors.resetAllSensors();
	}
	
	/**
	 * Reports interruption to Driver Station.
	 */
	protected void interrupted() {
		DriverStation.reportWarning("Autonomous sequence interrupted", false);
	}
}
