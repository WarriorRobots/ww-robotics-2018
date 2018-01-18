package org.usfirst.frc.team2478.robot.commands;

public class AutonomoDriveStraight extends CommandBase {
	
	private final double AUTO_SPEED = 0.5;
	private double leftCount, rightCount;
	
	public AutonomoDriveStraight() {
		requires(drivetrain);
		requires(motionSensors);
	}
	
	protected void initialize() {}
	
	protected void execute() {
		leftCount = motionSensors.getLeftEncCount();
		rightCount = motionSensors.getRightEncCount();
		drivetrain.tankDrive(AUTO_SPEED, AUTO_SPEED);
		System.out.println("Left: " + Double.toString(leftCount) + ", Right: " + Double.toString(rightCount));
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
