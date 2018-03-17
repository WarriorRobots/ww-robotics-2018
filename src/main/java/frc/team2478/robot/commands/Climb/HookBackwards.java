package frc.team2478.robot.commands.Climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Constants;
import frc.team2478.robot.Robot;

public class HookBackwards extends Command{

	public HookBackwards() {
		requires(Robot.climb);
	}
	@Override
	public void execute() {
		Robot.climb.runHookAtPercentage(Constants.ShooterRig.HOOK_PERCENT_SPEED);
	}
	@Override
	protected boolean isFinished() {
		return false;
	}
	@Override
	protected void end() {
		Robot.climb.hookstop();
	}
	@Override
	public void interrupted() {
		this.end();
	}
}
