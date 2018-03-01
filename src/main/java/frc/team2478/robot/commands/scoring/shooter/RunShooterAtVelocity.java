package frc.team2478.robot.commands.scoring.shooter;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;

@Deprecated // until encoders are reinstalled and PID is tuned
public class RunShooterAtVelocity extends Command {

	private double rpmTarget;
	
	public RunShooterAtVelocity(double rpmTarget) {
		requires(Robot.shooter);
		this.rpmTarget = rpmTarget;
	}
	
	@Override
	protected void execute() {
		Robot.shooter.runAtNativeUnitVelocity(rpmTarget);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
}
