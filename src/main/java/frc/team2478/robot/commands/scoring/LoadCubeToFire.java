package frc.team2478.robot.commands.scoring;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;
import frc.team2478.robot.commands.pneumatics.CloseIntake;

public class LoadCubeToFire extends Command {

	private int FIXTHIS;
	
	public LoadCubeToFire() {
		requires(Robot.feed);
		requires(Robot.intake);
	}
	
	@Override
	protected void execute() {
		Robot.feed.runMotorAtPercentage(0.3);
		Robot.intake.runMotorAtPercentage(0.3);
	}
	
	@Override
	protected boolean isFinished() {
		return !Robot.intake.isCubeLoaded();
	}

	@Override
	protected void end() {
		Robot.feed.stop();
		Robot.intake.stop();
		new CloseIntake().start();
	}
	
}
