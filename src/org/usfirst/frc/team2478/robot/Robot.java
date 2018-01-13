/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2478.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2478.robot.commands.AlignmentMode;
import org.usfirst.frc.team2478.robot.commands.NormalDrive;
import org.usfirst.frc.team2478.robot.control.AngularPID;
import org.usfirst.frc.team2478.robot.subsystems.Drivetrain;
import org.usfirst.frc.team2478.robot.subsystems.Motion;

public class Robot extends TimedRobot {
	public static final Drivetrain drivetrain = new Drivetrain();
	public static final Motion motionSensors = new Motion();
	public static OI oi; // find the purpose of this
	
	public AngularPID turnPID;
	
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	@Override
	public void robotInit() {
		motionSensors.navx.zeroYaw(); // remove later???
		oi = new OI();
		turnPID = new AngularPID(motionSensors.navx, RobotMap.ANGULAR_P,RobotMap.ANGULAR_I,RobotMap.ANGULAR_D, motionSensors.navx.getAngle() + 45);
		//m_chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		m_autonomousCommand = m_chooser.getSelected();

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (m_autonomousCommand != null) { // stops autonomous automatically
			m_autonomousCommand.cancel();
		}
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		Command teleopDrive = new NormalDrive();
		Command alignmentMode = new AlignmentMode();
		
		double angle = motionSensors.navx.getAngle();
		System.out.println(angle);
		
		if (OI.thumbButton.get()) {
			alignmentMode.start();
		}
		else {
			teleopDrive.start();
		}
		
	}

	@Override
	public void testInit() {
//		motionSensors.navx.zeroYaw();
		turnPID.startTimer();
	}
	
	
	@Override
	public void testPeriodic() {
		
		double pidValue = turnPID.getOutput();
		
		turnPID.update();
		System.out.println("Timer:" + Double.toString(turnPID.getTimer()));
		System.out.println("PID: " + Double.toString(pidValue));
		System.out.println("Angle: " + Double.toString(motionSensors.navx.getAngle()));
		
		if(OI.triggerButton.get()) {
			motionSensors.navx.zeroYaw();
			System.out.println("Zeroing!");
			
			turnPID.PIDreset();
			turnPID.startTimer();
		}
		
		drivetrain.differentialDrive.arcadeDrive(0, pidValue);
		
	}
}
