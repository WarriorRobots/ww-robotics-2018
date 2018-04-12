package frc.team2478.robot.commands.pneumatics;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;
import frc.team2478.robot.util.enums.SolenoidMode;


public class RaiseHood extends Command {
	
	private int count;
	
	public RaiseHood() {
//		requires(Robot.pneumatics);
	}
	
	@Override
	protected void initialize() {
		count = 0;
	}
	
	@Override
	protected void execute() {
		Robot.pneumatics.setHoodPiston(SolenoidMode.FORWARD);
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
