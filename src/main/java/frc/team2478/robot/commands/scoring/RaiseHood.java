package frc.team2478.robot.commands.scoring;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;
import frc.team2478.robot.util.enums.Mode;


public class RaiseHood extends Command {
	
	private int count = 0;
	
	/**
	* Extends pistons to put hood up.
	* @author Josh
	*/
	public RaiseHood() {
		requires(Robot.hood);
	}
	
	@Override
	protected void initialize() {
		this.count=0;
	}

	/**
	 * Extends pistons to put hood up.
	 */
	@Override
	protected void execute() {
		Robot.hood.setHoodPiston(Mode.FORWARD);
		count++;
	}
	
	@Override
	protected void end() {
		Robot.hood.setHoodPiston(Mode.OFF);
		this.count=0;
	}

	@Override
	protected boolean isFinished() {
		return (count > 5) ? true : false;
	}
	
	@Override
	protected void interrupted() {
		this.end();
	}
}
