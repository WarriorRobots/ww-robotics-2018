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
import frc.team2478.robot.commands.AutonomoGroupTest;
import frc.team2478.robot.subsystems.DriveEncoderSubsystem;
import frc.team2478.robot.subsystems.DrivetrainSubsystem;
import frc.team2478.robot.subsystems.LimelightSubsystem;
import frc.team2478.robot.subsystems.NavxSubsystem;
import frc.team2478.robot.util.ControlHandler;
import frc.team2478.robot.util.DashboardHandler;
import frc.team2478.robot.util.DashboardHandler.AutoTarget;
import frc.team2478.robot.util.DashboardHandler.Position;

public class Robot extends TimedRobot {
	
	String gameData;
		
	public static final DriveEncoderSubsystem encoders = new DriveEncoderSubsystem();
	public static final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
	public static final NavxSubsystem gyro = new NavxSubsystem();
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
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		try {
			if(gameData.length() > 0) {
				if (DashboardHandler.getPosition() == Position.MIDDLE) {
					if (DashboardHandler.getAutoTarget() == AutoTarget.SWITCH) {
						if (gameData.charAt(0) == 'L') {
							DriverStation.reportWarning("middle to left switch", false);
						} else if (gameData.charAt(0) == 'R') {
							DriverStation.reportWarning("middle to right switch", false);
						} else {
							throw new Exception("Failed to get game-specific-message");
						}
					} else if (DashboardHandler.getAutoTarget() == AutoTarget.SCALE) {
						if (gameData.charAt(1) == 'L') {
							DriverStation.reportWarning("middle to left scale", false);
						} else if (gameData.charAt(1) == 'R') {
							DriverStation.reportWarning("middle to right scale", false);
						} else {
							throw new Exception("Failed to get game-specific-message");
						}
					} else {
						throw new Exception("Failed to choose target");
					}
				} else if (DashboardHandler.getPosition() == Position.LEFT) {
					if (DashboardHandler.getAutoTarget() == AutoTarget.SWITCH) {
						if (gameData.charAt(0) == 'L') {
							DriverStation.reportWarning("left to left switch", false);
						} else if (gameData.charAt(0) == 'R') {
							DriverStation.reportWarning("left to right switch", false);
						} else {
							throw new Exception("Failed to get game-specific-message");
						}
					} else if (DashboardHandler.getAutoTarget() == AutoTarget.SCALE) {
						if (gameData.charAt(1) == 'L') {
							DriverStation.reportWarning("left to left scale", false);
						} else if (gameData.charAt(1) == 'R') {
							DriverStation.reportWarning("left to right scale", false);
						} else {
							throw new Exception("Failed to get game-specific-message");
						}
					} else {
						throw new Exception("Failed to choose target");
					}
				} else if (DashboardHandler.getPosition() == Position.RIGHT) {
					if (DashboardHandler.getAutoTarget() == AutoTarget.SWITCH) {
						if (gameData.charAt(0) == 'L') {
							DriverStation.reportWarning("right to left switch", false);
						} else if (gameData.charAt(0) == 'R') {
							DriverStation.reportWarning("right to right switch", false);
						} else {
							throw new Exception("Failed to get game-specific-message");
						}
					} else if (DashboardHandler.getAutoTarget() == AutoTarget.SCALE) {
						if (gameData.charAt(1) == 'L') {
							DriverStation.reportWarning("right to left scale", false);
						} else if (gameData.charAt(1) == 'R') {
							DriverStation.reportWarning("right to right scale", false);
						} else {
							throw new Exception("Failed to get game-specific-message");
						}
					} else {
						throw new Exception("Failed to choose target");
					}
				} else {
					throw new Exception("Failed to choose position");
				}
			} else {
				DriverStation.reportError("string too short", false);
			}
		} catch (Exception e) {
			DriverStation.reportError(e.getMessage(), true);
		}
		
		new AutonomoGroupTest().start();
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