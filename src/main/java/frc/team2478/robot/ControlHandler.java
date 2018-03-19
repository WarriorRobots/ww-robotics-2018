/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2478.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.team2478.robot.commands.EmergencyResetAll;
import frc.team2478.robot.commands.Climb.HookBackwards;
import frc.team2478.robot.commands.Climb.HookForwards;
import frc.team2478.robot.commands.Climb.WinchInwards;
import frc.team2478.robot.commands.Climb.WinchOutwards;
import frc.team2478.robot.commands.drive.ArcadeDriveAlignment;
import frc.team2478.robot.commands.drive.ReverseDrive;
import frc.team2478.robot.commands.drive.TankDriveTurnLock;
import frc.team2478.robot.commands.pneumatics.ClosePickup;
import frc.team2478.robot.commands.pneumatics.LowerHood;
import frc.team2478.robot.commands.pneumatics.OpenPickup;
import frc.team2478.robot.commands.pneumatics.RaiseAndClose;
import frc.team2478.robot.commands.scoring.feed.RunFeedAtDefault;
import frc.team2478.robot.commands.scoring.sequence.EjectCube;
import frc.team2478.robot.commands.scoring.sequence.PullCubeIntoRobot;
import frc.team2478.robot.commands.scoring.sequence.RackCubeToFire;
import frc.team2478.robot.commands.scoring.shooter.ShootHigh;
import frc.team2478.robot.commands.scoring.shooter.ShootLow;
import frc.team2478.robot.commands.scoring.shooter.ShootMid;
import frc.team2478.robot.commands.scoring.shooter.ShootSwitch;
import frc.team2478.robot.util.annotations.Debug;
import frc.team2478.robot.util.enums.DpadDirection;
import frc.team2478.robot.util.triggers.DpadTrigger;
import frc.team2478.robot.util.triggers.ThresholdTrigger;

/**
 * Contains methods for receiving data from Joysticks and the Xbox controller.
 */
public final class ControlHandler {

	private static ControlHandler instance = null;
	
	public static final int LEFT_JOY = 1;
	public static final int RIGHT_JOY = 0;
	public static final int XBOX = 2;
	
	private Joystick leftJoy, rightJoy;
	private XboxController xbox;
	
	private JoystickButton rightJoyTriggerButton, rightJoyThumbButton;
	private JoystickButton rightJoyButton5, leftJoyButton4, rightJoyButton3, rightJoyButton7, leftJoyButton5, rightJoyButton6;
	private ThresholdTrigger leftXboxTrigger, rightXboxTrigger;
	private JoystickButton leftXboxBumper, rightXboxBumper;
	private JoystickButton xboxX, xboxY, xboxB, xboxA, xboxBACK, xboxSTART;
	private DpadTrigger xboxUp, xboxDown, xboxRight;

	public static ControlHandler getInstance() {
		if (instance == null) {
			instance = new ControlHandler();
		} return instance;
	}
	
	private ControlHandler() {
		leftJoy = new Joystick(LEFT_JOY);
		rightJoy = new Joystick(RIGHT_JOY);
		xbox = new XboxController(XBOX);
	}
	
	/**
	 * Initializes buttons and triggers and maps them to commands.
	 * <p> The robot won't function without running this method once.
	 */
	public void initButtons() {
		rightJoyTriggerButton = new JoystickButton(rightJoy, 1);
		rightJoyTriggerButton.whileHeld(new TankDriveTurnLock()); // hold right trigger to reduce amount of turning
		
		rightJoyThumbButton = new JoystickButton(rightJoy, 2);
		rightJoyThumbButton.whileHeld(new ArcadeDriveAlignment()); // hold thumb button to slow robot & use one joystick

		rightJoyButton3 = new JoystickButton(rightJoy, 3);
		rightJoyButton3.whenPressed(new OpenPickup()); // press button 3(L) to close pickup
		
		rightJoyButton5 = new JoystickButton(rightJoy, 5);
		rightJoyButton5.whenPressed(new ReverseDrive()); // press button 5(R) to reverse front and back of robot

		rightJoyButton7 = new JoystickButton(rightJoy, 7);
		rightJoyButton7.whenPressed(new EmergencyResetAll());

		leftJoyButton4 = new JoystickButton(leftJoy, 4);
		leftJoyButton4.whenPressed(new ClosePickup()); // press button 4(L) to open pickup

		leftXboxTrigger = new ThresholdTrigger( () -> getXboxLeftTrigger(), 0.5);
		leftXboxTrigger.whileHeld(new PullCubeIntoRobot()); // hold left xbox trigger to pickup and load cube autonomously
		
		rightXboxTrigger = new ThresholdTrigger( () -> getXboxRightTrigger(), 0.5);
		rightXboxTrigger.whileHeld(new ShootSwitch()); // hold right xbox trigger to rev shooter and launch cube 1s later

		leftXboxBumper = new JoystickButton(xbox, 5);
		leftXboxBumper.whileHeld(new EjectCube()); // hold left xbox bumper to spit out cube

		rightXboxBumper = new JoystickButton(xbox, 6);
		rightXboxBumper.whenPressed(new ClosePickup()); // press right xbox bumper to close pickup, locking in cube
		rightXboxBumper.whileHeld(new RackCubeToFire()); // hold right xbox bumper to back up cube for firing preparation
		
		xboxUp = new DpadTrigger(DpadDirection.UP);
		xboxUp.whileHeld(new ShootHigh());
		
		xboxDown = new DpadTrigger(DpadDirection.DOWN);
		xboxDown.whileHeld(new ShootLow());
		
		xboxRight = new DpadTrigger(DpadDirection.RIGHT);
		xboxRight.whileHeld(new ShootMid());
		
		xboxA = new JoystickButton(xbox, 1);
		xboxA.whileHeld(new RunFeedAtDefault());

		xboxB = new JoystickButton(xbox, 2);
		xboxB.whenPressed(new ClosePickup());
		
		xboxX = new JoystickButton(xbox, 3);
		xboxX.whenPressed(new LowerHood());
		
		xboxY = new JoystickButton(xbox, 4);
		xboxY.whenPressed(new RaiseAndClose());
		
		xboxBACK = new JoystickButton(xbox, 7);
		xboxBACK.whileHeld(new HookBackwards());
		
		xboxSTART = new JoystickButton(xbox, 8);
		xboxSTART.whileHeld(new HookForwards());
		
		leftJoyButton5 = new JoystickButton(leftJoy, 5);
		leftJoyButton5.whileHeld(new WinchOutwards());
		rightJoyButton6 = new JoystickButton(rightJoy, 6);
		rightJoyButton6.whileHeld(new WinchInwards());
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