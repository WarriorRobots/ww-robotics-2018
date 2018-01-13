package org.usfirst.frc.team2478.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Motion extends Subsystem {

	public AHRS navx = new AHRS(I2C.Port.kMXP);
	
    public void initDefaultCommand() {
    	// none
    }
    
    public double measureAngle() {
    	
    	return Math.abs(navx.getAngle() % 360);
    	
    }
}

