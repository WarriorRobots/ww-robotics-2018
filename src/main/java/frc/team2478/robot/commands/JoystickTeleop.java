package frc.team2478.robot.commands;

/**
 * When called, robot will drive normally using the Y-axes of both joysticks.
 */
public class JoystickTeleop extends CommandBase {
	
    public JoystickTeleop() {
    	requires(drivetrain);
    }

    protected void initialize() {
    }

    protected void execute() {
    	drivetrain.tankDriveTeleop(oi.getLeftY(), oi.getRightY());
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