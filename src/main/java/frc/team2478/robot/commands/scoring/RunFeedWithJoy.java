package frc.team2478.robot.commands.scoring;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;

public class RunFeedWithJoy extends Command {
		
	private double percentage;
	
	/**
	 * Runs the shooter defaultly and off of the xbox right joystick.
	 */
	public RunFeedWithJoy() {
		requires(Robot.feed);
	}
	
	@Override
	protected void execute() {
		percentage = Robot.oi.getXboxRightY();
		if(Math.abs(percentage) > 0.3) {
			Robot.feed.setTargetPercentage(Robot.oi.getXboxRightY());
		} else {
			Robot.feed.stop();
		}
//		Robot.feed.setTargetPercentage(0.3);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		Robot.feed.stop();
	}
	
	@Override
	protected void interrupted() {
		this.end();
	}
}
