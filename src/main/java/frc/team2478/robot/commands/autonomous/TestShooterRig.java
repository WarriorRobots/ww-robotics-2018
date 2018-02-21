package frc.team2478.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.team2478.robot.Robot;
import frc.team2478.robot.commands.scoring.LowerHood;
import frc.team2478.robot.commands.scoring.RaiseHood;
import frc.team2478.robot.commands.scoring.RunFeed;
import frc.team2478.robot.commands.scoring.RunShooterWithPercentage;
import frc.team2478.robot.commands.scoring.StopFeed;
import frc.team2478.robot.commands.scoring.StopShooter;
import frc.team2478.robot.interfaces.PneumaticInterface.Mode;

public class TestShooterRig extends CommandGroup {

	/**
	 * Raise hood, run shooter + feed, and lower hood.
	 * @author Alex
	 */
	public TestShooterRig() {
		addSequential(new RaiseHood());
		addSequential(new WaitCommand(1));
		addParallel(new RunShooterWithPercentage(0.5));
		addSequential(new WaitCommand(2));
		addParallel(new RunFeed());
		addSequential(new WaitCommand(2));
		addParallel(new LowerHood());
		addParallel(new StopShooter());
		addParallel(new StopFeed());
	}
	
	@Override
	protected void end() {
		Robot.feed.stop();
		Robot.shooter.stop();
		Robot.hood.setPistonState(Mode.OFF);
	}

	@Override
	protected void interrupted() {
		this.end();
	}
}
