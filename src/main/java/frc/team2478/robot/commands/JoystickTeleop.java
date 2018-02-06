package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2478.robot.interfaces.DriveInterface;
import frc.team2478.robot.util.ControlHandler;

/**
 * When called, robot will drive normally using the Y-axes of both joysticks.
 */
public class JoystickTeleop extends Command {
	
	private DriveInterface drivetrain;
	private ControlHandler oi;
	
    public JoystickTeleop(ControlHandler oi, DriveInterface drivetrain) {
    	this.drivetrain = drivetrain;
    	requires((Subsystem) drivetrain);
    	this.oi = oi;
    }

    protected void initialize() {
    }

    protected void execute() {
    	drivetrain.tankDriveSquared(oi.getLeftY(), oi.getRightY());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    	this.end();
    }
}