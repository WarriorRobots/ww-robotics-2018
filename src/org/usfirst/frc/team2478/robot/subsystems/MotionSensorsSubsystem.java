package org.usfirst.frc.team2478.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;

public class MotionSensorsSubsystem extends Subsystem {

	private AHRS m_navx;
	public Encoder m_leftEnc, m_rightEnc; // make private after debugging
	public Encoder m_brokenEnc;

	public MotionSensorsSubsystem() {
	}
	
	public void init() {
		m_leftEnc = new Encoder(2,3); //remove magic numbers
		m_rightEnc = new Encoder(0,1);
		m_brokenEnc = new Encoder(7,8); // remove after debugging
		try {
			m_navx = new AHRS(I2C.Port.kMXP);
		} catch (RuntimeException ex) {
			DriverStation.reportError("Error instantiating navX MXP: ", true);
			System.out.println("INITIALIZATION FAILED: navX MXP");
		}
	}
	
	public double getAngle() {
		return m_navx.getAngle();
	}
	
	public double getLeftEncCount() {
		return m_leftEnc.get();
	}
	
	public double getRightEncCount() {
		return m_rightEnc.get();
	}
	
	public void resetNavx() {
		m_navx.reset();
	}

	public void resetEncoders() {
		m_leftEnc.reset();
		m_rightEnc.reset();
	}
	
	public void resetSensors() {
		this.resetNavx();
		this.resetSensors();
	}
	
    public void initDefaultCommand() {}
}