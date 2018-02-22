package frc.team2478.robot.commands.scoring.intake;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2478.robot.Robot;
import frc.team2478.robot.util.enums.Mode;


public class RetractIntake extends InstantCommand {
		
		/**
		* Retracts pistons to pull in the intake.
		* @author Josh
		*/
		public RetractIntake() {
			requires(Robot.pneumatics);
		}

		@Override
		protected void execute() {
			Robot.pneumatics.setHoodPiston(Mode.REVERSE);
		}
		
}
