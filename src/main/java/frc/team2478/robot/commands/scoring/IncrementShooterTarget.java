package frc.team2478.robot.commands.scoring;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2478.robot.Robot;

public class IncrementShooterTarget extends InstantCommand {

	public IncrementShooterTarget() {
		requires(Robot.shooter);
	}

	@Override
	protected void execute() {
		Robot.shooter.incrementTarget();
	}

}