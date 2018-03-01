package frc.team2478.robot.commands.pneumatics;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2478.robot.Robot;
import frc.team2478.robot.util.enums.Mode;


public class ClosePickup extends InstantCommand {
		
	private int count;

	public ClosePickup() {
		requires(Robot.pneumatics);
		count = 0;
	}
	
	@Override
	protected void execute() {
		Robot.pneumatics.setIntakePiston(Mode.FORWARD); // when solenoid extends, pistons retract
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
		DriverStation.reportWarning("CloseIntake interrupted", false);
		this.end();
	}
		
}
