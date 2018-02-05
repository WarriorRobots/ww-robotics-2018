package frc.team2478.robot.interfaces;

public interface GyroscopeInterface {
	
	public double getPitch();
	
	public double getRoll();
		
	public double getYaw();
	
	public void resetYaw();
	
	public double getAngle();
	
	public void resetAngle();

}
