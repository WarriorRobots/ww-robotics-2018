package frc.team2478.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;

/**
 * When right controller button 3 (or 5) is pressed, toggle the direction of the joystick inputs.
 * Ex: when the drive pressed 3, the robot is now in reverse.
 * Then the driver pushes the joysticks forward, the robot then goes backwards.
*/
public class InputReverse extends Command{
	
	public static boolean pressed = false;

    public InputReverse() {
    	requires(Robot.drivetrain);
    }

    @Override
	protected void initialize() {
    	pressed = true;
    }

    @Override
	protected void execute() {
    	if(pressed)
    		Robot.drivetrain.REVERSE= !Robot.drivetrain.REVERSE;
    	pressed = false;
    	Robot.drivetrain.tankDriveSquared(Robot.oi.getLeftY(), Robot.oi.getRightY());
    }

    @Override
	protected boolean isFinished() {
        return false;
    }

}
