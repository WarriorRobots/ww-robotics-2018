package frc.team2478.robot.commands.scoring.shooter;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;

public class StopShooterPeriodic extends Command {

	public StopShooterPeriodic() {
		requires(Robot.shooter);
	}
	
	@Override
	protected void execute() {
		Robot.shooter.stop();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}