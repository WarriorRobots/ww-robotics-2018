package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team2478.robot.commands.drive.StopDrive;
import frc.team2478.robot.commands.scoring.StopAllScoringMotors;

public class EmergencyResetAll extends CommandGroup {

	public EmergencyResetAll() {
		addParallel(new StopDrive());
		addParallel(new StopAllScoringMotors());
	}
	
	@Override
	protected void end() {
		Scheduler.getInstance().removeAll();
		System.out.println("=====RESETTING ALL COMMANDS=====");
	}
	
	@Override
	protected void interrupted() {
		this.end();
	}
	
}
