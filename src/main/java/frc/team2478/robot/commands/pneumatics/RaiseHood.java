package frc.team2478.robot.commands.pneumatics;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;
import frc.team2478.robot.util.enums.Mode;


public class RaiseHood extends Command {
	
	private int count;
	
	public RaiseHood() {
		requires(Robot.pneumatics);
		count = 0;
	}
	
	@Override
	protected void initialize() {
		this.count=0;
	}
	
	@Override
	protected void execute() {
//		System.out.println("raise");
		Robot.pneumatics.setHoodPiston(Mode.FORWARD);
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
	
}
