package frc.team2478.robot.commands.pneumatics;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;
import frc.team2478.robot.util.enums.Mode;

public class ClosePickup extends Command {
		
	private int count;

	public ClosePickup() {
		requires(Robot.pneumatics);
	}
	
	@Override
	protected void initialize() {
		count = 0;
	}
	
	@Override
	protected void execute() {
		Robot.pneumatics.setPickupPiston(Mode.REVERSE); // when solenoid extends, pistons retract
		count++;
	}
	
	@Override
	protected void end() {
		Robot.pneumatics.setPickupPiston(Mode.OFF);
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