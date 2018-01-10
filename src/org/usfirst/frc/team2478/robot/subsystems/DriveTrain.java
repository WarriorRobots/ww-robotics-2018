package org.usfirst.frc.team2478.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import org.usfirst.frc.team2478.robot.RobotMap;
import org.usfirst.frc.team2478.robot.commands.DriveWithJoysticks;
import com.ctre.phoenix.motorcontrol.can.*;

/**
 *
 */
public class DriveTrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public static WPI_TalonSRX leftFrontMotor = new WPI_TalonSRX(RobotMap.leftFront);
	public static WPI_TalonSRX leftBackMotor = new WPI_TalonSRX(RobotMap.leftBack);
	public static SpeedControllerGroup m_left = new SpeedControllerGroup(leftFrontMotor, leftBackMotor);
	
	public static WPI_TalonSRX rightFrontMotor = new WPI_TalonSRX(RobotMap.rightFront);
	public static WPI_TalonSRX rightBackMotor = new WPI_TalonSRX(RobotMap.rightBack);
	public static SpeedControllerGroup m_right = new SpeedControllerGroup(rightFrontMotor, rightBackMotor);
	
	public static DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    	setDefaultCommand(new DriveWithJoysticks());
    }
    
    public void leftForward() {
    	m_left.set(1);
    }

    public void leftBackward() {
    	m_left.set(-1);
    }

    public void leftStop() {
    	m_left.set(0);
    }
    
    public void rightForward() {
    	m_right.set(1);
    }

    public void rightBackward() {
    	m_right.set(-1);
    }

    public void rightStop() {
    	m_right.set(0);
    }
}

