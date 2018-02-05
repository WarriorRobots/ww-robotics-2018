package frc.team2478.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.team2478.robot.commands.JoystickTeleop;
import frc.team2478.robot.interfaces.DoubleEncoderInterface;
import frc.team2478.robot.interfaces.DriveInterface;

/**
 * Instantiates drivetrain motors and provides methods for running WPILib drive functions.
 */
public class DrivetrainSubsystem extends Subsystem implements DriveInterface, DoubleEncoderInterface {

	public static final int LEFT_FRONT = 2;
	public static final int LEFT_MIDDLE = 4;
	public static final int LEFT_BACK = 9;
	public static final int RIGHT_FRONT = 6;
	public static final int RIGHT_MIDDLE = 7;
	public static final int RIGHT_BACK = 8;
	
	public static final int LEFT_ENCODER_PORTA = 2;
	public static final int LEFT_ENCODER_PORTB = 3;
	public static final int RIGHT_ENCODER_PORTA = 0;
	public static final int RIGHT_ENCODER_PORTB = 1;
	
	private WPI_TalonSRX m_leftFront, m_leftMiddle, m_leftBack, m_rightFront, m_rightMiddle, m_rightBack;
	private SpeedControllerGroup m_leftGroup, m_rightGroup;
	private DifferentialDrive m_differentialDrive;
	
	private Encoder m_leftEnc, m_rightEnc;
	
	public DrivetrainSubsystem() {
		m_leftFront = new WPI_TalonSRX(LEFT_FRONT);
		m_leftMiddle = new WPI_TalonSRX(LEFT_MIDDLE);
		m_leftBack = new WPI_TalonSRX(LEFT_BACK);
		m_leftGroup = new SpeedControllerGroup(m_leftFront, m_leftMiddle, m_leftBack);
		
		m_rightFront = new WPI_TalonSRX(RIGHT_FRONT);
		m_rightMiddle = new WPI_TalonSRX(RIGHT_MIDDLE);
		m_rightBack = new WPI_TalonSRX(RIGHT_BACK);
		m_rightGroup = new SpeedControllerGroup(m_rightFront, m_rightMiddle, m_rightBack);

		m_differentialDrive = new DifferentialDrive(m_leftGroup, m_rightGroup);
		
		m_leftEnc = new Encoder(LEFT_ENCODER_PORTA, LEFT_ENCODER_PORTB);
		m_rightEnc = new Encoder(RIGHT_ENCODER_PORTA, RIGHT_ENCODER_PORTB);
	}
	
	/**
	 * Drives the left and right sides separately, with inputs squared for ease of driver use.
	 * <p> DO NOT USE WITH PID.
	 * @param leftSpeed  Percentage speed of left side, from -1 to 1.
	 * @param rightSpeed  Percentage speed of right side, from -1 to 1.
	 */
	@Override
	public void tankDriveSquared(double leftSpeed, double rightSpeed) {
		m_differentialDrive.tankDrive(leftSpeed, rightSpeed, true);
	}
	
	/**
	 * Drives the left and right sides separately, with inputs NOT squared.
	 * Intended for use with PID and autonomous.
	 * @param leftSpeed  Percentage speed of left side, from -1 to 1.
	 * @param rightSpeed  Percentage speed of right side, from -1 to 1.
	 */
	@Override
	public void tankDriveRaw(double leftSpeed, double rightSpeed) {
		m_differentialDrive.tankDrive(leftSpeed, rightSpeed, false);
	}
	
	/**
	 * Sets forward and turning speed, with inputs squared of ease of driver use.
	 * Intended for use with PID and autonomous.
	 * @param forwardSpeed  Percentage speed of forwards drive, from -1 to 1.
	 * @param turnSpeed  Percentage speed of turning, from -1 to 1.
	 */
	@Override
	public void arcadeDriveSquared(double forwardSpeed, double turnSpeed) {
		m_differentialDrive.arcadeDrive(forwardSpeed, turnSpeed, true);
	}
	
	/**
	 * Sets forward and turning speed, with inputs NOT squared.
	 * <p> DO NOT USE WITH PID.
	 * @param forwardSpeed  Percentage speed of forwards drive, from -1 to 1.
	 * @param turnSpeed  Percentage speed of turning, from -1 to 1.
	 */
	@Override
	public void arcadeDriveRaw(double forwardSpeed, double turnSpeed) {
		m_differentialDrive.arcadeDrive(forwardSpeed, turnSpeed, false);
	}
	
	/**
	 * Shuts off motors and stops driving.
	 */
	@Override
	public void stopDrive() {
		m_differentialDrive.stopMotor();
	}

	@Override
	public int getEncoderTicks(Side e) {
		switch(e) {
		case LEFT:
			return m_leftEnc.get();
		case RIGHT:
			return m_rightEnc.get();
		default: // compiler will crash without a default statement
			throw new IllegalArgumentException("Invalid argument for getEncoderTicks()");
		}
	}

	@Override
	public void resetEncoderTicks(Side e) {
		switch(e) {
		case LEFT:
			m_leftEnc.reset();
			break;
		case RIGHT:
			m_rightEnc.reset();
			break;
		}
	}

	@Override
	public void resetEncoders() {
		this.resetEncoderTicks(Side.LEFT);
		this.resetEncoderTicks(Side.RIGHT);
	}
	
	@Override
	public void printEncoderData() {
		System.out.println("LEFT: " + Double.toString(getEncoderTicks(Side.LEFT)) + 
						  " RIGHT: " + Double.toString(getEncoderTicks(Side.RIGHT)));
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new JoystickTeleop());
	}
}