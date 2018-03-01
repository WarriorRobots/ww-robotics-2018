package frc.team2478.robot.util;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.commands.autonomous.routines.LefttoLeftScale;
import frc.team2478.robot.commands.autonomous.routines.LefttoLeftSwitch;
import frc.team2478.robot.commands.autonomous.routines.LefttoRightScale;
import frc.team2478.robot.commands.autonomous.routines.LefttoRightSwitch;
import frc.team2478.robot.commands.autonomous.routines.MidtoLeftScale;
import frc.team2478.robot.commands.autonomous.routines.MidtoLeftSwitch;
import frc.team2478.robot.commands.autonomous.routines.MidtoRightScale;
import frc.team2478.robot.commands.autonomous.routines.MidtoRightSwitch;
import frc.team2478.robot.commands.autonomous.routines.RighttoLeftScale;
import frc.team2478.robot.commands.autonomous.routines.RighttoLeftSwitch;
import frc.team2478.robot.commands.autonomous.routines.RighttoRightScale;
import frc.team2478.robot.commands.autonomous.routines.RighttoRightSwitch;
import frc.team2478.robot.commands.autonomous.routines.TestAutonomo;

public class AutonomoSelector {

	private static AutonomoSelector instance = null;
	
	private String gameData = null;
	private Command autoCommand = null;

	private boolean atLeftPos, atMiddlePos, atRightPos = false;
	private boolean goToScale, goToSwitch = false;
	private boolean switchOnLeft, switchOnRight = false;
	private boolean scaleOnLeft, scaleOnRight = false;

	public static AutonomoSelector getInstance() {
		if (instance == null) {
			instance = new AutonomoSelector();
		}
		return instance;
	}
	
	public void chooseTest() {
		autoCommand = new TestAutonomo();
	}
	
	public void stopAuto() {
		autoCommand.cancel();
	}
	
	/**
	 * Selects autonomous case and calls {@code start()} on the chosen {@code CommandGroup}.
	 */
	public void selectAutoCase() {
		initData();
		
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
	}
	
	public void startAuto() {
//		new LowerHood().start(); // shooter must be lowered in order to drive safely
		autoCommand.start();
	}
	
	private void initData() {
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


	private void resetData() {
		gameData = null;
		atLeftPos = atMiddlePos = atRightPos = false;
		goToScale = goToSwitch = false;
		switchOnLeft = switchOnRight = false;
		scaleOnLeft = scaleOnRight = false;
	}
}