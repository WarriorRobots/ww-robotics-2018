package frc.team2478.robot.commands.scoring.intake;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2478.robot.Robot;
import frc.team2478.robot.util.enums.Mode;


public class ExtendIntake extends InstantCommand {
		
		/**
		* Extends pistons to push out the intake.
		* @author Josh
		*/
		public ExtendIntake() {
			requires(Robot.pneumatics);
		}

		@Override
		protected void execute() {
			Robot.pneumatics.setHoodPiston(Mode.FORWARD);
		}
}