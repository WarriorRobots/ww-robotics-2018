package frc.team2478.robot.util;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2478.robot.RobotMap;

public class DashboardHandler {
	
	/**
	 * Places a reset button on the dashboard.
	 */
	public static void putResetButton() {
		SmartDashboard.putBoolean("RESET", false);
	}
	
	/**
	 * Gets the state of the user-generated Reset button.
	 * 
	 * @return True if button is pressed, false if not
	 */
	public static boolean getResetButton() {
		return SmartDashboard.getBoolean("RESET", false);
	}
	
	/**
	 * Places all editable widgets on dashboard.
	 */
	public static void putWidgets() {
		SmartDashboard.putNumber(RobotMap.DashboardStrings.AUTO_DIST1, 500);
		SmartDashboard.putNumber(RobotMap.DashboardStrings.AUTO_DIST2, 250);
		SmartDashboard.putNumber(RobotMap.DashboardStrings.AUTO_TURN1, 90);
		SmartDashboard.putNumber(RobotMap.DashboardStrings.AUTO_TURN2, 135);
	}
	
}
