package frc.team2478.robot.util.triggers;

import edu.wpi.first.wpilibj.buttons.Button;
import frc.team2478.robot.Robot;

public class RightTrigger extends Button {

	@Override
	public boolean get() {
		return (Robot.oi.getXboxRightTrigger() > 0.5) ? true : false;
	}

}
