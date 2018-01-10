package org.usfirst.frc.team2478.robot.subsystems;

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
	WPI_TalonSRX leftFrontMotor = new WPI_TalonSRX(RobotMap.leftFront);
	WPI_TalonSRX leftBackSlave = new WPI_TalonSRX(RobotMap.leftBack);
	WPI_TalonSRX rightFrontMotor = new WPI_TalonSRX(RobotMap.rightFront);
	WPI_TalonSRX rightBackSlave = new WPI_TalonSRX(RobotMap.rightBack);
	
	DifferentialDrive Drive = new DifferentialDrive(leftFrontMotor, rightFrontMotor);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    	setDefaultCommand(new DriveWithJoysticks());
    }
    
    public void leftForward() {
    	leftFrontMotor.set(1);
    	leftBackSlave.follow(leftFrontMotor);
    }

    public void leftBackward() {
    	leftFrontMotor.set(-1);
    	leftBackSlave.follow(leftFrontMotor);
    }

    public void leftStop() {
    	leftFrontMotor.set(0);
    	leftBackSlave.follow(leftFrontMotor);
    }
    
    public void rightForward() {
    	rightFrontMotor.set(1);
    	rightBackSlave.follow(rightFrontMotor);
    }

    public void rightBackward() {
    	rightFrontMotor.set(-1);
    	rightBackSlave.follow(rightFrontMotor);
    }

    public void rightStop() {
    	rightFrontMotor.set(0);
    	rightBackSlave.follow(rightFrontMotor);
    }
}

