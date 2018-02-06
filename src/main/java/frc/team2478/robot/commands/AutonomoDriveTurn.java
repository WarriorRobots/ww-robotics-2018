package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2478.robot.Constants;
import frc.team2478.robot.interfaces.DoubleEncoderInterface;
import frc.team2478.robot.interfaces.DriveInterface;
import frc.team2478.robot.interfaces.GyroscopeInterface;
import frc.team2478.robot.util.SynchronousPIDF;

/**
 * When run, the robot will turn to the provided angle,
 * using a PID loop to maintain accuracy and control.
 */
public class AutonomoDriveTurn extends Command {
	
	private DriveInterface drivetrain;
	private DoubleEncoderInterface encoders;
	private GyroscopeInterface gyro;
	
	private double angleTarget, output;
	private boolean stopsAtSetpoint = true; // @debug variable
	private SynchronousPIDF pidLoop;
	private Timer timer;
	
	/**
	 * Create a new instance of {@link AutonomoDriveStraight}.
	 * @param angle  What angle in degrees to turn towards.
	 */
	public AutonomoDriveTurn(DriveInterface drivetrain, DoubleEncoderInterface encoders, GyroscopeInterface gyro,
							 double angle) {
		this.drivetrain = drivetrain;
		this.encoders = encoders;
		this.gyro = gyro;
		requires((Subsystem) drivetrain);
		requires((Subsystem) encoders);
		requires((Subsystem) gyro);
		
		angleTarget = angle;
		
		pidLoop = new SynchronousPIDF(
			Constants.ClosedLoop.TURNING_P,
			Constants.ClosedLoop.TURNING_I,
			Constants.ClosedLoop.TURNING_D);
		
		timer = new Timer();
	}

	/**
	 * Set the internal PID constants to new values
	 * @param p  P gain
	 * @param i  I gain
	 * @param d  D gain
	 */
	public void setPID(double p, double i, double d) {
		pidLoop.setPID(p, i, d);
	}
	
	/**
	 * Sets whether Command ends itself after reaching the setpoint
	 * @param stops  True if you want command to end; false if not
	 */
	public void willStopAtSetpoint(boolean stops) {
		stopsAtSetpoint = stops;
	}

	protected void initialize() {
		encoders.resetEncoders();
		pidLoop.reset();
		pidLoop.setSetpoint(angleTarget);
		timer.reset();
		timer.start();
	}
	
	protected void execute() {
		encoders.printEncoderData();
		output = pidLoop.calculate(gyro.getAngle(), timer.get());
		drivetrain.arcadeDriveRaw(0, output);
	}

	protected boolean isFinished() {
		if (pidLoop.onTarget(0.5) && stopsAtSetpoint) {
			return true;
		} else {
			return false;
		}
	}
	
	protected void end() {
		encoders.resetEncoders();
		timer.stop();
		pidLoop.reset();
		drivetrain.stopDrive();
	}
}