package frc.team2478.robot.interfaces;

public interface ShooterInterface extends PIDInterface, SingleEncoderInterface, MotorInterface {
	
	public void setTargetVelocity(double velocity);
	
	public boolean isUsingPid();
	
	public void setUsingPid(boolean b);
	
}
