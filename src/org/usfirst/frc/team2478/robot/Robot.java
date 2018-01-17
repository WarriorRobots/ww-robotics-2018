/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2478.robot;

import edu.wpi.first.wpilibj.TimedRobot;
//import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends TimedRobot {

	@Override
	public void robotInit() {
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
	}

	@Override
	public void autonomousInit() {
//		if (autonomo != null) { // stops autonomous automatically
//			autonomo.start();
//		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {}
//		if (autonomo != null) {
//			autonomo.cancel();
//		}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testInit() {}
	
	
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
