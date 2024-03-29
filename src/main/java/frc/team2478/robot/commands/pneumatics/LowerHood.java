package frc.team2478.robot.commands.pneumatics;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;
import frc.team2478.robot.util.enums.SolenoidMode;

public class LowerHood extends Command {
	
	private int count;

	public LowerHood() {
//		requires(Robot.pneumatics);
	}
	
	@Override
	protected void initialize() {
		count = 0;
	}
	
	@Override
	protected void execute() {
		Robot.pneumatics.setHoodPiston(SolenoidMode.REVERSE);
		count++;
	}
	
	@Override
	protected void end() {
		Robot.pneumatics.setHoodPiston(SolenoidMode.OFF);
	}

	@Override
	protected boolean isFinished() {
		return count > 5;
	}
	
	@Override
	protected void interrupted() {
		this.end();
	}

}
