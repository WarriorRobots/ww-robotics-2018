package frc.team2478.robot.interfaces;

public interface PneumaticInterface {
	
	public enum Mode {
		FORWARD,
		REVERSE,
		OFF
	}
	
	public void setPistonState(Mode s);
	
	public Mode getPistonState();
	
}