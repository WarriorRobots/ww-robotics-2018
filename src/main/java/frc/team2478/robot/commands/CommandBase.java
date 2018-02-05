package frc.team2478.robot.commands;

import frc.team2478.robot.OI;
import frc.team2478.robot.subsystems.LimelightSubsystem;
import frc.team2478.robot.subsystems.DrivetrainSubsystem;
import frc.team2478.robot.subsystems.NavxSubsystem;

/**
 * This interface instantiates the subsystems used by our robot.
 * All Commands must implement this interface.
 */

public interface CommandBase {
	
	public static OI oi = new OI();

	public static final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
	public static final NavxSubsystem motionSensors = new NavxSubsystem();
	public static final LimelightSubsystem limelight = new LimelightSubsystem();
	
//	protected static final FeedSubsystem feed = new FeedSubsystem();
//	protected static final IntakeSubsystem intake = new IntakeSubsystem();
//	protected static final ShooterSubsystem shooter = new ShooterSubsystem();
//	protected static final PneumaticSubsystem pneumatics = new PneumaticSubsystem();
	
}