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
	
	private static Position positionTarget = Position.MIDDLE;
	private static AutoTarget autoTarget = AutoTarget.SWITCH;
	
	public static enum Position {
		LEFT, MIDDLE, RIGHT
	}
	
	public static enum AutoTarget {
		SWITCH, SCALE
	}
	
	/**
	 * Places a reset button on the dashboard.
	 */
	public static void putResetButton() {
		SmartDashboard.putBoolean(RESET, false);
	}
	
	/**
	 * Gets the state of the user-generated Reset button.
	 * @return True if button is pressed, false otherwise.
	 */
	public static boolean getResetButton() {
		return SmartDashboard.getBoolean(RESET, false);
	}
	
	/**
	 * Places all editable autonomous widgets on the dashboard.
	 */
	public static void putAutonomoWidgets() {
//		SmartDashboard.putNumber(AUTO_DIST1, 500);
//		SmartDashboard.putNumber(AUTO_DIST2, 250);
//		SmartDashboard.putNumber(AUTO_TURN1, 90);
//		SmartDashboard.putNumber(AUTO_TURN2, 135);
	}
	
	/**
	 * Gets the first distance value from the dashboard.
	 * @return Double received from dashboard, 0 if missing
	 */
	public static double getDist1() {
		return SmartDashboard.getNumber(AUTO_DIST1, 500);
	}

	/**
	 * Gets the second distance value from the dashboard.
	 * @return Double received from dashboard, 0 if missing
	 */
	public static double getDist2() {
		return SmartDashboard.getNumber(AUTO_DIST2, 250);
	}

	/**
	 * Gets the first turn value from the dashboard.
	 * @return Double received from dashboard, 0 if missing
	 */
	public static double getTurn1() {
		return SmartDashboard.getNumber(AUTO_TURN1, 90);
	}

	/**
	 * Gets the second turn value from the dashboard.
	 * @return Double received from dashboard, 0 if missing
	 */
	public static double getTurn2() {
		return SmartDashboard.getNumber(AUTO_TURN2, 135);
	}

	/**
	* Places PID debug widgets on the dashboard.
	*/
	public static void putPidWidgets() {
		SmartDashboard.putNumber(P_GAIN, 500);
		SmartDashboard.putNumber(I_GAIN, 250);
		SmartDashboard.putNumber(D_GAIN, 90);
		SmartDashboard.putNumber(F_GAIN, 135);
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
	
	public static Position getPosition() {
//		return Robot.positionSelect.getSelected();
		return positionTarget;
	}
	
	public static AutoTarget getAutoTarget() {
//		return Robot.targetSelect.getSelected();
		return autoTarget;
	}
	
	public static void setPositionTarget(Position p) {
		positionTarget = p;
	}
	
	public static void setAutoTarget(AutoTarget a) {
		autoTarget = a;
	}
}