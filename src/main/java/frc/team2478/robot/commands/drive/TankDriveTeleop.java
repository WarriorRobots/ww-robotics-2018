package frc.team2478.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;

/**
 * When called, robot will drive in Tank Drive using the Y-axes of both joysticks.
 */
public class TankDriveTeleop extends Command {
	
    public TankDriveTeleop() {
    	requires(Robot.drivetrain);
    }

    @Override
	protected void execute() {
    	Robot.drivetrain.tankDriveSquared(Robot.oi.getLeftY(), Robot.oi.getRightY());
    }

    @Override
	protected boolean isFinished() {
        return false;
    }
}