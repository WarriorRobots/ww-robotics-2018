package frc.team2478.robot.interfaces;

public interface DoubleEncoderInterface {
	
	public enum Side {
		LEFT,
		RIGHT
	}
	
	public int getEncoderTicks(Side e);
	
	public void resetEncoderTicks(Side e);
	
	public void resetEncoders();
	
	public void printEncoderData();

}