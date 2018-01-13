package org.usfirst.frc.team2478.robot.commands;

import org.usfirst.frc.team2478.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LockMode extends Command {
	
    public double avg;

	public LockMode() {
        requires(Robot.drivetrain);
    }

    protected void initialize() {
    }

    protected void execute() {
    	if (Math.abs(Robot.oi.getLeftSpeed() - Robot.oi.getRightSpeed()) < 0.10) {
    		avg = (Robot.oi.getLeftSpeed() + Robot.oi.getLeftSpeed()) / 2;
    		Robot.drivetrain.differentialDrive.tankDrive(avg, avg);
    	}
    }

    protected boolean isFinished() { // needs a stop condition!
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
