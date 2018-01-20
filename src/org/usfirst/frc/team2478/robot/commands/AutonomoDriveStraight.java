package org.usfirst.frc.team2478.robot.commands;

import org.usfirst.frc.team2478.robot.RobotMap;

public class AutonomoDriveStraight extends CommandBase {
		
	public AutonomoDriveStraight() {
		requires(drivetrain);
		requires(motionSensors);
	}
	
	protected void initialize() {
		motionSensors.resetEncoders();
	}
	
	protected void execute() {
		drivetrain.tankDrive(RobotMap.AUTO_SPEED_FORWARDS, RobotMap.AUTO_SPEED_FORWARDS);
	}

	protected boolean isFinished() {
		if (motionSensors.getLeftEncCount() > 250 && motionSensors.getRightEncCount() > 250) {
			return true;
		} else {
			return false;
		}
	}
}
