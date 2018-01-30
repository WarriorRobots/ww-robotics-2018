package frc.team2478.robot.util;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashboardHandler {
	
	private static final String RESET = "RESET";
	private static final String AUTO_DIST1 = "DIST1";
	private static final String AUTO_DIST2 = "DIST2";
	private static final String AUTO_TURN1 = "TURN1";
	private static final String AUTO_TURN2 = "TURN2";
	private static final String P_GAIN = "P gain";
	private static final String I_GAIN = "I gain";
	private static final String D_GAIN = "D gain";	
	private static final String F_GAIN = "F gain";
	
	/**
	 * Places a reset button on the dashboard.
	 */
	public static void putResetButton() {
		SmartDashboard.putBoolean(RESET, false);
	}
	
	/**
	 * Gets the state of the user-generated Reset button.
	 * 
	 * @return True if button is pressed, false if not
	 */
	public static boolean getResetButton() {
		return SmartDashboard.getBoolean(RESET, false);
	}
	
	/**
	 * Places all editable autonomous widgets on the dashboard.
	 */
	public static void putAutonomoWidgets() {
		SmartDashboard.putNumber(AUTO_DIST1, 500);
		SmartDashboard.putNumber(AUTO_DIST2, 250);
		SmartDashboard.putNumber(AUTO_TURN1, 90);
		SmartDashboard.putNumber(AUTO_TURN2, 135);
	}
	
	@Deprecated
	public static double getDouble(String key, double defaultval) {
		return SmartDashboard.getNumber(key, defaultval);
	}
	
	/**
	 * Gets the first distance value from the dashboard.
	 * @return Double received from dashboard, 0 if missing
	 */
	public static double getDist1() {
		return SmartDashboard.getNumber(AUTO_DIST1, 0);
	}

	/**
	 * Gets the second distance value from the dashboard.
	 * @return Double received from dashboard, 0 if missing
	 */
	public static double getDist2() {
		return SmartDashboard.getNumber(AUTO_DIST2, 0);
	}

	/**
	 * Gets the first turn value from the dashboard.
	 * @return Double received from dashboard, 0 if missing
	 */
	public static double getTurn1() {
		return SmartDashboard.getNumber(AUTO_TURN1, 0);
	}

	/**
	 * Gets the second turn value from the dashboard.
	 * @return Double received from dashboard, 0 if missing
	 */
	public static double getTurn2() {
		return SmartDashboard.getNumber(AUTO_TURN2, 0);
	}

	/**
	* Places PID debug widgets on the dashboard.
	*/
	public static void putPidWidgets() {
		SmartDashboard.putNumber(P_GAIN, 0);
		SmartDashboard.putNumber(I_GAIN, 0);
		SmartDashboard.putNumber(D_GAIN, 0);
		SmartDashboard.putNumber(F_GAIN, 0);
	}

	/**
	 * Gets proportional gain from dashboard.
	 * @return Double received from dashboard, 0 if missing
	 */
	public static double getP() {
		return SmartDashboard.getNumber(P_GAIN, 0);
	}

	/**
	 * Gets integral gain from dashboard.
	 * @return Double received from dashboard, 0 if missing
	 */
	public static double getI() {
		return SmartDashboard.getNumber(I_GAIN, 0);
	}

	/**
	 * Gets derivative gain from dashboard.
	 * @return Double received from dashboard, 0 if missing
	 */
	public static double getD() {
		return SmartDashboard.getNumber(D_GAIN, 0);
	}

	/**
	 * Gets feed-forward gain from dashboard.
	 * @return Double received from dashboard, 0 if missing
	 */
	public static double getF() {
		return SmartDashboard.getNumber(F_GAIN, 0);
	}
	
}