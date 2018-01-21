package frc.team2478.robot.commands;

import frc.team2478.robot.OI;
import frc.team2478.robot.subsystems.DrivetrainSubsystem;
import frc.team2478.robot.subsystems.MotionSensorsSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public abstract class CommandBase extends Command {
	
	public static OI oi;

	public static final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
	public static final MotionSensorsSubsystem motionSensors = new MotionSensorsSubsystem();
	
	public CommandBase(String name) {
		super(name);
	}
	
	public CommandBase() {
		super();
	}
	
	public static void init() {
		oi = new OI();
		drivetrain.init();
		motionSensors.init();
		SmartDashboard.putData("SchedulerData", Scheduler.getInstance());
	}
	
}
