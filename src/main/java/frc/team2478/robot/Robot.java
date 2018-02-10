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
	
	@Override
	public void robotInit() {
		oi = new ControlHandler();
	}
	
	@Override
	public void robotPeriodic() {
//		if (DashboardHandler.getResetButton()) {
//			DashboardHandler.putAutonomoWidgets();
//			DashboardHandler.putResetButton();
//		}
		if(RobotController.isBrownedOut()) {
			DriverStation.reportWarning("VOLTAGE WARNING: " + Double.toString(RobotController.getInputVoltage()) + "V", false);
		}
		System.out.println(RobotController.getInputVoltage());
	}
			

	@Override
	public void disabledInit() {
//		DashboardHandler.putResetButton();
		Scheduler.getInstance().removeAll();
	}

	@Override
	public void disabledPeriodic() {}

	@Override
	public void autonomousInit() {
		// use null detector to prevent injuries
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		try {
			if (DashboardHandler.getPosition() == Position.MIDDLE) {
				if (DashboardHandler.getAutoTarget() == AutoTarget.SWITCH) {
					if (gameData.charAt(0) == 'L') {
						System.out.println("middle to left switch");
					} else if (gameData.charAt(0) == 'R') {
						System.out.println("middle to right switch");
					} else {
						throw new Exception("Failed to get game-specific-message");
					}
				} else if (DashboardHandler.getAutoTarget() == AutoTarget.SCALE) {
					if (gameData.charAt(1) == 'L') {
						System.out.println("middle to left scale");
					} else if (gameData.charAt(1) == 'R') {
						System.out.println("middle to right scale");
					} else {
						throw new Exception("Failed to get game-specific-message");
					}
				} else {
					throw new Exception("Failed to choose target");
				}
			} else if (DashboardHandler.getPosition() == Position.LEFT) {
				if (DashboardHandler.getAutoTarget() == AutoTarget.SWITCH) {
					if (gameData.charAt(0) == 'L') {
						System.out.println("left to left switch");
					} else if (gameData.charAt(0) == 'R') {
						System.out.println("left to right switch");
					} else {
						throw new Exception("Failed to get game-specific-message");
					}
				} else if (DashboardHandler.getAutoTarget() == AutoTarget.SCALE) {
					if (gameData.charAt(1) == 'L') {
						System.out.println("left to left scale");
					} else if (gameData.charAt(1) == 'R') {
						System.out.println("left to right scale");
					} else {
						throw new Exception("Failed to get game-specific-message");
					}
				} else {
					throw new Exception("Failed to choose target");
				}
			} else if (DashboardHandler.getPosition() == Position.RIGHT) {
				if (DashboardHandler.getAutoTarget() == AutoTarget.SWITCH) {
					if (gameData.charAt(0) == 'L') {
						System.out.println("right to left switch");
					} else if (gameData.charAt(0) == 'R') {
						System.out.println("right to right switch");
					} else {
						throw new Exception("Failed to get game-specific-message");
					}
				} else if (DashboardHandler.getAutoTarget() == AutoTarget.SCALE) {
					if (gameData.charAt(1) == 'L') {
						System.out.println("right to left scale");
					} else if (gameData.charAt(1) == 'R') {
						System.out.println("right to right scale");
					} else {
						throw new Exception("Failed to get game-specific-message");
					}
				} else {
					throw new Exception("Failed to choose target");
				}
			} else {
				throw new Exception("Failed to choose position");
			}
		} catch (Exception e) {
			DriverStation.reportError(e.getMessage(), true);
		}
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