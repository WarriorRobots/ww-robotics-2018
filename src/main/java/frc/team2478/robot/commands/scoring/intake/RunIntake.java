package frc.team2478.robot.commands.scoring.intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Constants;
import frc.team2478.robot.Robot;

public class RunIntake extends Command {
	
	/**
	 * Runs the intake backwards at a constant speed.
	 * @author Josh
	 */
	public RunIntake() {
		requires(Robot.intake);
	}
	
	@Override
	protected void execute() {
		Robot.intake.setTargetPercentage(-Constants.intake.intakePercent);
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
