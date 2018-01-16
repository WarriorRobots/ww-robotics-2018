package org.usfirst.frc.team2478.robot.commands;

import org.usfirst.frc.team2478.robot.Robot;
import org.usfirst.frc.team2478.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class LockMode extends Command {
	
    public double avg;
    public double getavg() {
    	return avg;
    }

	public LockMode() {
        requires(Robot.drivetrain);
    }

    protected void initialize() {}

    protected void execute() {
		
    	System.out.println("If lockmode is broken, check If statement (see alex)");
    	
		double difference = Math.abs(Robot.oi.getLeftSpeed() - Robot.oi.getRightSpeed());
    	if (difference < RobotMap.LOCKMODE_THRESHOLD) {
    		avg = (Robot.oi.getLeftSpeed() + Robot.oi.getLeftSpeed()) / 2;
    		Robot.drivetrain.differentialDrive.tankDrive(avg, avg);
    	} else {
    		Robot.drivetrain.differentialDrive.tankDrive(Robot.oi.getLeftSpeed(), Robot.oi.getRightSpeed());
    	}
    	
    }

    protected boolean isFinished() { // needs a stop condition!
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
