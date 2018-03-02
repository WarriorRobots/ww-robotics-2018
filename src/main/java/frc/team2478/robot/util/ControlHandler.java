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
import frc.team2478.robot.Constants;
import frc.team2478.robot.commands.drive.ArcadeDriveAlignment;
import frc.team2478.robot.commands.drive.ReverseDrive;
import frc.team2478.robot.commands.drive.TankDriveTurnLock;
import frc.team2478.robot.commands.pneumatics.ClosePickup;
import frc.team2478.robot.commands.pneumatics.LowerHood;
import frc.team2478.robot.commands.pneumatics.OpenPickup;
import frc.team2478.robot.commands.pneumatics.RaiseHood;
import frc.team2478.robot.commands.scoring.PickupCubeFromGround;
import frc.team2478.robot.commands.scoring.sequence.EjectCube;
import frc.team2478.robot.commands.scoring.sequence.RackCubeToFire;
import frc.team2478.robot.commands.scoring.sequence.RevAndShootCube;
import frc.team2478.robot.commands.scoring.shooter.DecrementShooterTarget;
import frc.team2478.robot.commands.scoring.shooter.IncrementShooterTarget;
import frc.team2478.robot.util.annotations.Debug;
import frc.team2478.robot.util.enums.Direction;
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
	
	@SuppressWarnings("unused")
	private JoystickButton rightJoyTriggerButton, rightJoyThumbButton, rightJoyButton3, leftJoyTriggerButton, leftJoyButton4, leftJoyButton3;
	
	private ThresholdTrigger leftXboxTrigger, rightXboxTrigger;
	private JoystickButton leftXboxBumper, rightXboxBumper;
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
	
	/**
	 * Initializes buttons and triggers and maps them to commands.
	 * <p> The robot won't function without running this method once.
	 */
	public void init() {
		rightJoyTriggerButton = new JoystickButton(rightJoy, 1);
		leftJoyTriggerButton = new JoystickButton(leftJoy, 1);
		rightJoyThumbButton = new JoystickButton(rightJoy, 2);
		rightJoyButton3 = new JoystickButton(rightJoy, 3);
		leftJoyButton4 = new JoystickButton(leftJoy, 4);
		leftJoyButton3 = new JoystickButton(leftJoy, 3);
		leftXboxTrigger = new ThresholdTrigger( () -> getXboxLeftTrigger(), 0.5);
		rightXboxTrigger = new ThresholdTrigger( () -> getXboxRightTrigger(), 0.5);
		leftXboxBumper = new JoystickButton(xbox, 5);
		rightXboxBumper = new JoystickButton(xbox, 6);
		xboxUp = new DpadTrigger(Direction.UP);
		xboxDown = new DpadTrigger(Direction.DOWN);
		xboxX = new JoystickButton(xbox, 3);
		xboxY = new JoystickButton(xbox, 4);
		xboxA = new JoystickButton(xbox, 1);
		xboxB = new JoystickButton(xbox, 2);
		
		
		rightJoyTriggerButton.whileHeld(new TankDriveTurnLock()); // hold right trigger to reduce amount of turning
		rightJoyThumbButton.whileHeld(new ArcadeDriveAlignment()); // hold thumb button to slow robot & use one joystick
		rightJoyButton3.whenPressed(new ReverseDrive()); // press button 3(R) to reverse front and back of robot		
		leftXboxTrigger.whileHeld(new PickupCubeFromGround()); // hold left xbox trigger to pickup and load cube autonomously
		rightXboxTrigger.whileHeld(new RevAndShootCube()); // hold right xbox trigger to rev shooter and launch cube 1s later
		leftXboxBumper.whileHeld(new EjectCube()); // hold left xbox bumper to spit out cube
		rightXboxBumper.whenPressed(new ClosePickup()); // press right xbox bumper to close pickup, locking in cube
		rightXboxBumper.whileHeld(new RackCubeToFire()); // hold right xbox bumper to back up cube for firing preparation
		xboxUp.whenPressed(new IncrementShooterTarget()); // press up to increase shooter speed to next preset
		xboxDown.whenPressed(new DecrementShooterTarget()); // press down to decrease shooter speed to next preset
		xboxX.whenPressed(new LowerHood()); 	// blue X
		xboxY.whenPressed(new RaiseHood());    // yellow Y
		xboxB.whenPressed(new ClosePickup()); // green A
		leftJoyButton3.whenPressed(new ClosePickup()); // press button 3(L) to close pickup
		leftJoyButton4.whenPressed(new OpenPickup()); // press button 4(L) to open pickup
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
		if (value > Constants.JoystickThresholds.LEFT_XBOX_MIN && value < Constants.JoystickThresholds.LEFT_XBOX_MAX) {
			return 0;
		} else {
			return value * scalingFactor;
		}
	}

	/**
	 * Gets Y-value of right Xbox joystick multiplied by scalingFactor.
	 * @param scalingFactor  Decimal value that proportionally alters Xbox joystick output.
	 */	
	public double getXboxRightY(double scalingFactor) {
		double value = -xbox.getY(Hand.kRight);
		if (value > Constants.JoystickThresholds.RIGHT_XBOX_MIN && value < Constants.JoystickThresholds.RIGHT_XBOX_MAX) {
			return 0;
		} else {
			return value * scalingFactor;
		}
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