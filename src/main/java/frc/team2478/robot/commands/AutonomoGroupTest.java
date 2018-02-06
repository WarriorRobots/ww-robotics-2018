package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2478.robot.interfaces.DriveEncoderInterface;
import frc.team2478.robot.interfaces.DrivetrainInterface;
import frc.team2478.robot.interfaces.GyroscopeInterface;

public class AutonomoGroupTest extends CommandGroup {
	
	public AutonomoGroupTest(DrivetrainInterface drivetrain, DriveEncoderInterface encoders, GyroscopeInterface gyro) {
		addSequential(new AutonomoDriveStraight(drivetrain, encoders, gyro, 200));
		addSequential(new AutonomoDriveTurn(drivetrain, encoders, gyro, 50));
	}

}
