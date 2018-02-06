package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2478.robot.interfaces.DoubleEncoderInterface;
import frc.team2478.robot.interfaces.DriveInterface;
import frc.team2478.robot.interfaces.GyroscopeInterface;

public class AutonomoGroupTest extends CommandGroup {
	
	public AutonomoGroupTest(DriveInterface drivetrain, DoubleEncoderInterface encoders, GyroscopeInterface gyro) {
		addSequential(new AutonomoDriveStraight(drivetrain, encoders, gyro, 200));
		addSequential(new AutonomoDriveTurn(drivetrain, encoders, gyro, 50));
	}

}
