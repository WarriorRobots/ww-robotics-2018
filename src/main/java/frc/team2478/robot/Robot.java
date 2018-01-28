/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2478.robot;

import frc.team2478.robot.commands.AutonomoGroupTest;
import frc.team2478.robot.commands.CameraAlign;
import frc.team2478.robot.commands.CommandBase;
import frc.team2478.robot.subsystems.DrivetrainSubsystem;
import frc.team2478.robot.util.DashboardHandler;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends TimedRobot {
	
//	CommandBase autonomoCommand;
//	SendableChooser<CommandBase> chooser = new SendableChooser<>();
	
	private NetworkTableInstance defaulttable;
	private NetworkTable ntable;
	private double target_exist;
	private double targetOffset_horz;
	private double targetOffset_vert;
	private double targetArea;
	private double targetSkew;

	@Override
	public void robotInit() {
		CommandBase.init();
//		chooser.addDefault("DEFAULT", new AutonomoDriveStraight(100));
//		chooser.addObject("TWO FEET", new AutonomoDriveStraight(1151));

		defaulttable = NetworkTableInstance.getDefault();
		ntable = defaulttable.getTable("limelight");
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

//	@Override
//	public void autonomousInit() {
////		autonomoCommand = chooser.getSelected();
//		new AutonomoGroupTest(DashboardHandler.getDist1(),
//							  DashboardHandler.getDist2(),
//							  DashboardHandler.getTurn1(),
//							  DashboardHandler.getTurn2()).start();
//	}

//	@Override
//	public void autonomousPeriodic() {
//		Scheduler.getInstance().run();
//	}

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
	public void testPeriodic() {}
	
	public void autonomousPeriodic() {
		target_exist = ntable.getEntry("tv").getDouble(0);
		targetOffset_horz = ntable.getEntry("tx").getDouble(0);
		targetOffset_vert = ntable.getEntry("ty").getDouble(0);
		targetArea = ntable.getEntry("ta").getDouble(0);
		targetSkew = ntable.getEntry("ts").getDouble(0);
		
//		System.out.format("Targetoffset Horz: %f\nTargetoffset Vert: %f\nTargetarea: %f\nTargetskew %f",
//				targetOffset_horz,targetOffset_vert,targetArea,targetSkew);
		
		
		if (target_exist == 1) {
			CameraAlign.align(targetOffset_horz/20);
			System.out.format("Targetoffset Horz: %f", targetOffset_horz);}
		if (target_exist == 0) {System.out.println("No target");}
		
		
	}
}