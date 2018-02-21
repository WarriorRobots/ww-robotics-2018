package frc.team2478.robot.commands.scoring;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2478.robot.Robot;

public class StopShooter extends InstantCommand {

	public StopShooter() {
		requires(Robot.shooter);
	}
	
	@Override
	protected void execute() {
		Robot.shooter.stop();
	}

}