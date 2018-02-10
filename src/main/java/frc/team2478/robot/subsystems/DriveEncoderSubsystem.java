package frc.team2478.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2478.robot.interfaces.DriveEncoderInterface;

public class DriveEncoderSubsystem extends Subsystem implements DriveEncoderInterface {

	public static final int LEFT_ENCODER_PORTA = 2;
	public static final int LEFT_ENCODER_PORTB = 3;
	public static final int RIGHT_ENCODER_PORTA = 0;
	public static final int RIGHT_ENCODER_PORTB = 1;
	
	private Encoder leftEnc, rightEnc;
	
	public DriveEncoderSubsystem() {
		leftEnc = new Encoder(LEFT_ENCODER_PORTA, LEFT_ENCODER_PORTB);
		rightEnc = new Encoder(RIGHT_ENCODER_PORTA, RIGHT_ENCODER_PORTB);
	}
	
	@Override
	public int getEncoderTicks(Side side) {
		switch(side) {
		case LEFT: return leftEnc.get();
		case RIGHT: return -rightEnc.get();
		default: // compiler will crash without a default statement
			throw new IllegalArgumentException("Invalid argument for getEncoderTicks()");
		}
	}

	@Override
	public void resetEncoderTicks(Side side) {
		switch(side) {
		case LEFT:
			leftEnc.reset();
			break;
		case RIGHT:
			rightEnc.reset();
			break;
		}
	}

	@Override
	public void resetEncoders() {
		leftEnc.reset();
		rightEnc.reset();
	}
	
	@Override
	public void printEncoderData() {
		System.out.println("LEFT: " + Double.toString(getEncoderTicks(Side.LEFT)) + 
						  " RIGHT: " + Double.toString(getEncoderTicks(Side.RIGHT)));
	}

	@Override
	protected void initDefaultCommand() {}
}
