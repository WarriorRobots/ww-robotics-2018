package frc.team2478.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import frc.team2478.robot.commands.JoystickTeleop;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * Instantiates drivetrain motors and provides methods for running WPILib drive functions.
 */
public final class DrivetrainSubsystem extends Subsystem {

	public static final int LEFT_FRONT = 2;
	public static final int LEFT_BACK = 4;
	public static final int RIGHT_FRONT = 1;
	public static final int RIGHT_BACK = 3;
	
	private WPI_TalonSRX m_leftFront, m_leftBack, m_rightFront, m_rightBack;
	private SpeedControllerGroup m_leftGroup, m_rightGroup;
	private DifferentialDrive m_differentialDrive;
	
	/**
	* Initializes Talons and drivetrain subsystem; run before calling drive functions.
	*/
	public void init() {
		m_leftFront = new WPI_TalonSRX(LEFT_FRONT);
		m_leftBack = new WPI_TalonSRX(LEFT_BACK);
		m_leftGroup = new SpeedControllerGroup(m_leftFront, m_leftBack);
		
		m_rightFront = new WPI_TalonSRX(RIGHT_FRONT);
		m_rightBack = new WPI_TalonSRX(RIGHT_BACK);
		m_rightGroup = new SpeedControllerGroup(m_rightFront, m_rightBack);

		m_differentialDrive = new DifferentialDrive(m_leftGroup, m_rightGroup);
	}
	
	/**
	 * Drives the left and right sides separately, with inputs squared for ease of driver use.
	 * <p> DO NOT USE WITH PID.
	 * @param leftSpeed  Percentage speed of left side, from -1 to 1.
	 * @param rightSpeed  Percentage speed of right side, from -1 to 1.
	 */
	public void tankDriveTeleop(double leftSpeed, double rightSpeed) {
		m_differentialDrive.tankDrive(leftSpeed, rightSpeed, true);
	}
	
	/**
	 * Drives the left and right sides separately, with inputs NOT squared.
	 * Intended for use with PID and autonomous.
	 * @param leftSpeed  Percentage speed of left side, from -1 to 1.
	 * @param rightSpeed  Percentage speed of right side, from -1 to 1.
	 */
	public void tankDriveAutonomo(double leftSpeed, double rightSpeed) {
		m_differentialDrive.tankDrive(leftSpeed, rightSpeed, false);
	}
	
	/**
	 * Sets forward and turning speed, with inputs squared of ease of driver use.
	 * Intended for use with PID and autonomous.
	 * @param forwardSpeed  Percentage speed of forwards drive, from -1 to 1.
	 * @param turnSpeed  Percentage speed of turning, from -1 to 1.
	 */
	public void arcadeDriveTeleop(double forwardSpeed, double turnSpeed) {
		m_differentialDrive.arcadeDrive(forwardSpeed, turnSpeed, true);
	}
	
	/**
	 * Sets forward and turning speed, with inputs NOT squared.
	 * <p> DO NOT USE WITH PID.
	 * @param forwardSpeed  Percentage speed of forwards drive, from -1 to 1.
	 * @param turnSpeed  Percentage speed of turning, from -1 to 1.
	 */
	public void arcadeDriveAutonomo(double forwardSpeed, double turnSpeed) {
		m_differentialDrive.arcadeDrive(forwardSpeed, turnSpeed, false);
	}
	
	/**
	 * Shuts off motors and stops driving.
	 */
	public void stopDrive() {
		m_differentialDrive.stopMotor();
	}

    public void initDefaultCommand() {
    	 setDefaultCommand(new JoystickTeleop());
    }
}