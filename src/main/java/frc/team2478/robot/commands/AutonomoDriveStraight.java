package frc.team2478.robot.commands;

import frc.team2478.robot.RobotMap;

public class AutonomoDriveStraight extends CommandBase {
	
	private double m_distance;
	
	public AutonomoDriveStraight(double distance) {
		requires(drivetrain);
		requires(motionSensors);
		m_distance = distance;
	}
	
	protected void initialize() {
		motionSensors.resetEncoders();
	}
	
	protected void execute() {
		drivetrain.tankDriveAutonomo(RobotMap.AUTO_SPEED_FORWARDS, RobotMap.AUTO_SPEED_FORWARDS);
	}

	protected boolean isFinished() {
		if (motionSensors.getLeftEncCount() > m_distance && motionSensors.getRightEncCount() > m_distance) {
			return true;
		} else {
			return false;
		}
	}
}
