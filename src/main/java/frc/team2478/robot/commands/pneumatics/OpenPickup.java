package frc.team2478.robot.commands.pneumatics;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2478.robot.Robot;
import frc.team2478.robot.util.enums.Mode;


public class OpenPickup extends InstantCommand {
	
	private int count;

	public OpenPickup() {
		requires(Robot.pneumatics);
		count = 0;
	}
	
	@Override
	protected void execute() {
		Robot.pneumatics.setIntakePiston(Mode.REVERSE); // when solenoid retracts, pistons extend
		count++;
	}
	
	@Override
	protected void end() {
		Robot.pneumatics.setHoodPiston(Mode.OFF);
	}

	@Override
	protected boolean isFinished() {
		return count > 5;
	}
	
	@Override
	protected void interrupted() {
		DriverStation.reportWarning("OpenIntake interrupted", false);
		this.end();
	}
}