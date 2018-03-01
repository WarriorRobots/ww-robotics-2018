package frc.team2478.robot.commands.scoring.intake;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2478.robot.Robot;

public class StopIntake extends InstantCommand {

	public StopIntake() {
		requires(Robot.intake);
	}
	
	@Override
	protected void execute() {
		Robot.intake.stop();
	}

}