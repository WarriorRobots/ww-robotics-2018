package frc.team2478.robot.commands;

public class CameraAlign extends CommandBase{
	
	public CameraAlign() {
		requires(drivetrain);
	}
	
	public static void align(double tx) {
		drivetrain.arcadeDriveTeleop(0,tx);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
