package org.usfirst.frc.team2478.robot.commands;

import org.usfirst.frc.team2478.robot.RobotMap;

public class AutonomoDriveStraight extends CommandBase {
	
	private double m_leftCount, m_rightCount;
	
	public AutonomoDriveStraight() {
		requires(drivetrain);
		requires(motionSensors);
	}
	
	protected void initialize() {}
	
	protected void execute() {
		m_leftCount = motionSensors.getLeftEncCount();
		m_rightCount = motionSensors.getRightEncCount();
		drivetrain.tankDriveAutonomo(RobotMap.AUTO_SPEED_FORWARDS, RobotMap.AUTO_SPEED_FORWARDS);
		System.out.println("Left: " + Double.toString(m_leftCount) + ", Right: " + Double.toString(m_rightCount));
		System.out.println(motionSensors.m_leftEnc.getRate());
	}

	protected boolean isFinished() {
		if (motionSensors.getLeftEncCount() > 250 && motionSensors.getRightEncCount() > 250) {
			return true;
		} else {
			return false;
		}
	}
}
