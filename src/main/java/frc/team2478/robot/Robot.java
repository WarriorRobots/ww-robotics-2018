/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2478.robot;

import frc.team2478.robot.commands.AutonomoDriveTurn;
import frc.team2478.robot.commands.CommandBase;
import frc.team2478.robot.util.DashboardHandler;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends TimedRobot {
	
	AutonomoDriveTurn autonomoCommand;
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
	public void disabledPeriodic() {
	}

	@Override
	public void autonomousInit() {
		autonomoCommand = new AutonomoDriveTurn(DashboardHandler.getTurn1());
		autonomoCommand.stopAtSetpoint(false);
		DriverStation.reportWarning("Command will NOT terminate by itself. Caution!", false);
//		autonomoCommand = chooser.getSelected();
//		new AutonomoGroupTest(DashboardHandler.getDist1(),
//							  DashboardHandler.getDist2(),
//							  DashboardHandler.getTurn1(),
//							  DashboardHandler.getTurn2()).start();
		
	}

	@Override
	public void autonomousPeriodic() {
		autonomoCommand.setPID(DashboardHandler.getP(),
							   DashboardHandler.getI(),
							   DashboardHandler.getD());
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