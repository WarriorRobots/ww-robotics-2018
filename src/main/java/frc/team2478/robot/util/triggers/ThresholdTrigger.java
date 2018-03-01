package frc.team2478.robot.util.triggers;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.buttons.Button;

public class ThresholdTrigger extends Button {

	private DoubleSupplier input;
	private double threshold;
	
	public ThresholdTrigger(DoubleSupplier input, double threshold) {
		this.input = input;
		this.threshold = threshold;
	}
	
	@Override
	public boolean get() {
		return (Math.abs(input.getAsDouble()) > threshold) ? true : false;
	}

}
