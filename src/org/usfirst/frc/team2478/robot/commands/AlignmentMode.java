package org.usfirst.frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2478.robot.Robot;
import org.usfirst.frc.team2478.robot.*;

public class AlignmentMode extends Command {

    public AlignmentMode() {
    	requires(Robot.drivetrain);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.drivetrain.differentialDrive.arcadeDrive(OI.getForwardSpeed(), OI.getTurnSpeed());
    }

    protected boolean isFinished() { // needs a stop condition!
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}