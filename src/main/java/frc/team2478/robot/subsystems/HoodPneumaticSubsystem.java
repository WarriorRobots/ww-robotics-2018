package frc.team2478.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2478.robot.interfaces.PneumaticInterface;

public class HoodPneumaticSubsystem extends Subsystem implements PneumaticInterface {

	public static final int SOL_CHANNEL_FORWARDS = 7;
	public static final int SOL_CHANNEL_BACKWARDS = 0;
	
	private DoubleSolenoid solenoid;
	
	public HoodPneumaticSubsystem() {
//		DriverStation.reportError("Change IDs of the solenoids", false);
		solenoid = new DoubleSolenoid(SOL_CHANNEL_FORWARDS, SOL_CHANNEL_BACKWARDS);
	}
	
	@Override
	public void setPistonState(Mode mode) {
		switch(mode) {
		case FORWARD:
			solenoid.set(Value.kForward);
			break;
		case REVERSE:
			solenoid.set(Value.kReverse);
			break;
		case OFF:
			solenoid.set(Value.kOff);
			break;
		}
	}

	@Override
	protected void initDefaultCommand() {}
}
