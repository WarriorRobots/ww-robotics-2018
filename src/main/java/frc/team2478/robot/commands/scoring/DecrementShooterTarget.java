package frc.team2478.robot.commands.scoring;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2478.robot.Robot;

public class DecrementShooterTarget extends InstantCommand {

	public DecrementShooterTarget() {
	}

	@Override
	protected void execute() {
		Robot.shooter.decrementTarget();
	}
}