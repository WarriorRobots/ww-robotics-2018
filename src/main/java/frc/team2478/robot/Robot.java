/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2478.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team2478.robot.commands.AutonomoDriveStraight;
import frc.team2478.robot.commands.CommandBase;
import frc.team2478.robot.util.DashboardHandler;

public class Robot extends TimedRobot {
	
	AutonomoDriveStraight autonomoCommand = new AutonomoDriveStraight(2000);
	
	@Override
	public void robotInit() {
		CommandBase.init();
	}
	
	@Override
	public void robotPeriodic() {
		if (DashboardHandler.getResetButton()) {
			DashboardHandler.putAutonomoWidgets();
			DashboardHandler.putResetButton();
		}
	}

	@Override
	public void disabledInit() {
		DashboardHandler.putResetButton();
		Scheduler.getInstance().removeAll();
	}

	@Override
	public void disabledPeriodic() {}

	@Override
	public void autonomousInit() {
//		new AutonomoGroupTest(500, -400, 90, -90).start();
		autonomoCommand.start();
		autonomoCommand.setDistancePid(0.01, 0, 0);
		autonomoCommand.willStopAtSetpoint(false);
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testInit() {}
	
	@Override
	public void testPeriodic() {}
}