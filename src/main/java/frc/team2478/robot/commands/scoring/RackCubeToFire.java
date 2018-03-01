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
		Robot.shooter.setTargetPercentage(-0.3);
		Robot.feed.runMotorAtPercentage(-0.3);
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.intake.isCubeLoaded();
	}
	
	@Override
	protected void end() {
		Robot.shooter.stop();
		Robot.feed.stop();
	}

}
