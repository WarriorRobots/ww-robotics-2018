package frc.team2478.robot.commands.scoring.intake;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2478.robot.Robot;
import frc.team2478.robot.util.enums.Mode;


public class OpenIntake extends InstantCommand {
		
		/**
		* Opens the intake.
		* @author Josh
		*/
		public OpenIntake() {
			requires(Robot.pneumatics);
		}

		@Override
		protected void execute() {
			Robot.pneumatics.setHoodPiston(Mode.REVERSE);
		}
}