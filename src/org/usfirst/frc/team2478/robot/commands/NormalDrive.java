package org.usfirst.frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2478.robot.Robot;

public class NormalDrive extends Command {
	
    public NormalDrive() {
    	requires(Robot.drivetrain);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.drivetrain.differentialDrive.tankDrive(Robot.oi.getLeftSpeed(), Robot.oi.getRightSpeed());
    }

    protected boolean isFinished() { // needs a stop condition!
        return false;
    }

    protected void end() {
    }

    protected void interrupted() { // will activate when AlignmentMode or LockMode is run
    }
}
