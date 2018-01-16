/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2478.robot;

public class RobotMap {
	
	// TalonSRX ID codes
	public static final int LEFT_FRONT = 2;
	public static final int LEFT_BACK = 4;
	public static final int RIGHT_FRONT = 1;
	public static final int RIGHT_BACK = 3;
	
	// Joystick / Controller USB ID codes
	public static final int LEFT_JOY = 1;
	public static final int RIGHT_JOY = 0;
	public static final int XBOX = 2;
	
	// commonly used numbers
	
	// alternate driving mode scalars
	public static final double ARCADE_FORWARDSPEED = 0.5;
	public static final double ARCADE_TURNSPEED = 0.5;
	public static final double LOCKMODE_THRESHOLD = 0.2;
	public static final double TEST_PID_COURSECORRECTION = 0.6;
	
	// PID constants
	public static final double ANGULAR_P = 0.02325;
	public static final double ANGULAR_I = 0.0003; //turning
	public static final double COURSECORRECTION_I = 0.000; //course correction
	public static final double ANGULAR_D = 0.02225; //01725 bck
	public static final double ANGULAR_SETPOINT = 0;
	public static final double ANGULAR_TOLERANCE = 0.05;

}