package frc.team2478.robot.commands.scoring.hood;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;
import frc.team2478.robot.util.enums.Mode;


public class RaiseHood extends Command {
	
	private int count = 0;
	
	/**
	* Extends pistons to raise hood up.
	* @author Josh
	*/
	public RaiseHood() {
		requires(Robot.pneumatics);
	}
	
	@Override
	protected void execute() {
		System.out.println("raise");
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
	
	@Override
	protected void interrupted() {
		this.end();
	}
	
}
