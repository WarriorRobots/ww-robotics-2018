/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2478.robot;

import org.usfirst.frc.team2478.robot.commands.AutonomoDriveStraight;
import org.usfirst.frc.team2478.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
	
	CommandBase autonomoCommand;
	SendableChooser<CommandBase> chooser = new SendableChooser<>();

	@Override
	public void robotInit() {
		CommandBase.init();
		chooser.addDefault("Default Auto", new AutonomoDriveStraight());
		SmartDashboard.putData("Auto Mode", chooser);
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
	}

	@Override
	public void autonomousInit() {
		autonomoCommand = chooser.getSelected();
		if (autonomoCommand != null) { // stops autonomous automatically
			autonomoCommand.start();
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (autonomoCommand != null) {
			autonomoCommand.cancel();
		}
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		System.out.println(Double.toString(CommandBase.motionSensors.getLeftEncCount()) + " " +
						   Double.toString(CommandBase.motionSensors.getRightEncCount()));
	}

	@Override
	public void testInit() {
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
