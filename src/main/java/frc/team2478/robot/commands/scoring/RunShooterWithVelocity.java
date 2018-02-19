package frc.team2478.robot.commands.scoring;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;

public class RunShooterWithVelocity extends Command {

	private double rpmTarget;
	
	public RunShooterWithVelocity(double rpmTarget) {
		requires(Robot.shooter);
		this.rpmTarget = rpmTarget;
	}
	
	@Override
	protected void execute() {
		Robot.shooter.setTargetVelocity(rpmTarget);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
}
