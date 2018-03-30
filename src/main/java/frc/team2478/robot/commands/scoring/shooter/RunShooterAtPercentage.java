package frc.team2478.robot.commands.scoring.shooter;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;

public class RunShooterAtPercentage extends Command {

	private double percentage;
	
	public RunShooterAtPercentage(double percentage) {
		requires(Robot.shooter);
		this.percentage = percentage;
	}

	@Override
	protected void execute() {
		Robot.shooter.runAtPercentage(percentage);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		Robot.shooter.stop();
	}
}
