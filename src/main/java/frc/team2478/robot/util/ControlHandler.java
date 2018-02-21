/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2478.robot.util;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.team2478.robot.commands.drive.InputReverse;
import frc.team2478.robot.commands.drive.JoystickAlignment;
import frc.team2478.robot.commands.drive.JoystickTurnLock;
import frc.team2478.robot.commands.scoring.DecrementShooterTarget;
import frc.team2478.robot.commands.scoring.IncrementShooterTarget;
import frc.team2478.robot.commands.scoring.LowerHood;
import frc.team2478.robot.commands.scoring.RaiseHood;
import frc.team2478.robot.commands.scoring.RetractIntake;
import frc.team2478.robot.commands.scoring.ShooterFeedGroup;
import frc.team2478.robot.util.annotations.Debug;
import frc.team2478.robot.util.triggers.DpadTrigger;
import frc.team2478.robot.util.triggers.DpadTrigger.Direction;
import frc.team2478.robot.util.triggers.RightTrigger;

/**
 * Contains knwldge for Joysticks, the Xbox controller, and methods for interfacing with them.
 * @author Tony (for the comment at least)
 */
public final class ControlHandler {

	public static final int LEFT_JOY = 1;
	public static final int RIGHT_JOY = 0;
	public static final int XBOX = 2;
	
	private Joystick leftJoy, rightJoy;
	private XboxController xbox;
	
	@SuppressWarnings("unused")
	private JoystickButton rightJoyTriggerButton, rightJoyThumbButton, rightJoyButton3, leftTriggerButton;
	public static JoystickButton buttonElevenLeft; // quick fix
	
	private RightTrigger rightXboxTrigger;
	private DpadTrigger xboxUp, xboxDown;
	private JoystickButton xboxX, xboxY, xboxA, xboxB;

	/**
	 * Instantiates a new OI.java object, and maps Commands to buttons.
	 */
	public ControlHandler() {
		leftJoy = new Joystick(LEFT_JOY);
		rightJoy = new Joystick(RIGHT_JOY);
		xbox = new XboxController(XBOX);
	}
	
	public void init() {
		rightJoyTriggerButton = new JoystickButton(rightJoy, 1);
		leftTriggerButton = new JoystickButton(leftJoy, 1);
		rightJoyThumbButton = new JoystickButton(rightJoy, 2);
		rightJoyButton3 = new JoystickButton(rightJoy, 3);
		buttonElevenLeft = new JoystickButton(leftJoy, 11);//temp fix
		
		rightXboxTrigger = new RightTrigger();
		xboxUp = new DpadTrigger(Direction.UP);
		xboxDown = new DpadTrigger(Direction.DOWN);
		xboxX = new JoystickButton(xbox, 3);
		xboxY = new JoystickButton(xbox, 4);
		xboxA = new JoystickButton(xbox, 1);
		xboxB = new JoystickButton(xbox, 2);
		
		
		rightJoyTriggerButton.whileHeld(new JoystickTurnLock());
		rightJoyThumbButton.whileHeld(new JoystickAlignment());
		rightJoyButton3.whenPressed(new InputReverse());
		
		rightXboxTrigger.whileHeld(new ShooterFeedGroup());
		xboxUp.whenPressed(new IncrementShooterTarget());
		xboxDown.whenPressed(new DecrementShooterTarget());
		xboxX.whenPressed(new LowerHood());
		xboxY.whenPressed(new RaiseHood());
//		xboxA.whenPressed(new RetractIntake());
//		xboxB.whenPressed(new ExtendIntake());
	}

	/**
	 * Gets Y-value of left joystick multiplied by scalingFactor.
	 * @param scalingFactor  Decimal value that proportionally alters joystick output.
	 */
	public double getLeftY(double scalingFactor) {
		return leftJoy.getY() * scalingFactor;
	}
	
	/**
	 * Gets Y-value of right joystick multiplied by scalingFactor.
	 * @param scalingFactor  Decimal value that proportionally alters joystick output.
	 */
	public double getRightY(double scalingFactor) {
		return rightJoy.getY() * scalingFactor;
	}

	/**
	 * Gets Y-value of left joystick.
	 */
	public double getLeftY() {
		return this.getLeftY(1);
	}

	/**
	 * Gets Y-value of right joystick.
	 */
	public double getRightY() {
		return this.getRightY(1);
	}
	
	/**
	 * Gets Y-value of left Xbox joystick multiplied by scalingFactor.
	 * @param scalingFactor  Decimal value that proportionally alters Xbox joystick output.
	 */
	public double getXboxLeftY(double scalingFactor) {
		return -xbox.getY(Hand.kLeft) * scalingFactor;
	}

	/**
	 * Gets Y-value of right Xbox joystick multiplied by scalingFactor.
	 * @param scalingFactor  Decimal value that proportionally alters Xbox joystick output.
	 */	
	public double getXboxRightY(double scalingFactor) {
		return -xbox.getY(Hand.kRight) * scalingFactor;
	}

	/**
	 * Gets Y-value of left Xbox joystick.
	 */	
	public double getXboxLeftY() {
		return this.getXboxLeftY(1);
	}

	/**
	 * Gets Y-value of right Xbox joystick.
	 * @return Y-value of right Xbox joystick.
	 */	
	public double getXboxRightY() {
		return this.getXboxRightY(1);
	}
	
	/**
	 * Gets Y-value of left Xbox trigger.
	 * @return Y-value of left Xbox trigger.
	 */
	public double getXboxLeftTrigger() {
		return xbox.getTriggerAxis(Hand.kLeft);
	}
	
	/**
	 * Gets Y-value of right Xbox trigger.
	 * @return Y-value of right Xbox trigger.
	 */
	public double getXboxRightTrigger() {
		return xbox.getTriggerAxis(Hand.kRight);
	}
	
	public double getDpadAngle() {
		return xbox.getPOV();
	}
	
	@Debug
	public void setLeftRumble() {
		xbox.setRumble(RumbleType.kLeftRumble, 0.5);
	}
	
	/**
	 * Gets X-value of left joystick multiplied by scalingFactor.
	 * @param scalingFactor  Decimal value that proportionally alters joystick output.
	 */
	public double getLeftX(double scalingFactor) {
		return leftJoy.getX() * scalingFactor;
	}

	/**
	 * Gets X-value of right joystick multiplied by scalingFactor.
	 * @param scalingFactor  Decimal value that proportionally alters joystick output.
	 */
	public double getRightX(double scalingFactor) {
		return rightJoy.getX() * scalingFactor;
	}

	/**
	 * Gets X-value of left joystick.
	 */
	public double getLeftX() {
		return this.getLeftX(1);
	}

	/**
	 * Gets X-value of right joystick.
	 */
	public double getRightX() {
		return this.getRightX(1);
	}
	
}