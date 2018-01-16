package org.usfirst.frc.team2478.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;

public class MotionSensorsSubsystem extends Subsystem {

	public AHRS navx = new AHRS(I2C.Port.kMXP);
	
	public Encoder leftEnc = new Encoder(2,3);
	public Encoder rightEnc = new Encoder(0,1);
	
    public void initDefaultCommand() {
    	// none
    }
}