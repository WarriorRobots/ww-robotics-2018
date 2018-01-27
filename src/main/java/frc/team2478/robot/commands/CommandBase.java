package frc.team2478.robot.commands;

import frc.team2478.robot.OI;
import frc.team2478.robot.subsystems.DrivetrainSubsystem;
import frc.team2478.robot.subsystems.MotionSensorsSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This class instantiates the subsystems used by our robot.
 * All non-autonomous Commands must extend this class.
 * For autonomous Commands, extend {@link AutonomoBase}.
 */
public abstract class CommandBase extends Command {
	
	protected static OI oi;

	protected static final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
	protected static final MotionSensorsSubsystem motionSensors = new MotionSensorsSubsystem();
	
	/**
	 * Initializes OI.java and all subsystems.
	 */
	public static void init() {
		oi = new OI();
		drivetrain.init();
		motionSensors.init();
	}
	
}
