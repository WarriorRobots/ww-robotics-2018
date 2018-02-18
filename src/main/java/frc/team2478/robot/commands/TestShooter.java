package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Robot;

public class TestShooter extends Command{

	public TestShooter() {
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.bob.periodic();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }

}
