package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2478.robot.Constants;
import frc.team2478.robot.interfaces.DoubleEncoderInterface;
import frc.team2478.robot.interfaces.DoubleEncoderInterface.Side;
import frc.team2478.robot.interfaces.DriveInterface;
import frc.team2478.robot.interfaces.GyroscopeInterface;
import frc.team2478.robot.util.SynchronousPIDF;

/**
 * When run, the robot will drive straight at the provided distance,
 * using a PID loop to stay on-course.
 */
public class AutonomoDriveStraight extends Command {
	
	private DriveInterface drivetrain;
	private DoubleEncoderInterface encoders;
	private GyroscopeInterface gyro;
	
	private double distanceTarget, leftCount, rightCount;
	private double avgDistance, angleOutput, distanceOutput;
	private boolean stopsAtSetpoint = true; // @debug

	private SynchronousPIDF pidAngle, pidDistance;
	private Timer timer;
	
	/**
	 * Create a new instance of {@link AutonomoDriveStraight}.
	 * @param distance How many encoder clicks to travel.
	 */
	public AutonomoDriveStraight(DriveInterface drivetrain, DoubleEncoderInterface encoders, GyroscopeInterface gyro,
								 double distance) {
		requires((Subsystem) drivetrain);
		requires((Subsystem) encoders);
		requires((Subsystem) gyro);
		this.drivetrain = drivetrain;
		this.encoders = encoders;
		this.gyro = gyro;
		
		distanceTarget = distance;

		pidAngle = new SynchronousPIDF( // default vals
			Constants.ClosedLoop.TURNING_P,
			Constants.ClosedLoop.COURSECORRECTION_I,
			Constants.ClosedLoop.TURNING_D);
		pidDistance = new SynchronousPIDF(
			Constants.ClosedLoop.DISTANCE_P,
			Constants.ClosedLoop.DISTANCE_I,
			Constants.ClosedLoop.DISTANCE_D);

		timer = new Timer();
	}
	
	/**
	 * Set the internal angular PID constants to new values
	 * @param p  P gain
	 * @param i  I gain
	 * @param d  D gain
	 */
	public void setAngularPid(double p, double i, double d) {
		pidAngle.setPID(p, i, d);
	}
	
	/**
	 * Set the internal distance PID constants to new values
	 * @param p  P gain
	 * @param i  I gain
	 * @param d  D gain
	 */
	public void setDistancePid(double p, double i, double d) {
		pidDistance.setPID(p, i, d);
	}
	
	/**
	 * Sets whether Command ends itself after reaching the setpoint.
	 * <p>Used for debugging, if you want to tune a PID loop.
	 * @param stops  True if you want command to end; false if not
	 */
	public void willStopAtSetpoint(boolean stops) {
		stopsAtSetpoint = stops;
	}

	protected void initialize() {
		encoders.resetEncoders();
		pidDistance.reset();
		pidDistance.setSetpoint(distanceTarget); // needs an iZone
		pidAngle.reset();
		pidAngle.setSetpoint(0);
		timer.reset();
		timer.start();
	}
	
	protected void execute() {
		leftCount = encoders.getEncoderTicks(Side.LEFT);
		rightCount = encoders.getEncoderTicks(Side.RIGHT);
		encoders.printEncoderData();
		
		avgDistance = (leftCount + rightCount) / 2;
		angleOutput = pidAngle.calculate(gyro.getAngle(), timer.get());
		distanceOutput = pidDistance.calculate(avgDistance, timer.get());
		
		drivetrain.arcadeDriveRaw(distanceOutput, angleOutput);
	}

	protected boolean isFinished() {
		if (pidDistance.onTarget(0.5) && stopsAtSetpoint) {
			return true;
		} else {
			return false;
		}
	}
	
	protected void end() {
		drivetrain.stopDrive();
		encoders.resetEncoders();
		timer.stop();
		pidDistance.reset();
		pidAngle.reset();
	}
}