package frc.team2478.robot.commands.scoring;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;
import frc.team2478.robot.interfaces.PneumaticInterface.Mode;


public class LowerHood extends Command {
	
	private int count = 0;

	/**
	* Retracts pistons to put hood down.
	* @author Josh
	*/
	public LowerHood() {
		requires(Robot.hood);
	}
	
	@Override
	protected void initialize() {
		this.count=0;
	}
	
	/**
	 * Retracts pistons to put hood down.
	 */
	@Override
	protected void execute() {
		Robot.hood.setPistonState(Mode.REVERSE);
		count++;
	}
	
	@Override
	protected void end() {
		Robot.hood.setPistonState(Mode.OFF);
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
