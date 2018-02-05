package frc.team2478.robot.interfaces;

public interface DriveInterface {

	public void tankDriveSquared(double leftY, double rightY);
	
	public void tankDriveRaw(double leftY, double rightY);
	
	public void arcadeDriveSquared(double forward, double turn);
	
	public void arcadeDriveRaw(double forward, double turn);
	
	public void stopDrive();
	
}