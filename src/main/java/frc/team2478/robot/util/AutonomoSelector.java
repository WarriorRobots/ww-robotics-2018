package frc.team2478.robot.util;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
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

	/**
	 * Selects autonomous case and calls {@code start()} on the chosen {@code CommandGroup}.
	 */
	public static void selectAutoCase() {
		initData();
		Command autoCommand = null;
		
		if (atMiddlePos) {
			if (goToSwitch) {
				if (switchOnLeft) {
					autoCommand = new MidtoLeftSwitch();
				} else if (switchOnRight) {
					autoCommand = new MidtoRightSwitch();
				}
			} else if (goToScale) {
				if (scaleOnLeft) {
					autoCommand = new MidtoLeftScale();
				} else if (scaleOnRight) {
					autoCommand = new MidtoRightScale();
				}
			}
		} else if (atLeftPos) {
			if (goToSwitch) {
				if (switchOnLeft) {
					autoCommand = new LefttoLeftSwitch();
				} else if (switchOnRight) {
					autoCommand = new LefttoRightSwitch();
				}
			} else if (goToScale) {
				if (scaleOnLeft) {
					autoCommand = new LefttoLeftScale();
				} else if (scaleOnRight) {
					autoCommand = new LefttoRightScale();
				}
			}
		} else if (atRightPos) {
			if (goToSwitch) {
				if (switchOnLeft) {
					autoCommand = new RighttoLeftSwitch();
				} else if (switchOnRight) {
					autoCommand = new RighttoRightSwitch();
				}
			} else if (goToScale) {
				if (scaleOnLeft) {
					autoCommand = new RighttoLeftScale();
				} else if (scaleOnRight) {
					autoCommand = new RighttoRightScale();
				}
			}
		}

		autoCommand.start();
		
		resetData();
	}
	
	private static void initData() {
		resetData();
		gameData = DriverStation.getInstance().getGameSpecificMessage();

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
		}

		switch (DashboardHandler.getAutoTarget()) {
		case SWITCH:
			goToSwitch = true;
			break;
		case SCALE:
			goToScale = true;
			break;
		}

		switch (gameData.charAt(0)) {
		case 'L':
			switchOnLeft = true;
			break;
		case 'R':
			switchOnRight = true;
			break;
		}

		switch (gameData.charAt(1)) {
		case 'L':
			scaleOnLeft = true;
			break;
		case 'R':
			scaleOnRight = true;
			break;
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