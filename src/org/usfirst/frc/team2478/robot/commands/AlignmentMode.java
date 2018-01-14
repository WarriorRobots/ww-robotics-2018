package org.usfirst.frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2478.robot.Robot;

public class AlignmentMode extends Command {

    public AlignmentMode() {
    	requires(Robot.drivetrain);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.drivetrain.differentialDrive.arcadeDrive(Robot.oi.getForwardSpeed(), Robot.oi.getTurnSpeed());
    }

    protected boolean isFinished() { // needs a stop condition!
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}