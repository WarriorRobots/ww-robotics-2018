/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2478.robot.util;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Contains logic for Joysticks, the Xbox controller, and methods for interfacing with them.
 */
public final class ControlHandler {

	private Joystick leftJoy, rightJoy;
	private XboxController xbox;
	
	private Button rightTriggerButton = new JoystickButton(rightJoy, 1);
	private Button rightThumbButton = new JoystickButton(rightJoy, 2);
//	private Button right4Button = new JoystickButton(rightJoy, 4);
	private Button leftTriggerButton = new JoystickButton(leftJoy, 1);
	
	public enum ButtonName {
		RIGHT_TRIGGER, RIGHT_THUMB, LEFT_TRIGGER
	}
	
	/**
	 * Instantiates a new OI.java object, and maps Commands to buttons.
	 */
	public ControlHandler(Joystick leftJoy, Joystick rightJoy, XboxController xbox) {
//		rightTriggerButton.whileHeld(new JoystickTurnLock());
//		rightThumbButton.whileHeld(new JoystickAlignment());
//		leftTriggerButton.whileHeld(new CameraAlign());
		this.leftJoy = leftJoy;
		this.rightJoy = rightJoy;
		this.xbox = xbox;
	}

	/**
	 * Gets Y-value of left joystick multiplied by scalingFactor.
	 * @param scalingFactor  Decimal value that proportionally alters joystick output.
	 * @return Scaled Y-value of left joystick.
	 */
	public double getLeftY(double scalingFactor) {
		return -leftJoy.getY() * scalingFactor;
	}
	
	/**
	 * Gets Y-value of right joystick multiplied by scalingFactor.
	 * @param scalingFactor  Decimal value that proportionally alters joystick output.
	 * @return Scaled Y-value of right joystick.
	 */
	public double getRightY(double scalingFactor) {
		return -rightJoy.getY() * scalingFactor;
	}

	/**
	 * Gets Y-value of left joystick, with scaling factor of 1.
	 * @return Y-value of left joystick.
	 */
	public double getLeftY() {
		return this.getLeftY(1);
	}

	/**
	 * Gets Y-value of right joystick, with scaling factor of 1.
	 * @return Y-value of right joystick.
	 */
	public double getRightY() {
		return this.getRightY(1);
	}
	
	/**
	 * Gets Y-value of left Xbox joystick multiplied by scalingFactor.
	 * @param scalingFactor  Decimal value that proportionally alters Xbox joystick output.
	 * @return Scaled Y-value of right Xbox joystick.
	 */
	public double getXboxLeftY(double scalingFactor) {
		return xbox.getX(Hand.kLeft);
	}

	/**
	 * Gets Y-value of right Xbox joystick multiplied by scalingFactor.
	 * @param scalingFactor  Decimal value that proportionally alters Xbox joystick output.
	 * @return Scaled Y-value of right Xbox joystick.
	 */	
	public double getXboxRightY(double scalingFactor) {
		return xbox.getX(Hand.kRight);
	}

	/**
	 * Gets Y-value of left Xbox joystick, with scaling factor of 1.
	 * @return Y-value of left Xbox joystick.
	 */	
	public double getXboxLeftY() {
		return this.getXboxLeftY(1);
	}

	/**
	 * Gets Y-value of right Xbox joystick, with scaling factor of 1.
	 * @return Y-value of right Xbox joystick.
	 */	
	public double getXboxRightY() {
		return this.getXboxRightY(1);
	}
	
	/**
	 * Gets X-value of left joystick multiplied by scalingFactor.
	 * @param scalingFactor  Decimal value that proportionally alters joystick output.
	 * @return Scaled X-value of left joystick.
	 */
	public double getLeftX(double scalingFactor) {
		return leftJoy.getX() * scalingFactor;
	}

	/**
	 * Gets X-value of right joystick multiplied by scalingFactor.
	 * @param scalingFactor  Decimal value that proportionally alters joystick output.
	 * @return Scaled X-value of right joystick.
	 */
	public double getRightX(double scalingFactor) {
		return rightJoy.getX() * scalingFactor;
	}

	/**
	 * Gets X-value of left joystick, with scaling factor of 1.
	 * @return X-value of left joystick.
	 */
	public double getLeftX() {
		return this.getLeftX(1);
	}

	/**
	 * Gets X-value of right joystick, with scaling factor of 1.
	 * @return X-value of right joystick.
	 */
	public double getRightX() {
		return this.getRightX(1);
	}
	
	public void whileHeld(ButtonName button, Command command) {
		switch (button) {
		case LEFT_TRIGGER: leftTriggerButton.whileHeld(command);
		case RIGHT_THUMB: rightThumbButton.whileHeld(command);
		case RIGHT_TRIGGER: rightTriggerButton.whileHeld(command);
		}
	}
	
//	/**
//	* Gets state of right trigger button.
//	* @return true if pressed, false otherwise
//	*/
//	public boolean getRightTriggerButton() {
//		return rightTriggerButton.get();
//	}
//	
//	/**
//	* Gets state of right thumb button.
//	* @return true if pressed, false otherwise
//	*/
//	public boolean getRightThumbButton() {
//		return rightThumbButton.get();
//	}
}
