package frc.team2478.robot.commands.scoring.shooter;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;

public class RunShooterAtTargetSpeed extends Command {

	public RunShooterAtTargetSpeed() {
		requires(Robot.shooter);
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
