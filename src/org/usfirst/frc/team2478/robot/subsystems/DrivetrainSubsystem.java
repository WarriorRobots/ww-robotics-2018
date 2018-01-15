package org.usfirst.frc.team2478.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc.team2478.robot.RobotMap;
import org.usfirst.frc.team2478.robot.commands.NormalDrive;

import com.ctre.phoenix.motorcontrol.can.*;

public class DrivetrainSubsystem extends Subsystem {

	public WPI_TalonSRX leftFront = new WPI_TalonSRX(RobotMap.LEFT_FRONT);
	public WPI_TalonSRX leftBack = new WPI_TalonSRX(RobotMap.LEFT_BACK);
	public SpeedControllerGroup leftGroup = new SpeedControllerGroup(leftFront, leftBack);
	
	public WPI_TalonSRX rightFront = new WPI_TalonSRX(RobotMap.RIGHT_FRONT);
	public WPI_TalonSRX rightBack = new WPI_TalonSRX(RobotMap.RIGHT_BACK);
	public SpeedControllerGroup rightGroup = new SpeedControllerGroup(rightFront, rightBack);
	
	public DifferentialDrive differentialDrive = new DifferentialDrive(leftGroup, rightGroup);

    public void initDefaultCommand() {
    	setDefaultCommand(new NormalDrive());
    }
}