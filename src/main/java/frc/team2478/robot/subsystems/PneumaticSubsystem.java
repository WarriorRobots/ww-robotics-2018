package frc.team2478.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.team2478.robot.util.enums.Mode;

/**
 * Components that use the pneumatics on the robot.
 */
public class PneumaticSubsystem extends Subsystem {

	private static final int HOOD_FORWARDS_CHANNEL = 7;
	private static final int HOOD_BACKWARDS_CHANNEL = 0;
	
	private static final int INTAKE_FORWARDS_CHANNEL = 6;
	private static final int INTAKE_BACKWARDS_CHANNEL = 1;
	
	private DoubleSolenoid hoodSolenoid, intakeSolenoid;
	private Compressor compressor;
	
	private boolean hoodLifted, intakeExtended;
	
	public PneumaticSubsystem() {
		hoodSolenoid = new DoubleSolenoid(HOOD_FORWARDS_CHANNEL, HOOD_BACKWARDS_CHANNEL);
		intakeSolenoid = new DoubleSolenoid(INTAKE_FORWARDS_CHANNEL, INTAKE_BACKWARDS_CHANNEL);
		compressor = new Compressor();
		compressor.start();
	}
	
	/**
	 * Extends, retracts, or disables the hood pistons.
	 * @param mode  Mode.FORWARD, Mode.REVERSE, or Mode.OFF
	 */
	public void setHoodPiston(Mode mode) {
		switch(mode) {
		case FORWARD:
			hoodSolenoid.set(Value.kForward);
			hoodLifted = true;
			break;
		case REVERSE:
			hoodSolenoid.set(Value.kReverse);
			hoodLifted = false;
			break;
		case OFF:
			hoodSolenoid.set(Value.kOff);
			break;
		}
	}
	
	/**
	 * Extends, retracts, or disables the intake pistons.
	 * @param mode  Mode.FORWARD, Mode.REVERSE, or Mode.OFF
	 */
	public void setIntakePiston(Mode mode) {
		switch(mode) {
		case FORWARD:
			intakeSolenoid.set(Value.kForward);
			intakeExtended = false;
			break;
		case REVERSE:
			intakeSolenoid.set(Value.kReverse);
			intakeExtended = true;
			break;
		case OFF:
			intakeSolenoid.set(Value.kOff);
			break;
		}
	}
	
	/**
	 * Checks if the robot low pressure switch is enabled (typically below 95 psi).
	 * @return True if pressure is low, false otherwise
	 */
	public boolean isLowPressure() {
		return compressor.getPressureSwitchValue();
	}
	
	/**
	 * Allows the compressor to run IF AND ONLY IF pressure is low. DOES NOT FORCIBLY TURN THE COMPRESSOR ON.
	 */
	public void startCompressor() {
		compressor.start();
	}
	
	/**
	 * Stops the compressor from running in any circumstances.
	 */
	public void stopCompressor() {
		compressor.stop();
	}

	@Override
	protected void initDefaultCommand() {}
	
	@Override
	public void initSendable(SendableBuilder builder) {
		builder.setSmartDashboardType("subsystem-pneumatics");
		builder.addBooleanProperty("hood-up", () -> {
			return hoodLifted;
		}, null);
		builder.addBooleanProperty("intake-out", () -> {
			return intakeExtended;
		}, null);
	}

}
