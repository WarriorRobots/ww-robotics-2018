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
	 * Contains PID constants used for closed-loop control.
	 */
	public static final class ClosedLoop {
		/**
		 * P value for the loop used while turning.
		 */
		public static final double TURNING_P = 0.017;
		
		/**
		 * I value for the loop used while turning.
		 */
		public static final double TURNING_I = 0.0015;
		
		/**
		 * D value for the loop used while turning.
		 */
		public static final double TURNING_D = 0.02225;
		
		/**
		 * I value for the loop used while driving straight.
		 */
		public static final double COURSECORRECTION_I = 0.000;
		
		/**
		 * Tolerance of the PID loop used while turning.
		 */
		public static final double TURNING_TOLERANCE = 0.25;
		
		/**
		 * Tolerance of the PID loop used for measuring distance driven.
		 */
		public static final double DISTANCE_TOLERANCE = 5;
		
		/**
		 * P value for the loop used while driving forwards.
		 */
		public static final double DISTANCE_P = 0.02;
		
		/**
		 * I value for the loop used while driving forwards.
		 */
		public static final double DISTANCE_I = 0.0001;
		
		/**
		 * D value for the loop used while driving forwards.
		 */
		public static final double DISTANCE_D = 0.06;
	}

	/**
	 * Contains scaling factors for drive methods.
	 */
	public static final class DriveScalars {
		/**
		 * Joystick multiplier of robot while driving forwards in alignment mode.
		 */
		public static final double ALIGNMENT_FORWARDSPEED = 0.5;
		
		/**
		 * Joystick multiplier of robot while turning in alignment mode.
		 */
		public static final double ALIGNMENT_TURNSPEED = 0.5;
		
		/**
		 * Percentage difference between joysticks required before lockmode disables.
		 */
		public static final double LOCKMODE_TOLERANCE = 0.2;
	}
}