/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2478.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	public Joystick leftJoy = new Joystick(RobotMap.LEFT_JOY);
	public Joystick rightJoy = new Joystick(RobotMap.RIGHT_JOY);
	public XboxController xbox = new XboxController(RobotMap.XBOX);

	public Button triggerButton = new JoystickButton(rightJoy, 1);
	public Button thumbButton = new JoystickButton(rightJoy, 2);

	public OI() {
		// what goes in here??
	}

	public double getLeftSpeed() {
		return leftJoy.getY() * RobotMap.INVERT;
	}

	public double getRightSpeed() {
		return rightJoy.getY() * RobotMap.INVERT;
	}
	
	public double getForwardSpeed() {
		return rightJoy.getY() * RobotMap.ARCADE_FORWARDSPEED * RobotMap.INVERT;
	}
	
	public double getTurnSpeed() {
		return rightJoy.getX() * RobotMap.ARCADE_FORWARDSPEED * RobotMap.INVERT;
	}
}
