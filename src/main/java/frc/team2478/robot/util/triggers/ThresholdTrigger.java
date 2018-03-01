package frc.team2478.robot.util.triggers;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.buttons.Button;

public class ThresholdTrigger extends Button {

	private Supplier<Double> input;
	private double threshold;
	
	public ThresholdTrigger(Supplier<Double> input, double threshold) {
		this.input = input;
		this.threshold = threshold;
	}
	
	@Override
	public boolean get() {
		return (Math.abs(input.get()) > threshold) ? true : false;
	}

}
