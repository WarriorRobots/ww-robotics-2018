/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2478.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.team2478.robot.commands.EmergencyResetAll;
import frc.team2478.robot.commands.drive.ArcadeDriveAlignment;
import frc.team2478.robot.commands.drive.CameraAlign;
import frc.team2478.robot.commands.drive.TankDriveTurnLock;
import frc.team2478.robot.commands.pneumatics.ClosePickup;
import frc.team2478.robot.commands.pneumatics.LowerHood;
import frc.team2478.robot.commands.pneumatics.OpenPickup;
import frc.team2478.robot.commands.pneumatics.RaiseAndClose;
import frc.team2478.robot.commands.pneumatics.RaiseHood;
import frc.team2478.robot.commands.scoring.PickupCubeFromPortal;
import frc.team2478.robot.commands.scoring.feed.RunFeedAtDefault;
import frc.team2478.robot.commands.scoring.sequence.EjectCube;
import frc.team2478.robot.commands.scoring.sequence.PickupCube;
import frc.team2478.robot.commands.scoring.sequence.TopLoadCube;
import frc.team2478.robot.commands.scoring.shooter.ShootHigh;
import frc.team2478.robot.commands.scoring.shooter.ShootLow;
import frc.team2478.robot.commands.scoring.shooter.ShootMid;
import frc.team2478.robot.commands.scoring.shooter.ShootSwitch;
import frc.team2478.robot.util.triggers.DpadTrigger;
import frc.team2478.robot.util.triggers.ThresholdTrigger;

/**
 * Contains methods for receiving data from Joysticks and the Xbox controller.
 */
public final class ControlHandler {

	public static final int LEFT_JOY = 1;
	public static final int RIGHT_JOY = 0;
	public static final int XBOX = 2;
	
	private Joystick leftJoy, rightJoy;
	private XboxController xbox;

	private JoystickButton rightJoyTriggerButton, rightJoyThumbButton, leftJoyTriggerButton;
	private JoystickButton leftJoyButton3, leftJoyButton4;
	private JoystickButton rightJoyButton3, rightJoyButton4, rightJoyButton7;
	private ThresholdTrigger leftXboxTrigger, rightXboxTrigger;
	private JoystickButton leftXboxBumper, rightXboxBumper;
	private JoystickButton xboxX, xboxY, xboxB, xboxA, xboxSTART;
	private DpadTrigger xboxUp, xboxDown, xboxRight;
	
	public ControlHandler() {
		leftJoy = new Joystick(LEFT_JOY);
		rightJoy = new Joystick(RIGHT_JOY);
		xbox = new XboxController(XBOX);
		
		leftJoyTriggerButton = new JoystickButton(leftJoy, 1);
		leftJoyButton3 = new JoystickButton(leftJoy, 3);
		leftJoyButton4 = new JoystickButton(leftJoy, 4);
		rightJoyTriggerButton = new JoystickButton(rightJoy, 1);
		rightJoyThumbButton = new JoystickButton(rightJoy, 2);
		rightJoyButton3 = new JoystickButton(rightJoy, 3);
		rightJoyButton4 = new JoystickButton(rightJoy, 4);
		rightJoyButton7 = new JoystickButton(rightJoy, 7);
		
//		leftJoyTriggerButton.whileHeld(new CameraAlign());
		leftJoyButton3.whileHeld(new TopLoadCube());
		leftJoyButton4.whenPressed(new ClosePickup());
		rightJoyTriggerButton.whileHeld(new TankDriveTurnLock());
		rightJoyThumbButton.whileHeld(new ArcadeDriveAlignment());
		rightJoyButton3.whenPressed(new OpenPickup());
		rightJoyButton4.whileHeld(new ShootSwitch());
		rightJoyButton4.whileHeld(new RunFeedAtDefault());
		rightJoyButton7.whenPressed(new EmergencyResetAll());

		leftXboxTrigger = new ThresholdTrigger( () -> xbox.getTriggerAxis(Hand.kLeft), 0.5);
		rightXboxTrigger = new ThresholdTrigger( () -> xbox.getTriggerAxis(Hand.kRight), 0.5);
		leftXboxBumper = new JoystickButton(xbox, 5);
		rightXboxBumper = new JoystickButton(xbox, 6);
		xboxUp = new DpadTrigger( () -> xbox.getPOV(), 0);
		xboxDown = new DpadTrigger( () -> xbox.getPOV(), 180);
		xboxRight = new DpadTrigger( () -> xbox.getPOV(), 90);
		xboxA = new JoystickButton(xbox, 1);
		xboxB = new JoystickButton(xbox, 2);
		xboxX = new JoystickButton(xbox, 3);
		xboxY = new JoystickButton(xbox, 4);
		xboxSTART = new JoystickButton(xbox, 8);

		leftXboxTrigger.whileHeld(new PickupCube());
		rightXboxTrigger.whileHeld(new ShootSwitch());
		leftXboxBumper.whileHeld(new EjectCube());
		rightXboxBumper.whileHeld(new PickupCubeFromPortal());
		xboxUp.whileHeld(new ShootHigh());
		xboxDown.whileHeld(new ShootLow());
		xboxRight.whileHeld(new ShootMid());
		xboxA.whileHeld(new RunFeedAtDefault());
		xboxB.whenPressed(new ClosePickup());
		xboxX.whenPressed(new LowerHood());
		xboxY.whenPressed(new RaiseAndClose());
		xboxSTART.whenPressed(new RaiseHood());
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
		double value = -xbox.getY(Hand.kLeft);
		return value * scalingFactor;
	}

	/**
	 * Gets Y-value of right Xbox joystick multiplied by scalingFactor.
	 * @param scalingFactor  Decimal value that proportionally alters Xbox joystick output.
	 */	
	public double getXboxRightY(double scalingFactor) {
		double value = -xbox.getY(Hand.kRight);
		return value * scalingFactor;
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