package frc.team2478.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Constants;
import frc.team2478.robot.Robot;

/**
 * Releases the rope, lowering the robot to the ground.
 */
public class WinchOutwards extends Command {
	
	@Override
	public void execute() {
		Robot.climb.runWinchAtPercentage(-Constants.ShooterRig.WINCH_PERCENT_SPEED);
	}
	
	@Override
	public void end() {
		Robot.climb.stopWinch();
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	public void interrupted() {
		this.end();
	}
}
