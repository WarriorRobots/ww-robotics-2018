package frc.team2478.robot.commands.scoring.shooter;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2478.robot.Robot;

public class IncrementShooterTarget extends InstantCommand {

	@Override
	protected void execute() {
		Robot.shooter.incrementTarget();
	}
}