package frc.team2478.robot.commands.scoring;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;

public class RunShooter extends Command {

	public RunShooter() {
		requires(Robot.shooter);
	}
	
	@Override
	protected void execute() {
		Robot.shooter.shootForCurrentTarget();
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
