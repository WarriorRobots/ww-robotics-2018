package frc.team2478.robot;

import edu.wpi.first.wpilibj.DriverStation;
import frc.team2478.robot.commands.autonomous.MidtoLeftScale;
import frc.team2478.robot.commands.autonomous.MidtoLeftSwitch;
import frc.team2478.robot.commands.autonomous.MidtoRightScale;
import frc.team2478.robot.commands.autonomous.MidtoRightSwitch;
import frc.team2478.robot.util.DashboardHandler;
import frc.team2478.robot.util.DashboardHandler.AutoTarget;
import frc.team2478.robot.util.DashboardHandler.Position;

public class AutoRobot {
	
	private static String gameData;
	
	public static void selectCase() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		try {
			if(gameData.length() > 0) {
				if (DashboardHandler.getPosition() == Position.MIDDLE) {
					if (DashboardHandler.getAutoTarget() == AutoTarget.SWITCH) {
						if (gameData.charAt(0) == 'L') {
							DriverStation.reportWarning("middle to left switch", false);
							new MidtoLeftSwitch().start();
						} else if (gameData.charAt(0) == 'R') {
							DriverStation.reportWarning("middle to right switch", false);
							new MidtoRightSwitch().start();
							
						} else {
							throw new Exception("Failed to get game-specific-message");
						}
					} else if (DashboardHandler.getAutoTarget() == AutoTarget.SCALE) {
						if (gameData.charAt(1) == 'L') {
							DriverStation.reportWarning("middle to left scale", false);
							new MidtoLeftScale().start();
						} else if (gameData.charAt(1) == 'R') {
							DriverStation.reportWarning("middle to right scale", false);
							new MidtoRightScale().start();
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
	}

}
