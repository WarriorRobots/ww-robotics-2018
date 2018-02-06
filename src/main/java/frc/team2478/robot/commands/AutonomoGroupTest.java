package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2478.robot.interfaces.DoubleEncoderInterface;
import frc.team2478.robot.interfaces.DriveInterface;
import frc.team2478.robot.interfaces.GyroscopeInterface;

public class AutonomoGroupTest extends CommandGroup {
	
	private DriveInterface m_drivetrain;
	private DoubleEncoderInterface m_encoders;
	private GyroscopeInterface m_gyro;

	public AutonomoGroupTest(DriveInterface drivetrain, DoubleEncoderInterface encoders, GyroscopeInterface gyro) {
		m_drivetrain = drivetrain;
		m_encoders = encoders;
		m_gyro = gyro;
		addSequential(new AutonomoDriveStraight(drivetrain, encoders, gyro, 200));
		addSequential(new AutonomoDriveTurn(drivetrain, encoders, gyro, 50));
	}

}
