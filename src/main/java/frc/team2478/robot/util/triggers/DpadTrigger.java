package frc.team2478.robot.util.triggers;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.team2478.robot.Robot;
import frc.team2478.robot.util.enums.Direction;

public class DpadTrigger extends Button {

	Direction direction;
	
	public DpadTrigger(Direction direction) {
		this.direction = direction;
	}
	
	@Override
	public boolean get() {
		switch(direction) {
		case UP:
			return (Robot.oi.getDpadAngle() == 0) ? true : false;
		case DOWN:
			return (Robot.oi.getDpadAngle() == 180) ? true : false;
		case LEFT:
			return (Robot.oi.getDpadAngle() == 270) ? true : false;
		case RIGHT:
			return (Robot.oi.getDpadAngle() == 90) ? true : false;
		default:
			DriverStation.reportError("Error with d-pad on Xbox Controller", true);
			return false;
		}
	}

}
