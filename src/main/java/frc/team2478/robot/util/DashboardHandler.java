package frc.team2478.robot.util;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2478.robot.util.enums.AutoTarget;
import frc.team2478.robot.util.enums.NumberOfCubes;
import frc.team2478.robot.util.enums.StartingPosition;

/**
 * Sends miscellaneous data to the dashboard. Call in robotInit() of Robot.java
 */
public class DashboardHandler {
	
	private static DashboardHandler instance = null;
	
	@SuppressWarnings("unused")
	private static StartingPosition positionTarget = StartingPosition.MIDDLE;
	@SuppressWarnings("unused")
	private static AutoTarget autoTarget = AutoTarget.SWITCH;
	
	private static SendableChooser<StartingPosition> positionDropdown;
	private static SendableChooser<AutoTarget> targetDropdown;
	private static SendableChooser<NumberOfCubes> cubeNumberDropdown;
	
	private DashboardHandler() {
		positionDropdown = new SendableChooser<>();
		positionDropdown.addDefault("MIDDLE", StartingPosition.MIDDLE);
		positionDropdown.addObject("LEFT", StartingPosition.LEFT);
		positionDropdown.addObject("RIGHT", StartingPosition.RIGHT);
		targetDropdown = new SendableChooser<>();
		targetDropdown.addDefault("SWITCH", AutoTarget.SWITCH);
		targetDropdown.addObject("SCALE", AutoTarget.SCALE);
		targetDropdown.addObject("CROSS LINE", AutoTarget.LINE);
		cubeNumberDropdown = new SendableChooser<>();
		cubeNumberDropdown.addDefault("ONE CUBE", NumberOfCubes.ONE);
		cubeNumberDropdown.addObject("TWO CUBES", NumberOfCubes.TWO);
	}
	
	public void init() {
		SmartDashboard.putData("Position Selector", positionDropdown);
		SmartDashboard.putData("Target Selector", targetDropdown);
		SmartDashboard.putData("Number of Cubes Selector", cubeNumberDropdown);
	}
	
	public static DashboardHandler getInstance() {
		if (instance == null) {
			instance = new DashboardHandler();
		} return instance;
	}

	public StartingPosition getStartingPosition() {
		return positionDropdown.getSelected();
	}
	
	public AutoTarget getAutoTarget() {
		return targetDropdown.getSelected();
	}
	
	public NumberOfCubes getNumberOfCubes() {
		return cubeNumberDropdown.getSelected();
	}
	
}