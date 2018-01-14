package org.usfirst.frc.team2478.robot.control;

import org.usfirst.frc.team2478.robot.Robot;
import org.usfirst.frc.team2478.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class BetterAngularPID extends PIDSubsystem{
	
	public double output;
	
	public BetterAngularPID() {
		super("Turn", RobotMap.ANGULAR_P, RobotMap.ANGULAR_I, RobotMap.ANGULAR_D);
		setAbsoluteTolerance(RobotMap.ANGULAR_TOLERANCE);
		getPIDController().setContinuous(false);
		this.setSetpoint(RobotMap.ANGULAR_SETPOINT);
	}

	@Override
	protected double returnPIDInput() {
		return Robot.motionSensors.navx.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		this.output = output;
		Robot.drivetrain.differentialDrive.arcadeDrive(0, output);
	}

	@Override
	protected void initDefaultCommand() {}
	
	public double get() {
		return this.getPIDController().get();
	}
	
	public boolean getEnable() {
		return this.getPIDController().isEnabled();
	}
}
