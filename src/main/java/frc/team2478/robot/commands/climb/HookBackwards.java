package frc.team2478.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Constants;
import frc.team2478.robot.Robot;

/**
 * Lowers hook back down towards the robot using the window motor.
 * Speed will be slower and more gentle than HookForwards.
 */
public class HookBackwards extends Command {

	@Override
	public void execute() {
		Robot.climb.runHookAtPercentage(Constants.ShooterRig.HOOK_PERCENT_SPEED_BACKWARD);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		Robot.climb.stopHook();
	}
	
	@Override
	public void interrupted() {
		this.end();
	}
}
