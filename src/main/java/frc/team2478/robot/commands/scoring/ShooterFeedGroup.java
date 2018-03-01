package frc.team2478.robot.commands.scoring;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2478.robot.Robot;
import frc.team2478.robot.util.annotations.Debug;

public class ShooterFeedGroup extends CommandGroup {
	
	private int FIXTHIS;
	
	@Debug
	public ShooterFeedGroup() {
//		addParallel(new RunShooterAtTargetSpeed());
//		addSequential(new WaitCommand(0.5));
//		addSequential(new RunFeedWithJoystick());
	}

	@Override
	protected void end() {
		Robot.shooter.stop();
		Robot.feed.stop();
	}
	
	@Override
	protected void interrupted() {
		this.end();
	}
}