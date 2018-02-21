package frc.team2478.robot.commands.scoring;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2478.robot.Robot;
import frc.team2478.robot.interfaces.PneumaticInterface.Mode;


public class RetractIntake extends InstantCommand {
		
		/**
		* Retracts pistons to pull in the intake.
		* @author Josh
		*/
		public RetractIntake() {
			requires(Robot.intake);
		}

		/**
		 * Retracts pistons to pull in the intake.
		 */
		@Override
		protected void execute() {
			Robot.hood.setPistonState(Mode.REVERSE);
		}
}
