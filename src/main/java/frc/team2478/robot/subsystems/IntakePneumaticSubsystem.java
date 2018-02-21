package frc.team2478.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2478.robot.util.enums.Mode;

public class IntakePneumaticSubsystem extends Subsystem {

	public static final int SOL_CHANNEL_FORWARDS = 6;
	public static final int SOL_CHANNEL_BACKWARDS = 1;
	
	private DoubleSolenoid solenoid;
	
	public IntakePneumaticSubsystem() {
		DriverStation.reportError("Change IDs of the solenoids", false);
		//solenoid = new DoubleSolenoid(SOL_CHANNEL_FORWARDS, SOL_CHANNEL_BACKWARDS);
	}
	
	public void setPistonState(Mode mode) {
		DriverStation.reportError("Change IDs of the solenoids", false);
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
