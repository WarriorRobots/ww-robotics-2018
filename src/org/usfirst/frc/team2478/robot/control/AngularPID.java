package org.usfirst.frc.team2478.robot.control;

public class AngularPID extends BasicPID {
	
	private AHRS navx;
	
	//Below initialized control with the navx but without I or D gains
	//	(angular PID loops do not need the I or D)
    ///////////////////////////////////////////////////////////////////////////
	
	public AngularPID(AHRS navx, double p) {
		this(navx,p,0);
	}
	
	public AngularPID(AHRS navx, double p, double setPoint) {
		super(p,0,0,setPoint);
		setIzone(ALWAYS_FALSE);
		this.navx = navx;
	}
	
    ///////////////////////////////////////////////////////////////////////////
	
	//Below updates the PID with navx
	public void update() {
		double head = navx.getYaw();
		calculate(0.0,head);
	}

}
