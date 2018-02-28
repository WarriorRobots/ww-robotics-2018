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
	 * When true, this disables ALL drivetrain related function.
	 * <p> Usefull for testing the robot without blocks
	 * @author Josh
	 */
	public static final boolean DISABLED_DRIVE = false;
	
	/**
	 * Below this volate the robot triggers.
	 */
	public static final double LOW_VOLTAGE_WARNING = 7;
	
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
		public static final double TURNING_TOLERANCE = .5;
		
		/**
		 * Tolerance of the PID loop used for measuring distance driven.
		 */
		public static final double DISTANCE_TOLERANCE = 5;
		
		/**
		 * P value for the loop used while driving forwards.
		 */
		public static final double DISTANCE_P = 0.005; // 0.02
		
		/**
		 * I value for the loop used while driving forwards.
		 */
		public static final double DISTANCE_I = 0.0; // 0.0001
		
		/**
		 * D value for the loop used while driving forwards.
		 */
		public static final double DISTANCE_D = 0.0; // 0.06
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
		public static final double ALIGNMENT_TURNSPEED = 0.6;
		
		/**
		 * Percentage difference between joysticks required before lockmode disables.
		 */
		public static final double LOCKMODE_TOLERANCE = 0.2;
	}
	
	/**
	 * Variables related to the shooter (excluding the PID).
	 * @author Josh
	 */
	public static final class ShooterRig {
		/**
		 * Conversion factor equal to 600*100ms / 4096clicks.
		 * Used for converting encoder clicks to revolutions per minute.
		 * <p>Equivalent to {@value}.
		 */
		public static final double MILS_PER_CLICK_RATIO = 600.0 / 4096.0; //0.1465
		
		/**
		 * Defines the gearbox ratio (outer rotations per inner rotations).
		 * <p>Equivalent to {@value}.
		 */
		public static final double OUT_PER_IN_RATIO = 1.0 / 5.0;
		
		/**
		 * Convert raw encoder measurements to RPM.
		 * @param vel  velocity in clicks per 100ms
		 * @return velocity in revolutions per minute
		 */
	    public static double encoderClicksToRpm(double vel) {
			return vel * MILS_PER_CLICK_RATIO;
		}
	    
	    /**
		 * Convert RPM back to raw encoder measurements.
		 * @param rpm  revolutions per minute
		 * @return velocity in encoder clicks per 100ms
		 */
	    public static double rpmToEncoderClicks(double rpm) {
			return rpm / MILS_PER_CLICK_RATIO;
		}
	    
	    /**
	     * Convert the rotation of the shooter into the rotations of the motor
	     * @param out The outward shooter speed.
	     * @return The inward motor speed.
	     */
	    public static double gearboxIn(double out) {
	    	return out/OUT_PER_IN_RATIO;
	    }
	    
	    /**
	     * Convert the rotation of the motor into the rotations of the shooter
	     * @param in The inward motor speed.
	     * @return The outward shooter speed.
	     */
	    public static double gearboxOut(double in) {
	    	return in*OUT_PER_IN_RATIO;
	    }
	}
	
	/**
	 * Constants affiliated with the intake.
	 * @author Josh
	 */
	public static final class intake {
		
		/**
		 * Percent motor power assigned to the intake.
		 * @author Josh
		 */
		public static final double intakePercent = 1;
	} 
}
