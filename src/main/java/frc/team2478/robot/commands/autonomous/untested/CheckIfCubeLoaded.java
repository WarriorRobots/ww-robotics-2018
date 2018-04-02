package frc.team2478.robot.commands.autonomous.untested;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;

public class CheckIfCubeLoaded extends Command {

	@Override
	protected boolean isFinished() {
		return Robot.feed.isCubeLoaded();
	}

}
