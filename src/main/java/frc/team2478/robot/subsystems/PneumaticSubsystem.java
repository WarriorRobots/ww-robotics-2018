package frc.team2478.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2478.robot.util.enums.Mode;

public class PneumaticSubsystem extends Subsystem {

	public static final int HOOD_FORWARDS_CHANNEL = 7;
	public static final int HOOD_BACKWARDS_CHANNEL = 0;
	
	public static final int INTAKE_FORWARDS_CHANNEL = 6;
	public static final int INTAKE_BACKWARDS_CHANNEL = 2;
	
	private DoubleSolenoid hoodSolenoid, intakeSolenoid;
	
	public PneumaticSubsystem() {
//		DriverStation.reportError("Change IDs of the solenoids", false);
		hoodSolenoid = new DoubleSolenoid(HOOD_FORWARDS_CHANNEL, HOOD_BACKWARDS_CHANNEL);
	}
	
	public void setHoodPiston(Mode mode) {
		switch(mode) {
		case FORWARD:
			hoodSolenoid.set(Value.kForward);
			break;
		case REVERSE:
			hoodSolenoid.set(Value.kReverse);
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
			break;
		case REVERSE:
			intakeSolenoid.set(Value.kReverse);
			break;
		case OFF:
			intakeSolenoid.set(Value.kOff);
			break;
		}
	}

	@Override
	protected void initDefaultCommand() {}
}
