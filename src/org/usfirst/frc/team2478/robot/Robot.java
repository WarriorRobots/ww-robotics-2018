/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2478.robot;

import org.usfirst.frc.team2478.robot.commands.AlignmentMode;
import org.usfirst.frc.team2478.robot.commands.Autonomo;
import org.usfirst.frc.team2478.robot.commands.LockMode;
import org.usfirst.frc.team2478.robot.commands.NormalDrive;
import org.usfirst.frc.team2478.robot.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team2478.robot.subsystems.MotionSensorsSubsystem;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
	public static final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
	public static final MotionSensorsSubsystem motionSensors = new MotionSensorsSubsystem();
	public static OI oi;
	public static SynchronousPIDF pidLoop;
	
	Autonomo autonomouses = new Autonomo();
	public static Timer timer = new Timer();
	
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	@Override
	public void robotInit() {
		motionSensors.navx.zeroYaw(); // remove later???
		oi = new OI();
		SmartDashboard.putData("Auto mode", m_chooser);
		pidLoop = new SynchronousPIDF(RobotMap.ANGULAR_P, RobotMap.ANGULAR_I, RobotMap.ANGULAR_D);
		motionSensors.navx.zeroYaw();
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
		timer.reset();
		timer.start();
		motionSensors.navx.zeroYaw();
		pidLoop.reset();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		autonomouses.autoLine(100);
//		System.out.println("Auto is running!");
	}

	@Override
	public void teleopInit() {
		if (autonomouses != null) { // stops autonomous automatically
			autonomouses.cancel();
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
	public void testInit() {
	}
	
	
	@Override
	public void testPeriodic() {
		
	}
}
