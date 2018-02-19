package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2478.robot.Robot;

/**
 * This command runs for one single loop, setting the drivetrain into reverse direction.
 * <p>It should be called with {@code whenPressed()} or {@code whenReleased()}, <b>NOT {@code whileHeld()}...
 * otherwise the robot will reverse itself every tick!</b>
 */
public class InputReverse extends InstantCommand {
	
    public InputReverse() {
    	requires(Robot.drivetrain);
    }

    protected void execute() { // this runs once and only once!
    	Robot.drivetrain.setReversed(!Robot.drivetrain.getReversed());
    }

}