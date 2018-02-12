package frc.team2478.robot.util;

import edu.wpi.first.wpilibj.DriverStation;
import frc.team2478.robot.commands.autonomous.LefttoLeftScale;
import frc.team2478.robot.commands.autonomous.LefttoLeftSwitch;
import frc.team2478.robot.commands.autonomous.LefttoRightScale;
import frc.team2478.robot.commands.autonomous.LefttoRightSwitch;
import frc.team2478.robot.commands.autonomous.MidtoLeftScale;
import frc.team2478.robot.commands.autonomous.MidtoLeftSwitch;
import frc.team2478.robot.commands.autonomous.MidtoRightScale;
import frc.team2478.robot.commands.autonomous.MidtoRightSwitch;
import frc.team2478.robot.commands.autonomous.RighttoLeftScale;
import frc.team2478.robot.commands.autonomous.RighttoLeftSwitch;
import frc.team2478.robot.commands.autonomous.RighttoRightScale;
import frc.team2478.robot.commands.autonomous.RighttoRightSwitch;

public class AutonomoSelector {

	private static String gameData = null;

	private static boolean atLeftPos, atMiddlePos, atRightPos = false;
	private static boolean goToScale, goToSwitch = false;
	private static boolean switchOnLeft, switchOnRight = false;
	private static boolean scaleOnLeft, scaleOnRight = false;

	public static void selectCase() throws Exception {
		initData();

		if (atMiddlePos) {
			if (goToSwitch) {
				if (switchOnLeft) {
					new MidtoLeftSwitch().start();
				} else if (switchOnRight) {
					new MidtoRightSwitch().start();
				}
			} else if (goToScale) {
				if (scaleOnLeft) {
					new MidtoLeftScale().start();
				} else if (scaleOnRight) {
					new MidtoRightScale().start();
				}
			}
		} else if (atLeftPos) {
			if (goToSwitch) {
				if (switchOnLeft) {
					new LefttoLeftSwitch().start();
				} else if (switchOnRight) {
					new LefttoRightSwitch().start();
				}
			} else if (goToScale) {
				if (scaleOnLeft) {
					new LefttoLeftScale().start();
				} else if (scaleOnRight) {
					new LefttoRightScale().start();
				}
			}
		} else if (atRightPos) {
			if (goToSwitch) {
				if (switchOnLeft) {
					new RighttoLeftSwitch().start();
				} else if (switchOnRight) {
					new RighttoRightSwitch().start();
				}
			} else if (goToScale) {
				if (scaleOnLeft) {
					new RighttoLeftScale().start();
				} else if (scaleOnRight) {
					new RighttoRightScale().start();
				}
			}
		}

		resetData();
	}
	
	private static void initData() throws Exception {
		if (gameData.length() > 0) {
			gameData = DriverStation.getInstance().getGameSpecificMessage();
		} else {
			throw new Exception("Driver Station is missing autonomous data");
		}

		switch (DashboardHandler.getPosition()) {
		case MIDDLE:
			atMiddlePos = true;
			break;
		case LEFT:
			atLeftPos = true;
			break;
		case RIGHT:
			atRightPos = true;
			break;
		default:
			throw new Exception("Failed to get position");
		}

		switch (DashboardHandler.getAutoTarget()) {
		case SWITCH:
			goToSwitch = true;
			break;
		case SCALE:
			goToScale = true;
			break;
		default:
			throw new Exception("Failed to get intended target");
		}

		switch (gameData.charAt(0)) {
		case 'L':
			switchOnLeft = true;
			break;
		case 'R':
			switchOnRight = true;
			break;
		default:
			throw new Exception("Failed to get switch position");
		}

		switch (gameData.charAt(1)) {
		case 'L':
			scaleOnLeft = true;
			break;
		case 'R':
			scaleOnRight = true;
			break;
		default:
			throw new Exception("Failed to get scale position");
		}
	}


	private static void resetData() {
		gameData = null;
		atLeftPos = atMiddlePos = atRightPos = false;
		goToScale = goToSwitch = false;
		switchOnLeft = switchOnRight = false;
		scaleOnLeft = scaleOnRight = false;
	}
}