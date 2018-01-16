package org.usfirst.frc.team2478.robot.commands;

import org.usfirst.frc.team2478.robot.Robot;
import org.usfirst.frc.team2478.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class LockMode extends Command {
    
//    public double getAvg() {
//    	return avg;
//    }

	public LockMode() {
        requires(Robot.drivetrain);
    }

    protected void initialize() {}

    protected void execute() {
		
    	System.out.println("If lockmode is broken, check If statement (see alex)");
    	
    	// how far apart are the joystick Y-axes?
		double difference = Math.abs(Robot.oi.getLeftSpeed() - Robot.oi.getRightSpeed());
		double average = (Robot.oi.getLeftSpeed() + Robot.oi.getRightSpeed()) / 2;
		
    	if (difference < RobotMap.LOCKMODE_THRESHOLD) {
    		Robot.drivetrain.differentialDrive.tankDrive(average, average);
    	} else {
    		Robot.drivetrain.differentialDrive.tankDrive(Robot.oi.getLeftSpeed(), Robot.oi.getRightSpeed());
    	}
    	
    }

    protected boolean isFinished() { // needs a stop condition!
        return false;
    }

    protected void end() {}

    protected void interrupted() {
    	System.out.println("LockMode interrupted+++++++++++++++++++++++++++++++++++++++");
    }
}
