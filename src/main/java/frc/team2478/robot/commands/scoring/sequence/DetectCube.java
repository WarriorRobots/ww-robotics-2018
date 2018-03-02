package frc.team2478.robot.commands.scoring.sequence;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.team2478.robot.Robot;

public class DetectCube extends ConditionalCommand {

	public DetectCube(Command onTrue, Command onFalse) {
		super(onTrue, onFalse);
	}

	@Override
	protected boolean condition() {
		return Robot.feed.isCubeLoaded();
	}
	
}
