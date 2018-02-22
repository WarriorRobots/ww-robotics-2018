package frc.team2478.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2478.robot.commands.scoring.feed.RunFeedWithManualControl;

/**
 * Instantiates feed motors on the robot, and provides methods for using them.
 */
public class FeedSubsystem extends Subsystem {

	private final int SLAVE_MOTOR = 9; // right
	private final int MASTER_MOTOR = 10; // left

	private WPI_TalonSRX masterMotor, slaveMotor;
	
	public FeedSubsystem() {
		masterMotor = new WPI_TalonSRX(MASTER_MOTOR);
		slaveMotor = new WPI_TalonSRX(SLAVE_MOTOR);
		
		//slaveMotor.setInverted(true);
		slaveMotor.follow(masterMotor);
	}

	/**
	 * Set the percent motor speed.
	 * @param percent -1 to 1
	 */
	public void setTargetPercentage(double percent) {
		masterMotor.set(ControlMode.PercentOutput, percent);
	}
	
	/**
	 * Stop the shooter.
	 */
	public void stop() {
		masterMotor.stopMotor();
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new RunFeedWithManualControl());
	}
}
