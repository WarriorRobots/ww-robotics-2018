package frc.team2478.robot.util;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2478.robot.util.enums.AutoTarget;
import frc.team2478.robot.util.enums.StartingPosition;

public class DashboardHandler {
	
	private static DashboardHandler instance = null;
	
	private static final String RESET = "RESET";
	
	private static StartingPosition positionTarget = StartingPosition.MIDDLE;
	private static AutoTarget autoTarget = AutoTarget.SWITCH;
	
	public static DashboardHandler getInstance() {
		if (instance == null) {
			instance = new DashboardHandler();
		} return instance;
	}
	
	private DashboardHandler() {
		// init selectors
	}

	public StartingPosition getPosition() {
//		return Robot.positionSelect.getSelected();
		return positionTarget;
	}
	
	public AutoTarget getAutoTarget() {
//		return Robot.targetSelect.getSelected();
		return autoTarget;
	}
	
	public void setPositionTarget(StartingPosition p) {
		positionTarget = p;
	}
	
	public void setAutoTarget(AutoTarget a) {
		autoTarget = a;
	}
	
	/**
	 * Places a reset button on the dashboard.
	 */
	public void putResetButton() {
		SmartDashboard.putBoolean(RESET, false);
	}
	
	/**
	 * Gets the state of the user-generated Reset button.
	 * @return True if button is pressed, false otherwise.
	 */
	public boolean getResetButton() {
		return SmartDashboard.getBoolean(RESET, false);
	}

}