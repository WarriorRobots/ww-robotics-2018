package frc.team2478.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.team2478.robot.Robot;
import frc.team2478.robot.commands.pneumatics.LowerHood;
import frc.team2478.robot.commands.pneumatics.RaiseHood;
import frc.team2478.robot.commands.scoring.feed.RunFeedAtPercentage;
import frc.team2478.robot.commands.scoring.feed.StopFeed;
import frc.team2478.robot.commands.scoring.shooter.RunShooterAtPercentage;
import frc.team2478.robot.commands.scoring.shooter.StopShooter;
import frc.team2478.robot.util.enums.Mode;

public class TestShooterRig extends CommandGroup {

	/**
	 * Raise hood, run shooter + feed, and lower hood.
	 * @author Alex
	 */
	public TestShooterRig() {
		addSequential(new RaiseHood());
		addSequential(new WaitCommand(1));
		addParallel(new RunShooterAtPercentage(0.3));
		addSequential(new WaitCommand(2));
		addParallel(new RunFeedAtPercentage(0.5));
		addSequential(new WaitCommand(2));
		addParallel(new LowerHood());
		addParallel(new StopShooter());
		addParallel(new StopFeed());
	}
	
	@Override
	protected void end() {
		Robot.feed.stop();
		Robot.shooter.stop();
		Robot.pneumatics.setHoodPiston(Mode.OFF);
	}

	@Override
	protected void interrupted() {
		this.end();
	}
}
