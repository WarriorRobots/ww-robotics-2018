/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2478.robot;

/**
 * Contains all constants that are used in the program, grouped into subclasses.
 */
public final class RobotMap {
	
	public static final class DashboardStrings {
		// name of Dashboard Widgets
		public static final String RESET = "RESET";
		public static final String AUTO_DIST1 = "DIST1";
		public static final String AUTO_DIST2 = "DIST2";
		public static final String AUTO_TURN1 = "TURN1";
		public static final String AUTO_TURN2 = "TURN2";
	}

	/**
	 * Contains PID constants. 
	 */
	public static final class ClosedLoop {
		// PID constants
		public static final double TURNING_P = 0.02325;
		public static final double TURNING_I = 0.0003; //turning
		public static final double COURSECORRECTION_I = 0.000; //course correction
		public static final double TURNING_D = 0.02225; //01725 bck
		public static final double TURNING_TOLERANCE = 0.05;
	}
	
	/**
	 * Contains Motor / Talon IDs.
	 */
	public static final class Motors {
		// TalonSRX ID codes
		public static final int LEFT_FRONT = 2;
		public static final int LEFT_BACK = 4;
		public static final int RIGHT_FRONT = 1;
		public static final int RIGHT_BACK = 3;
	}

	/**
	 * Contains controller / USB IDs.
	 */
	public static final class Controllers {
		// Joystick / Controller USB ID codes
		public static final int LEFT_JOY = 1;
		public static final int RIGHT_JOY = 0;
		public static final int XBOX = 2;
	}

	/**
	 * Contains scaling factors for drive methods.
	 */
	public static final class DriveScalars {
		// alternate driving mode scalars
		public static final double ARCADE_FORWARDSPEED = 0.5;
		public static final double ARCADE_TURNSPEED = 0.5;
		public static final double LOCKMODE_TOLERANCE = 0.2;
//		public static final double TEST_PID_COURSECORRECTION = 0.6;
		// autonomous scalars
		public static final double AUTO_SPEED_FORWARDS = 0.4; //0.6
	}
	
	/**
	 * Contains IDs for the RoboRIO DIO ports.
	 */
	public static final class DioPorts {}
	
	/**
	 * Contains IDs for the RoboRIO PWM ports.
	 */
	public static final class PwmPorts {}
}