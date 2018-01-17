package org.usfirst.frc.team2478.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc.team2478.robot.RobotMap;
//import org.usfirst.frc.team2478.robot.commands.NormalDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class DrivetrainSubsystem extends Subsystem {

	private WPI_TalonSRX m_leftFront, m_leftBack, m_rightFront, m_rightBack;
	private SpeedControllerGroup m_leftGroup, m_rightGroup;

	public DrivetrainSubsystem() {
		m_leftFront = new WPI_TalonSRX(RobotMap.LEFT_FRONT);
		m_leftBack = new WPI_TalonSRX(RobotMap.LEFT_BACK);
		m_leftGroup = new SpeedControllerGroup(m_leftFront, m_leftBack);
		
		m_rightFront = new WPI_TalonSRX(RobotMap.RIGHT_FRONT);
		m_rightBack = new WPI_TalonSRX(RobotMap.RIGHT_BACK);
		m_rightGroup = new SpeedControllerGroup(m_rightFront, m_rightBack);
	}
		
	public DifferentialDrive differentialDrive = new DifferentialDrive(m_leftGroup, m_rightGroup);

    public void initDefaultCommand() {
    	// setDefaultCommand(new NormalDrive());
    	// none (replace once we can whileHeld() properly)
    }
}