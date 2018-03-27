package frc.team2478.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;

public class CheckCubeDistance extends Command {

	private double size;
	
	public CheckCubeDistance(double size) {
		this.size = size;
	}
	
	
	@Override
	protected boolean isFinished() {
		return Robot.vision.getObjectArea() > size;
	}

}
