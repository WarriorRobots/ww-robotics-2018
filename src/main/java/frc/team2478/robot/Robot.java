/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2478.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2478.robot.subsystems.DrivetrainSubsystem;
import frc.team2478.robot.subsystems.FeedSubsystem;
import frc.team2478.robot.subsystems.LimelightSubsystem;
import frc.team2478.robot.subsystems.PickupSubsystem;
import frc.team2478.robot.subsystems.PneumaticSubsystem;
import frc.team2478.robot.subsystems.ShooterSubsystem;
import frc.team2478.robot.util.AutonomoSelector;
import frc.team2478.robot.util.ControlHandler;
import frc.team2478.robot.util.DashboardHandler;
import frc.team2478.robot.util.enums.AutoTarget;
import frc.team2478.robot.util.enums.StartingPosition;

public class Robot extends TimedRobot {
	
	public static final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
	public static final ShooterSubsystem shooter = new ShooterSubsystem();
	public static final PickupSubsystem pickup = new PickupSubsystem();
	public static final FeedSubsystem feed = new FeedSubsystem();
	public static final PneumaticSubsystem pneumatics = new PneumaticSubsystem();
	public static final LimelightSubsystem vision = new LimelightSubsystem();
	public static ControlHandler oi;
	
//	public static SendableChooser<V>
	
	
	@Override
	public void robotInit() {
		oi = new ControlHandler();
		oi.init();
		SmartDashboard.putData(drivetrain);
		SmartDashboard.putData(pneumatics);
		SmartDashboard.putData(Scheduler.getInstance());
//		SmartDashboard.putData(vision);
		SmartDashboard.putData(shooter);
//		SmartDashboard.putData(feed);
//		SmartDashboard.putData(pickup);
	}
	
	@Override
	public void robotPeriodic() {
	//	SafetyHandler.getInstance().checkBatteryVoltage();
	}
			

	@Override
	public void disabledInit() {
		drivetrain.setReversed(false);
		Scheduler.getInstance().removeAll();
	}
	
	@Override
	public void teleopInit() {
	}
	
	@Override
	public void autonomousInit() {
		drivetrain.setReversed(false);
		Scheduler.getInstance().removeAll();
		DashboardHandler.getInstance().setPositionTarget(StartingPosition.RIGHT);
		DashboardHandler.getInstance().setAutoTarget(AutoTarget.SWITCH);
		AutonomoSelector.getInstance().selectAutoCase();
		AutonomoSelector.getInstance().startAuto();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("speed", shooter.getNativeUnitVelocity());
	}

}