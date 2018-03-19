package frc.team2478.robot.commands.Climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Constants;
import frc.team2478.robot.Robot;

public class WinchInwards extends Command{

	public WinchInwards(){
		requires(Robot.climb);
	}
	@Override
	public void execute() {
		Robot.climb.runWinchAtPercentage(Constants.ShooterRig.WINCH_PERCENT_SPEED);
	}
	@Override
	public void end() {
		Robot.climb.winchstop();
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
