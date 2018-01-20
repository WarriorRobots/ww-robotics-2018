//package org.usfirst.frc.team2478.robot.unused;
//
//import org.usfirst.frc.team2478.robot.Robot;
//import org.usfirst.frc.team2478.robot.RobotMap;
//
//import edu.wpi.first.wpilibj.command.PIDSubsystem;
//
//public class PidTurningSubsystem extends PIDSubsystem {
//	
//	public double output;
//	public PidTurningSubsystem() {
//		super(RobotMap.ANGULAR_P, RobotMap.ANGULAR_I, RobotMap.ANGULAR_D); //removed String name from constructor
//		setAbsoluteTolerance(RobotMap.ANGULAR_TOLERANCE);
////		getPIDController().setContinuous(false); // false by default
//		setSetpoint(RobotMap.ANGULAR_SETPOINT);
//	}
//
//	@Override
//	protected double returnPIDInput() {
//		return RobotmotionSensors.navx.getAngle();
//	}
//
//	@Override
//	protected void usePIDOutput(double output) {
//		this.output = output;
//		Robot.drivetrain.differentialDrive.arcadeDrive(0, output);
//	}
//
//	@Override
//	protected void initDefaultCommand() {}
//	
//	// don't bypass the original methods until they work properly by themselves!!
////	public double get() {
////		return getPIDController().get();
////	}
////	
////	public boolean getEnable() {
////		return getPIDController().isEnabled();
////	}
//}
