package org.usfirst.frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2478.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2478.robot.Robot;
import org.usfirst.frc.team2478.robot.OI;

/**
 *
 */
public class DriveAlign extends Command {

    public DriveAlign() {
    	requires(Robot.kDriveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	DriveTrain.m_drive.arcadeDrive(OI.rightJoy.getY(), OI.rightJoy.getX());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
// hope it works
