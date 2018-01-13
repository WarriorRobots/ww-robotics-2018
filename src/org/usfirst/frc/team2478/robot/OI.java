/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2478.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	// should all this be static??
	public static Joystick leftJoy = new Joystick(RobotMap.LEFT_JOY);
	public static Joystick rightJoy = new Joystick(RobotMap.RIGHT_JOY);

	public static Button triggerButton = new JoystickButton(rightJoy, 1);
	public static Button thumbButton = new JoystickButton(rightJoy, 2);

	public OI() {
		// what goes in here??
	}

	public static double getLeftSpeed() {
		return leftJoy.getY() * -1;
	}

	public static double getRightSpeed() {
		return rightJoy.getY() * -1;
	}
	
	public static double getForwardSpeed() {
		return rightJoy.getY() * -0.5;
	}
	
	public static double getTurnSpeed() {
		return rightJoy.getX() * 0.5;
	}
}
