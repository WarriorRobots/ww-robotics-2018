/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2478.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.team2478.robot.subsystems.DrivetrainSubsystem;
import frc.team2478.robot.subsystems.LimelightSubsystem;
import frc.team2478.robot.util.AutonomoSelector;
import frc.team2478.robot.util.ControlHandler;
import frc.team2478.robot.util.DashboardHandler;
import frc.team2478.robot.util.DashboardHandler.AutoTarget;
import frc.team2478.robot.util.DashboardHandler.Position;

public class Robot extends TimedRobot {
		
	public static final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
	public static final LimelightSubsystem limelight = new LimelightSubsystem();
	public static ControlHandler oi;
	
	public static SendableChooser<Position> positionSelect = new SendableChooser<>();
	public static SendableChooser<AutoTarget> targetSelect = new SendableChooser<>();
	
	@Override
	public void robotInit() {
		oi = new ControlHandler();
//		Robot.positionSelect.addObject("left position", Position.LEFT);
//		Robot.positionSelect.addObject("middle position", Position.MIDDLE);
//		Robot.positionSelect.addObject("right position", Position.RIGHT);
//		Robot.targetSelect.addObject("switch", AutoTarget.SWITCH);
//		Robot.targetSelect.addObject("scale", AutoTarget.SCALE);
//		SmartDashboard.putData(Robot.positionSelect);
//		SmartDashboard.putData(Robot.targetSelect);
	}
	
	@Override
	public void robotPeriodic() {
//		if (DashboardHandler.getResetButton()) {
//			DashboardHandler.putAutonomoWidgets();
//			DashboardHandler.putResetButton();
//		}
		if (RobotController.getInputVoltage() < 9) {
//			DriverStation.reportWarning("VOLTAGE WARNING: " + Double.toString(RobotController.getInputVoltage()) + "V", false);
			DriverStation.reportError("REPLACE BATTERY!!!! " + Double.toString(RobotController.getInputVoltage()), false);
		}
	}
			

	@Override
	public void disabledInit() {
//		DashboardHandler.putResetButton();
		Scheduler.getInstance().removeAll();
		DashboardHandler.putAutonomoWidgets();
	}

	@Override
	public void disabledPeriodic() {}

	@Override
	public void autonomousInit() {
		// use null detector to prevent injuries
		AutonomoSelector.selectCase();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
//		Scheduler.getInstance().removeAll();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testInit() {}
	
	@Override
	public void testPeriodic() {}
}