package frc.team2478.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Constants;
import frc.team2478.robot.Robot;

public class HookForwards extends Command {

	public HookForwards() {
		requires(Robot.climb);
	}
	
	@Override
	public void execute() {
		Robot.climb.runHookAtPercentage(Constants.ShooterRig.HOOK_PERCENT_SPEED_FORWARD);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	public void end() {
		Robot.climb.stopHook();
	}
	
	@Override
	public void interrupted() {
		this.end();
	}
}
