package frc.team2478.robot.commands.scoring;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2478.robot.Robot;

public class StopFeed extends InstantCommand {

	public StopFeed() {
		requires(Robot.feed);
	}
	
	@Override
	protected void execute() {
		Robot.feed.stop();
	}

}