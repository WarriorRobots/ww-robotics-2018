/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2478.robot;

import frc.team2478.robot.commands.CameraAlign;
import frc.team2478.robot.commands.JoystickAlignment;
import frc.team2478.robot.commands.JoystickTurnLock;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Contains logic for Joysticks, the Xbox controller, and methods for interfacing with them.
 */
public final class OI {
	
	// Joystick / Controller USB ID codes
	public static final int LEFT_JOY = 1;
	public static final int RIGHT_JOY = 0;
	public static final int XBOX = 2;
	
	private Joystick m_leftJoy = new Joystick(LEFT_JOY);
	private Joystick m_rightJoy = new Joystick(RIGHT_JOY);
	private XboxController m_xbox = new XboxController(XBOX);

	private Button m_rightTriggerButton = new JoystickButton(m_rightJoy, 1);
	private Button m_rightThumbButton = new JoystickButton(m_rightJoy, 2);
//	private Button m_right4Button = new JoystickButton(m_rightJoy, 4);
	private Button m_leftTriggerButton = new JoystickButton(m_leftJoy, 1);
	
	/**
	 * Instantiates a new OI.java object, and maps Commands to buttons.
	 */
	public OI() {
		m_rightTriggerButton.whileHeld(new JoystickTurnLock());
		m_rightThumbButton.whileHeld(new JoystickAlignment());
		m_leftTriggerButton.whileHeld(new CameraAlign());
	}

	/**
	 * Gets Y-value of left joystick multiplied by scalingFactor.
	 * @param scalingFactor  Decimal value that proportionally alters joystick output.
	 * @return Scaled Y-value of left joystick.
	 */
	public double getLeftY(double scalingFactor) {
		return -m_leftJoy.getY() * scalingFactor;
	}
	
	/**
	 * Gets Y-value of right joystick multiplied by scalingFactor.
	 * @param scalingFactor  Decimal value that proportionally alters joystick output.
	 * @return Scaled Y-value of right joystick.
	 */
	public double getRightY(double scalingFactor) {
		return -m_rightJoy.getY() * scalingFactor;
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
		return m_xbox.getX(Hand.kLeft);
	}

	/**
	 * Gets Y-value of right Xbox joystick multiplied by scalingFactor.
	 * @param scalingFactor  Decimal value that proportionally alters Xbox joystick output.
	 * @return Scaled Y-value of right Xbox joystick.
	 */	
	public double getXboxRightY(double scalingFactor) {
		return m_xbox.getX(Hand.kRight);
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
		return m_leftJoy.getX() * scalingFactor;
	}

	/**
	 * Gets X-value of right joystick multiplied by scalingFactor.
	 * @param scalingFactor  Decimal value that proportionally alters joystick output.
	 * @return Scaled X-value of right joystick.
	 */
	public double getRightX(double scalingFactor) {
		return m_rightJoy.getX() * scalingFactor;
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
	
	/**
	* Gets state of right trigger button.
	* @return true if pressed, false otherwise
	*/
	public boolean getRightTriggerButton() {
		return m_rightTriggerButton.get();
	}
	
	/**
	* Gets state of right thumb button.
	* @return true if pressed, false otherwise
	*/
	public boolean getRightThumbButton() {
		return m_rightThumbButton.get();
	}
}
