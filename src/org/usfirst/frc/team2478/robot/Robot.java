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
import org.usfirst.frc.team2478.robot.commands.LockMode;
import org.usfirst.frc.team2478.robot.commands.NormalDrive;
import org.usfirst.frc.team2478.robot.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team2478.robot.subsystems.MotionSensorsSubsystem;

public class Robot extends TimedRobot {
	public static final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
	public static final MotionSensorsSubsystem motionSensors = new MotionSensorsSubsystem();
	public static OI oi;

	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	@Override
	public void robotInit() {
		oi = new OI();
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
		//m_autonomousCommand = m_chooser.getSelected();
		autonomous = new Autonomo();

		// schedule the autonomous command (example)
		if (autonomous != null) {
			autonomous.autoLine(100);
		}
	}

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
		Command lockMode = new LockMode();
		
		double angle = motionSensors.navx.getAngle();
		System.out.println(angle);
		
		if (oi.thumbButton.get()) {
			alignmentMode.start();
		}
		else if (oi.triggerButton.get()) {
			lockMode.start();
		}
		else {
			teleopDrive.start();
		}
		
	}

	@Override
	public void testPeriodic() {
	}
}
