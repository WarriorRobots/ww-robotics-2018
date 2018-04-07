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
	 * Set to true to stop the robot from moving its wheels.
	 * This is for testing only; ALWAYS SET TO FALSE DURING A COMPETITION.
	 */
	public static final boolean DISABLED_DRIVE = false;
	
	/**
	 * Contains PID constants used for autonomous closed-loop control.
	 */
	public static final class AutonomoDrive {
		/*
		 * The only 2 changes made were the 2 d values:
		 * distance: 0 -> 0.02
		 * turning : 0 -> 0.015
		 */
		
		// autonomous driving forwards		
		public static final double DISTANCE_P = 0.0075;
		public static final double DISTANCE_I = 0.0001;
		public static final double DISTANCE_D = 0.02; // 0.05
		public static final double DISTANCE_TOLERANCE = 5;
		// autonomous turning in place
		public static final double TURNING_P = 0.019;
		public static final double TURNING_I = 0.0015;
		public static final double TURNING_D = 0.023;
		public static final double TURNING_TOLERANCE = .25;
		// autonomous preventing drift during driving
		public static final double COURSECORRECTION_P = 0.017;
		public static final double COURSECORRECTION_I = 0.0001;
		public static final double COURSECORRECTION_D = .1;
		
		/**
		 * {@value}
		 */
		public static final double INCHES_PER_CLICK = (5.0 * Math.PI) / 128.0;
		
		public static double ClicksToInches(int clicks) {
			return ((double) clicks) * INCHES_PER_CLICK;
		}
		
		public static int InchesToClicks(double inches) {
			return (int) (inches / INCHES_PER_CLICK);
		}
	}

	/**
	 * Contains values that are multiplied by joystick input to slow the robot.
	 */
	public static final class DriveScalars {
		// see ArcadeDriveAlignment
		public static final double ALIGNMENT_FORWARDSPEED = 0.5;
		public static final double ALIGNMENT_TURNSPEED = 0.6;
		// see TankDriveTurnLock
		public static final double LOCKMODE_TOLERANCE = 0.2;

	}
	
	/**
	 * Contains values and methods related to the shooter mechanism.
	 */
	public static final class ShooterRig {
		/**
		 * Conversion factor equal to 600*100ms / 4096clicks.
		 * Used for converting encoder clicks to revolutions per minute.
		 * <p> Equivalent to {@value}.
		 */
		public static final double MILS_PER_CLICK_RATIO = 600.0 / 4096.0; //0.1465
		
		/**
		 * Defines the gear box ratio (outer rotations per inner rotations).
		 * <p> Equivalent to {@value}.
		 */
		public static final double OUT_PER_IN_RATIO = 1.0 / 5.0;
		
		/**
		 * Convert raw encoder measurements to RPM.
		 * @param clicksPer100ms  velocity in clicks per 100ms
		 * @return velocity in revolutions per minute
		 */
	    public static double encoderClicksToRpm(double clicksPer100ms) {
			return clicksPer100ms * MILS_PER_CLICK_RATIO;
		}
	    
	    /**
		 * Convert RPM back to raw encoder measurements.
		 * @param revolutionsPerMinute  revolutions per minute
		 * @return velocity in encoder clicks per 100ms
		 */
	    public static double rpmToEncoderClicks(double revolutionsPerMinute) {
			return revolutionsPerMinute / MILS_PER_CLICK_RATIO;
		}
	    
	    /**
	     * Convert the rotation of the shooter into the rotations of the motor
	     * @param revolutions The outward shooter speed.
	     * @return The inward motor speed.
	     */
	    public static double gearboxShooterToMotor(double revolutions) {
	    	return revolutions / OUT_PER_IN_RATIO;
	    }
	    
	    /**
	     * Convert the rotation of the motor into the rotations of the shooter
	     * @param revolutions The inward motor speed.
	     * @return The outward shooter speed.
	     */
	    public static double gearboxMotorToShooter(double revolutions) {
	    	return revolutions * OUT_PER_IN_RATIO;
	    }
	    
	    public static final double PICKUP_PERCENT_SPEED = 0.9;
	    public static final double FEED_PERCENT_SPEED = 0.45;
	    public static final double SHOOTER_PERCENT_SPEED = 0.35;
	    public static final double HOOK_PERCENT_SPEED_FORWARD = 1.0;
	    public static final double HOOK_PERCENT_SPEED_BACKWARD = -0.3;
	    public static final double WINCH_PERCENT_SPEED = 1.0;
	    public static final double SWITCH_SPEED = rpmToEncoderClicks(660);
		public static final double LOW_SPEED = rpmToEncoderClicks(1870);
		public static final double MID_SPEED = rpmToEncoderClicks(2130);
		public static final double HIGH_SPEED = rpmToEncoderClicks(2275);
		public static final double AUTO_SPEED = rpmToEncoderClicks(2130); // +50
	}
	
	/**
	 * Contains booleans that define whether certain motor or encoder polarities are reversed.
	 */
	public static final class Inversions {
		public static final boolean LEFT_ENCODER_REVERSED = true;
		public static final boolean RIGHT_ENCODER_REVERSED = false;
		public static final boolean HOOK_MOTOR_REVERSED = true;
		public static final boolean WINCH_MOTOR_REVERSED = true;
		public static final boolean FEED_LEFT_REVERSED = false;
		public static final boolean FEED_RIGHT_REVERSED = true;
		public static final boolean PICKUP_MASTER_REVERSED = false;
		public static final boolean PICKUP_SLAVE_REVERSED = true;
		public static final boolean SHOOTER_MASTER_REVERSED = true;
		public static final boolean SHOOTER_SLAVE_REVERSED = true;
		public static final boolean SHOOTER_ENCODER_REVERSED = false;
	}
}
