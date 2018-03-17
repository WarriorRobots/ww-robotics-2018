package frc.team2478.robot.commands.Climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Constants;
import frc.team2478.robot.Robot;

public class WinchInwards extends Command{

	public WinchInwards(){
		requires(Robot.climb);
	}
	public void execute() {
		Robot.climb.runWinchAtPercentage(Constants.ShooterRig.WINCH_PERCENT_SPEED);
	}
	public void end() {
		Robot.climb.winchstop();
	}
	@Override
	protected boolean isFinished() {
		return false;
	}
	public void interrupted() {
		this.end();
	}
}
