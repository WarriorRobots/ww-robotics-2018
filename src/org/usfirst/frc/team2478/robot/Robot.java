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
//import org.usfirst.frc.team2478.robot.commands.NormalDrive;
import org.usfirst.frc.team2478.robot.control.SynchronousPIDF;
import org.usfirst.frc.team2478.robot.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team2478.robot.subsystems.MotionSensorsSubsystem;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends TimedRobot {
	public static DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
	public static MotionSensorsSubsystem motionSensors = new MotionSensorsSubsystem();
	public static OI oi;
	
//	public SynchronousPIDF pidLoop;
//	public Autonomo autonomo = new Autonomo();
//	public Timer timer = new Timer();

	@Override
	public void robotInit() {
		oi = new OI();
//		pidLoop = new SynchronousPIDF(RobotMap.ANGULAR_P,
//									  RobotMap.COURSECORRECTION_I,
//									  RobotMap.ANGULAR_D);
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
//		timer.reset();
//		timer.start();
//		motionSensors.navx.zeroYaw();
//		pidLoop.reset();
//		if (autonomo != null) { // stops autonomous automatically
//			autonomo.start();
//		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
//		autonomo.autoLine(100);
	}

	@Override
	public void teleopInit() {
//		if (autonomo != null) { // stops autonomous automatically
//			autonomo.cancel();
//		}
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
// //		Command teleopDrive = new NormalDrive();
// 		Command alignmentMode = new AlignmentMode();
// 		Command lockMode = new LockMode();

// 		double angle = motionSensors.navx.getAngle();
// 		System.out.println(angle);
		
// 		if (oi.thumbButton.get()) {
// 			alignmentMode.start();
// 		} else if (oi.triggerButton.get()) {
// 			lockMode.start();
// 		} else {
// 			alignmentMode.cancel();
// 			lockMode.cancel();
// 			System.out.println("Teleop Drive is commented out");
// 		}
	}

	@Override
	public void testInit() {
//		pidLoop.reset();
//		pidLoop.setSetpoint(RobotMap.ANGULAR_SETPOINT);
//		motionSensors.navx.zeroYaw();
//		timer.reset();
//		timer.start();
	}
	
	
	@Override
	public void testPeriodic() {
//		double angle = motionSensors.navx.getAngle();
//		double output = pidLoop.calculate(angle, timer.get());
//		
//		System.out.println("Time: " + Double.toString(timer.get()));
//		System.out.println("Angle: " + Double.toString(angle));
//		System.out.println("PID output: " + Double.toString(output));
//		
//		drivetrain.differentialDrive.tankDrive(RobotMap.TEST_PID_COURSECORRECTION + output,
//											   RobotMap.TEST_PID_COURSECORRECTION - output);
	}
}
