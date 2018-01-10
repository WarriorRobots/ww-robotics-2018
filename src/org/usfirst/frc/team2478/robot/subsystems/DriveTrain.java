package org.usfirst.frc.team2478.robot.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2478.robot.RobotMap;

/**
 *
 */
public class DriveTrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Talon leftFront = RobotMap.leftFront;
	Talon leftBack = RobotMap.leftBack;
	Talon rightFront = RobotMap.rightFront;
	Talon rightBack = RobotMap.rightBack;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void leftForward() {
    	leftFront.set(1);
    	leftBack.set(1);
    }

    public void leftBackward() {
    	leftFront.set(-1);
    	leftBack.set(-1);
    }

    public void leftStop() {
    	leftFront.set(0);
    	leftBack.set(0);
    }
    
    public void rightForward() {
    	rightFront.set(1);
    	rightBack.set(1);
    }

    public void rightBackward() {
    	rightFront.set(-1);
    	rightBack.set(-1);
    }

    public void rightStop() {
    	rightFront.set(0);
    	rightBack.set(0);
    }
}

