package frc.team2478.robot.commands.scoring.sequence;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.team2478.robot.Constants;
import frc.team2478.robot.commands.scoring.StopAllMotors;
import frc.team2478.robot.commands.scoring.feed.RunFeedAtPercentage;
import frc.team2478.robot.commands.scoring.shooter.RunShooterAtTargetSpeed;

public class RevAndShootCube extends CommandGroup {

	public RevAndShootCube() {
		addParallel(new RunShooterAtTargetSpeed());
		addSequential(new WaitCommand(0.6)); // needs to be short to preserve points (time-based game)
		addSequential(new RunFeedAtPercentage(Constants.ShooterRig.FEED_PERCENT_SPEED));
	}
	
	@Override
	protected void end() {
		Scheduler.getInstance().add(new StopAllMotors());
	}
	
	@Override
	protected void interrupted() {
		this.end();
	}
	
}
