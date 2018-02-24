package frc.team2478.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.team2478.robot.interfaces.PneumaticInterface;

public class HoodPneumaticSubsystem extends Subsystem implements PneumaticInterface {

	public static final int LEFT_SOL_CHANNEL_FORWARDS = 0;
	public static final int LEFT_SOL_CHANNEL_BACKWARDS = 0;
	public static final int RIGHT_SOL_CHANNEL_FORWARDS = 0;
	public static final int RIGHT_SOL_CHANNEL_BACKWARDS = 0;
	public static final double PULSE_DURATION = 0;
	/**
	 * True when hood is lifted.
	 * @author Josh
	 */
	public static boolean solenoidExtended = false;
	
	private DoubleSolenoid leftSol, rightSol;
	
	public HoodPneumaticSubsystem() {
		DriverStation.reportError("Change IDs of the solenoids", false);
		leftSol = new DoubleSolenoid(LEFT_SOL_CHANNEL_FORWARDS, LEFT_SOL_CHANNEL_BACKWARDS);
		rightSol = new DoubleSolenoid(RIGHT_SOL_CHANNEL_FORWARDS, RIGHT_SOL_CHANNEL_BACKWARDS);
	}
	
	@Override
	public void setPistonState(Mode mode) {
		switch(mode) {
		case FORWARD:
			leftSol.set(Value.kForward);
			rightSol.set(Value.kForward);
			solenoidExtended = true;
			break;
		case REVERSE:
			leftSol.set(Value.kReverse);
			rightSol.set(Value.kReverse);
			solenoidExtended = false;
			break;
		case OFF:
			leftSol.set(Value.kOff);
			rightSol.set(Value.kOff);
			break;
		}
	}
	
	/**
	 * @author Josh
	 * @return True when hood is lifted (according to code, not physically).
	 */
	public boolean getStasis() {
		return solenoidExtended;
	}
	
	/**
	 * Dashboard setup for hood.
	 * @author Josh
	 */
	@Override
	public void initSendable(SendableBuilder builder) {
		builder.setSmartDashboardType("subsystem-hood");
		builder.addBooleanProperty("solenoid-extended", () -> getStasis(), null);
	}

	@Override
	protected void initDefaultCommand() {}
}
