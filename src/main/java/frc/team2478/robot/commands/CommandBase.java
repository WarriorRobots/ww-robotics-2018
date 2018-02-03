package frc.team2478.robot.commands;

import frc.team2478.robot.OI;
import frc.team2478.robot.subsystems.CameraSubsystem;
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
	protected static final CameraSubsystem limelight = new CameraSubsystem();
	
//	protected static final FeedSubsystem feed = new FeedSubsystem();
//	protected static final IntakeSubsystem intake = new IntakeSubsystem();
//	protected static final ShooterSubsystem shooter = new ShooterSubsystem();
//	protected static final PneumaticSubsystem pneumatics = new PneumaticSubsystem();
	
	/**
	 * Initializes OI.java and all subsystems.
	 */
	public static void init() {
		oi = new OI();
		drivetrain.init();
		motionSensors.init();
		limelight.init();
		
//		feed.init();
//		intake.init();
//		shooter.init();
//		pneumatics.init();
	}
	
}