package frc.team2478.robot.interfaces;

public interface PIDInterface {
	
	public void setPID(double p, double i, double d);
	
	public void setFeedForward(double f);

}
