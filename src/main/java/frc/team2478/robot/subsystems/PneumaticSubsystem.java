package frc.team2478.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.team2478.robot.util.enums.Mode;

public class PneumaticSubsystem extends Subsystem {

	public static final int HOOD_FORWARDS_CHANNEL = 7;
	public static final int HOOD_BACKWARDS_CHANNEL = 0;
	
	public static final int INTAKE_FORWARDS_CHANNEL = 6;
	public static final int INTAKE_BACKWARDS_CHANNEL = 1;
	
	private DoubleSolenoid hoodSolenoid, intakeSolenoid;
	private Compressor compressor;
	
	private boolean hoodLifted, intakeExtended;
	
	public PneumaticSubsystem() {
//		DriverStation.reportError("Change IDs of the solenoids", false);
		hoodSolenoid = new DoubleSolenoid(HOOD_FORWARDS_CHANNEL, HOOD_BACKWARDS_CHANNEL);
		intakeSolenoid = new DoubleSolenoid(INTAKE_FORWARDS_CHANNEL, INTAKE_BACKWARDS_CHANNEL);
		compressor = new Compressor();
		compressor.setClosedLoopControl(true);
	}
	
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
	
	public boolean isLowPressure() {
		return compressor.getPressureSwitchValue();
	}
	
	public void setClosedLoop(boolean isClosedLoop) {
		compressor.setClosedLoopControl(isClosedLoop);
	}

	@Override
	protected void initDefaultCommand() {}
	
	/**
	 * Dashboard setup for hood.
	 * @author Josh
	 */
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
