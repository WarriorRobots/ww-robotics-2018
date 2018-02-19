package frc.team2478.robot.commands.scoring;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;

public class RunShooterWithPercentage extends Command {

private double percentage;
	
	public RunShooterWithPercentage(double percentage) {
		requires(Robot.shooter);
		this.percentage = percentage;
	}

	@Override
	protected void execute() {
		Robot.shooter.setTargetPercentage(percentage);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
}
