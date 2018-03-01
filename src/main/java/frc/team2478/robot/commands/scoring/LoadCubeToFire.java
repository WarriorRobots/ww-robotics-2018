package frc.team2478.robot.commands.scoring;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;
import frc.team2478.robot.commands.pneumatics.CloseIntake;

public class LoadCubeToFire extends Command {

	private int FIXTHIS;
	
	public LoadCubeToFire() {
		requires(Robot.feed);
		requires(Robot.pickup);
	}
	
	@Override
	protected void execute() {
		Robot.feed.runAtPercentage(0.3);
		Robot.pickup.runAtPercentage(0.3);
	}
	
	@Override
	protected boolean isFinished() {
		return !Robot.feed.isCubeLoaded();
	}

	@Override
	protected void end() {
		Robot.feed.stop();
		Robot.pickup.stop();
		new CloseIntake().start();
	}
	
}
