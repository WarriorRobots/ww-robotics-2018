package frc.team2478.robot.commands.scoring.feed;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;

public class RunFeedWithJoystick extends Command {

	public RunFeedWithJoystick() {
		requires(Robot.feed);
	}

	@Override
	protected void execute() {
		Robot.feed.runAtPercentage(Robot.oi.getXboxLeftY(0.35));
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		Robot.pickup.stop();
	}
}
