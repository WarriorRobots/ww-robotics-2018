package frc.team2478.robot.util.triggers;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.team2478.robot.ControlHandler;
import frc.team2478.robot.util.enums.DpadDirection;

public class DpadTrigger extends Button {

	DpadDirection direction;
	
	public DpadTrigger(DpadDirection direction) {
		this.direction = direction;
	}
	
	@Override
	public boolean get() {
		switch(direction) {
		case UP:
			return (ControlHandler.getInstance().getDpadAngle() == 0) ? true : false;
		case DOWN:
			return (ControlHandler.getInstance().getDpadAngle() == 180) ? true : false;
		case LEFT:
			return (ControlHandler.getInstance().getDpadAngle() == 270) ? true : false;
		case RIGHT:
			return (ControlHandler.getInstance().getDpadAngle() == 90) ? true : false;
		default:
			DriverStation.reportError("Error with d-pad on Xbox Controller", true);
			return false;
		}
	}

}
