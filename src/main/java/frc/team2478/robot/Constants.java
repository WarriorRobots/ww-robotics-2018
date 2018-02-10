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
public final class Constants {
	
	/**
	 * Contains PID constants. 
	 */
	public static final class ClosedLoop {
		// PID constants
		public static final double TURNING_P = 0.02325;
		public static final double TURNING_I = 0.0003; //turning
		public static final double TURNING_D = 0.025; //02225 bck
		public static final double COURSECORRECTION_I = 0.000; //course correction
		public static final double TURNING_TOLERANCE = 0.05;
		public static final double DISTANCE_P = 0.01;
		public static final double DISTANCE_I = 0;
		public static final double DISTANCE_D = 0;
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