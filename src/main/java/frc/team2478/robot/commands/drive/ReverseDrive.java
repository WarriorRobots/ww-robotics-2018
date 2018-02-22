package frc.team2478.robot.commands.drive;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2478.robot.Robot;

/**
 * This command runs for one single loop, setting the drivetrain into reverse direction.
 * <p>It should be called with {@code whenPressed()} or {@code whenReleased()}, <b>NOT {@code whileHeld()}...
 * otherwise the robot will reverse itself every tick!</b>
 */
public class ReverseDrive extends InstantCommand {
	
    public ReverseDrive() {
    	requires(Robot.drivetrain);
    }

    @Override
	protected void execute() { // this runs once and only once!
    	Robot.drivetrain.setReversed(!Robot.drivetrain.getReversed());
    }

}