package frc.team2478.robot.util;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import frc.team2478.robot.Constants;

public class SafetyHandler {
	
	private static SafetyHandler instance = null;
	
	public static SafetyHandler getInstance() {
		if (instance == null) {
			instance = new SafetyHandler();
		}
		return instance;
	}
	
	public void checkBatteryVoltage() {
		if (RobotController.getInputVoltage() < Constants.LOW_VOLTAGE_WARNING) {
			DriverStation.reportError("REPLACE BATTERY!" + Double.toString(RobotController.getInputVoltage()), false);
		}
	}
}