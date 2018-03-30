package frc.team2478.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.team2478.robot.Constants;
import frc.team2478.robot.commands.scoring.feed.RunFeedWithJoystick;
import frc.team2478.robot.interfaces.TandemMotorInterface;

/**
 * Contains the wheels/belts that push the cube into shooter wheels.
 */
public class FeedSubsystem extends Subsystem implements TandemMotorInterface {

	private static final int RIGHT_MOTOR = 9; // right
	private static final int LEFT_MOTOR = 10; // left
	private static final int INFARED_SENSOR_ID = 9;
	
	private WPI_TalonSRX leftMotor, rightMotor;
	private DigitalInput infaredSensor;
	
	public FeedSubsystem() { // there's not supposed to be a follow command
		leftMotor = new WPI_TalonSRX(LEFT_MOTOR);
		rightMotor = new WPI_TalonSRX(RIGHT_MOTOR);
		leftMotor.setInverted(Constants.Inversions.FEED_LEFT_REVERSED);
		rightMotor.setInverted(Constants.Inversions.FEED_RIGHT_REVERSED);
		infaredSensor = new DigitalInput(INFARED_SENSOR_ID);
	}

	@Override
	public void runAtPercentage(double percent) {
		leftMotor.set(ControlMode.PercentOutput, percent);
		rightMotor.set(ControlMode.PercentOutput, -percent);
	}

	@Override
	public void stop() {
		leftMotor.stopMotor();
		rightMotor.stopMotor();
	}
		
	/**
	 * Detects if a cube is loaded into the lower half of the robot.
	 * @return True if a cube is present, false otherwise.
	 */
	public boolean isCubeLoaded() {
		// the infared sensor returns whether the cube is *not* present
		return !infaredSensor.get();
	}
	
	@Override
	public void initSendable(SendableBuilder builder) {
		builder.setSmartDashboardType("subsystem-feed");
		builder.addBooleanProperty("cube-loaded", () -> isCubeLoaded(), null);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new RunFeedWithJoystick());
	}
}
