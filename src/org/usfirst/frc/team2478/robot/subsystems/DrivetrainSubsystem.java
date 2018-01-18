package org.usfirst.frc.team2478.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc.team2478.robot.RobotMap;
//import org.usfirst.frc.team2478.robot.commands.NormalDrive;
import org.usfirst.frc.team2478.robot.commands.JoystickTeleop;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class DrivetrainSubsystem extends Subsystem {

	private WPI_TalonSRX m_leftFront, m_leftBack, m_rightFront, m_rightBack;
	private SpeedControllerGroup m_leftGroup, m_rightGroup;
	private DifferentialDrive m_differentialDrive;

	public DrivetrainSubsystem() {
	}
	
	/**
	* Initializes Talon and drivetrain system; run before calling drive functions.
	* @return true if successful, false if failed
	*/
	public void init() {
		m_leftFront = new WPI_TalonSRX(RobotMap.LEFT_FRONT);
		m_leftBack = new WPI_TalonSRX(RobotMap.LEFT_BACK);
		m_leftGroup = new SpeedControllerGroup(m_leftFront, m_leftBack);
		
		m_rightFront = new WPI_TalonSRX(RobotMap.RIGHT_FRONT);
		m_rightBack = new WPI_TalonSRX(RobotMap.RIGHT_BACK);
		m_rightGroup = new SpeedControllerGroup(m_rightFront, m_rightBack);

		m_differentialDrive = new DifferentialDrive(m_leftGroup, m_rightGroup);
	}
	
	public void tankDrive(double leftSpeed, double rightSpeed) {
		m_differentialDrive.tankDrive(leftSpeed, rightSpeed);
	}
	
	public void arcadeDrive(double forwardSpeed, double turnSpeed) {
		m_differentialDrive.arcadeDrive(forwardSpeed, turnSpeed);
	}
	
	public void stopDrive() {
		m_differentialDrive.stopMotor();
		System.out.println("differentialDrive.stopMotor() is running");
	}

    public void initDefaultCommand() {
    	 setDefaultCommand(new JoystickTeleop());
    }
}