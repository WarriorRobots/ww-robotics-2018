package frc.team2478.robot.commands.scoring;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;

public class RackCubeToFire extends Command {

	private int FIXTHIS;
	
	public RackCubeToFire() {
		requires(Robot.feed);
		requires(Robot.shooter);
	}
	
	@Deprecated
	@Override
	protected void execute() {
		Robot.shooter.runAtPercentage(-0.3);
		Robot.feed.runAtPercentage(-0.3);
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.feed.isCubeLoaded();
	}
	
	@Override
	protected void end() {
		Robot.shooter.stop();
		Robot.feed.stop();
	}

}
