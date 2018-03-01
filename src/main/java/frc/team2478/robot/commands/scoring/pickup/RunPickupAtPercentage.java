package frc.team2478.robot.commands.scoring.pickup;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;

public class RunPickupAtPercentage extends Command {
	
	private double percentage;
	
	public RunPickupAtPercentage(double percentage) {
		requires(Robot.pickup);
		this.percentage = percentage;
	}
	
	@Override
	protected void execute() {
		Robot.pickup.runAtPercentage(percentage);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		Robot.pickup.stop();
	}
	
	@Override
	protected void interrupted() {
		this.end();
	}
}
