package frc.team2478.robot.commands.scoring.shooter;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;
import frc.team2478.robot.util.enums.ShotHeight;

/**
 * Set target to SWITCH and rev the shooter.
 */
public class ShootSwitch extends Command {

	public ShootSwitch() {
		requires(Robot.shooter);
	}
	
	@Override
	protected void initialize() {
		Robot.shooter.setCurrentTarget(ShotHeight.SWITCH);
	}
	
	@Override
	protected void execute() {
		Robot.shooter.shootForCurrentTarget();
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.shooter.stop();
	}
	
}
