package org.usfirst.frc.team2478.robot.deprecated;

import com.kauailabs.navx.frc.AHRS;

public class AngularPID extends BasicPID{

	private AHRS navx;
	
    ///////////////////////////////////////////////////////////////////////////
	
	public AngularPID(AHRS navx, double p, double i, double d) {
		this(navx,p,i,d,0.0);
	}
	
	public AngularPID(AHRS navx, double p, double i, double d, double setPoint) {
		super(p,i,d,setPoint);
		this.navx = navx;
	}
	
    ///////////////////////////////////////////////////////////////////////////
	
	//Below updates the PID with navx
	public void update() {
		double head = navx.getAngle();
		calculate(head);
	}
	
}
