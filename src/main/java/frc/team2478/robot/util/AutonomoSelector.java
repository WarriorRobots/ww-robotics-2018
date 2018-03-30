package frc.team2478.robot.util;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.commands.autonomous.routines.CrossLine;
import frc.team2478.robot.commands.autonomous.routines.LefttoLeftScale;
import frc.team2478.robot.commands.autonomous.routines.LefttoLeftSwitch;
import frc.team2478.robot.commands.autonomous.routines.LefttoRightScale;
import frc.team2478.robot.commands.autonomous.routines.LefttoRightSwitch;
import frc.team2478.robot.commands.autonomous.routines.MidtoLeftSwitch;
import frc.team2478.robot.commands.autonomous.routines.MidtoRightSwitch;
import frc.team2478.robot.commands.autonomous.routines.RighttoLeftScale;
import frc.team2478.robot.commands.autonomous.routines.RighttoLeftSwitch;
import frc.team2478.robot.commands.autonomous.routines.RighttoRightScale;
import frc.team2478.robot.commands.autonomous.routines.RighttoRightSwitch;
import frc.team2478.robot.commands.autonomous.routines.TestCase;
import frc.team2478.robot.commands.autonomous.routines.unfinished.MidtoLeftScale;
import frc.team2478.robot.commands.autonomous.routines.unfinished.MidtoRightScale;

/**
 * Contains methods that select the Autonomous case of the robot.
 * Place these methods inside autonomousInit() of Robot.java
 */
public class AutonomoSelector {

	private static AutonomoSelector instance = null;
	
	private String gameData = null;
	private Command autoCommand = null;

	private boolean atLeftPos, atMiddlePos, atRightPos = false;
	private boolean goToScale, goToSwitch, goToLine = false;
	private boolean switchOnLeft, switchOnRight = false;
	private boolean scaleOnLeft, scaleOnRight = false;
	
	public static AutonomoSelector getInstance() {
		if (instance == null) {
			instance = new AutonomoSelector();
		}
		return instance;
	}
	
	public void selectTestCase() {
		autoCommand = new TestCase();
	}
	
	public void stopAuto() {
		autoCommand.cancel();
	}
	
	/**
	 * Selects autonomous case and calls {@code start()} on the chosen {@code CommandGroup}.
	 */
	public void selectAutoCase() {
		initData();
		
		if (goToLine) {
				autoCommand = new CrossLine();
				DriverStation.reportWarning("CrossLine, I choose you!", false);
		} else if (atMiddlePos) {
			if (goToSwitch) {
				if (switchOnLeft) {
					autoCommand = new MidtoLeftSwitch();
					DriverStation.reportWarning("MidtoLeftSwitch, I choose you!", false);
				} else if (switchOnRight) {
					autoCommand = new MidtoRightSwitch();
					DriverStation.reportWarning("MidtoRightSwitch, I choose you!", false);
				}
			} else if (goToScale) {
				if (scaleOnLeft) {
					autoCommand = new MidtoLeftScale();
					DriverStation.reportWarning("MidtoLeftScale, I choose you!", false);
				} else if (scaleOnRight) {
					autoCommand = new MidtoRightScale();
					DriverStation.reportWarning("MidtoRightScale, I choose you!", false);
				}
			}
		} else if (atLeftPos) {
			if (goToSwitch) {
				if (switchOnLeft) {
					autoCommand = new LefttoLeftSwitch();
					DriverStation.reportWarning("LefttoLeftSwitch, I choose you!", false);
				} else if (switchOnRight) {
					autoCommand = new LefttoRightSwitch();
					DriverStation.reportWarning("LefttoRightSwitch, I choose you!", false);
				}
			} else if (goToScale) {
				if (scaleOnLeft) {
					autoCommand = new LefttoLeftScale();
					DriverStation.reportWarning("LefttoLeftScale, I choose you!", false);
				} else if (scaleOnRight) {
					autoCommand = new LefttoRightScale();
					DriverStation.reportWarning("LefttoRightScale, I choose you!", false);
				}
			}
		} else if (atRightPos) {
			if (goToSwitch) {
				if (switchOnLeft) {
					autoCommand = new RighttoLeftSwitch();
					DriverStation.reportWarning("RighttoLeftSwitch, I choose you!", false);
				} else if (switchOnRight) {
					autoCommand = new RighttoRightSwitch();
					DriverStation.reportWarning("RighttoRightSwitch, I choose you!", false);
				}
			} else if (goToScale) {
				if (scaleOnLeft) {
					autoCommand = new RighttoLeftScale();
					DriverStation.reportWarning("RighttoLeftScale, I choose you!", false);
				} else if (scaleOnRight) {
					autoCommand = new RighttoRightScale();
					DriverStation.reportWarning("RighttoRightScale, I choose you!", false);
				}
			}
		} else {
			DriverStation.reportError("FAILURE TO CHOOSE AUTO", false);
		}
	}
	
	public void startAuto() {
		try {
			autoCommand.start();
		} catch (Exception e) {
			DriverStation.reportError(e.getMessage(), false);
		}
	}
	
	private void initData() {
		resetData();
		gameData = DriverStation.getInstance().getGameSpecificMessage();

		switch (DashboardHandler.getInstance().getStartingPosition()) {
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

		switch (DashboardHandler.getInstance().getAutoTarget()) {
		case SWITCH:
			goToSwitch = true;
			break;
		case SCALE:
			goToScale = true;
			break;
		case LINE:
			goToLine = true;
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
		goToScale = goToSwitch = goToLine = false;
		switchOnLeft = switchOnRight = false;
		scaleOnLeft = scaleOnRight = false;
	}
}