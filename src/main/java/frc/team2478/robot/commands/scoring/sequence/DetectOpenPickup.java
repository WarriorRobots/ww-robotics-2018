package frc.team2478.robot.commands.scoring.sequence;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.team2478.robot.Robot;

public class DetectOpenPickup extends ConditionalCommand {

	public DetectOpenPickup(Command onTrue) {
		super(onTrue);
	}

	@Override
	protected boolean condition() {
		return !Robot.pneumatics.isPickupOut();
	}	
	
}
