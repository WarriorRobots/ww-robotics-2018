package frc.team2478.robot.commands.scoring.intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;

public class RunIntakeAtPercentage extends Command {
	
	private double percentage;
	
	/**
	 * Runs the intake backwards at a constant speed.
	 * @author Josh
	 */
	public RunIntakeAtPercentage(double percentage) {
		requires(Robot.intake);
		this.percentage = percentage;
	}
	
	@Override
	protected void execute() {
		Robot.intake.runAtPercentage(percentage);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		Robot.intake.stop();
	}
	
	@Override
	protected void interrupted() {
		this.end();
	}
}
