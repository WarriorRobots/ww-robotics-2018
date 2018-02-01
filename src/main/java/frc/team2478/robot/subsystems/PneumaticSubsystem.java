package frc.team2478.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PneumaticSubsystem extends Subsystem {
	
	private DoubleSolenoid m_solenoid;
	private Compressor m_compressor;
	
	private final int TEMP1 = 0;
	private final int TEMP2 = 1;
	
	public void init() {
		m_solenoid = new DoubleSolenoid(TEMP1, TEMP2);
		
		m_compressor = new Compressor(0);
		m_compressor.setClosedLoopControl(true);	
	}
	
	public void solenoidForward() {
		m_solenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	public void solenoidReverse() {
		m_solenoid.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void solenoidOff() {
		m_solenoid.set(DoubleSolenoid.Value.kOff);
	}

	@Override
	protected void initDefaultCommand() {}
}