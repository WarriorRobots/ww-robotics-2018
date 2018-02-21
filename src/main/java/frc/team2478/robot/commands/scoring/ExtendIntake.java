package frc.team2478.robot.commands.scoring;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2478.robot.Robot;
import frc.team2478.robot.util.enums.Mode;


public class ExtendIntake extends InstantCommand {
		
		/**
		* Retracts pistons to push in the intake.
		* @author Josh
		*/
		public ExtendIntake() {
			requires(Robot.intake);
		}

		/**
		 * Retracts pistons to push in the intake.
		 */
		@Override
		protected void execute() {
			Robot.hood.setHoodPiston(Mode.REVERSE);
		}
}
