package frc.team2478.robot.commands.drive;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2478.robot.Robot;

public class StopDrive extends InstantCommand {

	public StopDrive() {
		requires(Robot.drivetrain);
	}
	
	@Override
	protected void execute() {
		Robot.drivetrain.stopDrive();
	}
	
}
