package org.usfirst.frc.team2478.robot.control;

public class AngularPID extends BasicPID {
	
	private AHRS navx;
	
	public AngularPID(AHRS navx, double p) {
		this(navx,p,0);
	}
	
	public AngularPID(AHRS navx, double p, double setPoint) {
		super(p,0,0,setPoint);
		setIzone(ALWAYS_FALSE);
		this.navx = navx;
	}
	
	public void update() {
		double head = 0.0/*navx.getYaw()*/; //check output type
		calculate(0.0,head);
	}

}
