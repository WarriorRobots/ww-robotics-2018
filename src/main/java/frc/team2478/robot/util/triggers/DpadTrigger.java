package frc.team2478.robot.util.triggers;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.buttons.Button;

public class DpadTrigger extends Button {

	private Supplier<Integer> input;
	private int angle;
	
	public DpadTrigger(Supplier<Integer> input, int angle) {
		this.angle = angle;
		this.input = input;
	}
	
	@Override
	public boolean get() {
		return (input.get() == angle) ? true : false;
	}

}
