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
import frc.team2478.robot.util.DashboardHandler.Position;
import frc.team2478.robot.util.DashboardHandler.Priority;

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
		try {
			gameData = DriverStation.getInstance().getGameSpecificMessage();
			if (DashboardHandler.getPosition() == Position.MIDDLE) {
				if (gameData.charAt(0) == 'L') {
					// left switch
				} else if (gameData.charAt(0) == 'R') {
					// right switch
				} else {
					// cross line
				}
			} else if (DashboardHandler.getPosition() == Position.LEFT) {
				if (gameData.charAt(1) == 'L') {
					// left scale
				} else if (gameData.charAt(1) == 'R') {
					if (DashboardHandler.getPriority() == Priority.HIGH) {
						// right scale
					} else if (DashboardHandler.getPriority() == Priority.LOW) {
						// cross line
					} else {
						// cross line
					}
				}
			} else if (DashboardHandler.getPosition() == Position.RIGHT) {
				if (gameData.charAt(1) == 'R') {
					// right scale
				} else if (gameData.charAt(1) == 'L') {
					if (DashboardHandler.getPriority() == Priority.HIGH) {
						// left scale
					} else if (DashboardHandler.getPriority() == Priority.LOW) {
						// cross line
					}
				}
			} else {
				throw new Exception("skipped the main if statement");
			}
		} catch (Exception e) {
			DriverStation.reportError("Failure to parse Driver Station autonomous data: " + e.getMessage(), true);
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