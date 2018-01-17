package org.usfirst.frc.team2478.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;

public class MotionSensorsSubsystem extends Subsystem {

	public AHRS navx;
	public Encoder leftEnc, rightEnc;

	public MotionSensorsSubsystem() {
		navx = new AHRS(I2C.Port.kMXP);
		
		// try {
		// 	leftEnc = new Encoder(2,3);
		// 	rightEnc = new Encoder(0,1);
		// } catch (Exception e) {
		// 	System.out.println(e.getMessage());
		// }

		leftEnc = new Encoder(2,3);
		rightEnc = new Encoder(0,1);
	}
	
    public void initDefaultCommand() {
    	// none
    }
}