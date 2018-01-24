/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2478.robot;

import frc.team2478.robot.commands.AutonomoGroupTest;
import frc.team2478.robot.commands.CommandBase;
import frc.team2478.robot.util.DashboardHandler;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
	
//	CommandBase autonomoCommand;
//	SendableChooser<CommandBase> chooser = new SendableChooser<>();

	@Override
	public void robotInit() {
		CommandBase.init();
//		chooser.addDefault("DEFAULT", new AutonomoDriveStraight(100));
//		chooser.addObject("TWO FEET", new AutonomoDriveStraight(1151));
	}
	
	@Override
	public void robotPeriodic() {
		if (DashboardHandler.getResetButton()) {
			DashboardHandler.putWidgets();
			DashboardHandler.putResetButton();
		}
	}

	@Override
	public void disabledInit() {
		DashboardHandler.putResetButton();
		Scheduler.getInstance().removeAll();
	}

	@Override
	public void disabledPeriodic() {
	}

	@Override
	public void autonomousInit() {
//		autonomoCommand = chooser.getSelected();
		new AutonomoGroupTest(SmartDashboard.getNumber(RobotMap.DashboardStrings.AUTO_DIST1, 100),
							  SmartDashboard.getNumber(RobotMap.DashboardStrings.AUTO_DIST2, 100),
							  SmartDashboard.getNumber(RobotMap.DashboardStrings.AUTO_TURN1, 0),
							  SmartDashboard.getNumber(RobotMap.DashboardStrings.AUTO_TURN2, 0)).start();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testInit() {
	}
	
	
	@Override
	public void testPeriodic() {
	}
}