package frc.team2478.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Constants;
import frc.team2478.robot.Robot;
import frc.team2478.robot.interfaces.DriveEncoderInterface.Side;
import frc.team2478.robot.util.SynchronousPIDF;
import frc.team2478.robot.util.annotations.Debug;

/**
 * When run, the robot will drive straight at the provided distance,
 * using a PID loop to stay on-course.
 */
public class AutonomoDriveStraight extends Command {
	
	private int distanceTarget, leftCount, rightCount, avgCount;
	private double angleOutput, distanceOutput;
	
	@Debug
	private boolean stopsAtSetpoint = true;

	private SynchronousPIDF pidAngle, pidDistance;
	private Timer timer;
	
	/**
	 * Create a new instance of {@link AutonomoDriveStraight}.
	 * @param distanceTarget How many encoder clicks to travel.
	 */
	public AutonomoDriveStraight(int distanceTarget) {
		requires(Robot.drivetrain);

		pidAngle = new SynchronousPIDF( // default vals
			Constants.ClosedLoop.TURNING_P,
			Constants.ClosedLoop.COURSECORRECTION_I,
			Constants.ClosedLoop.TURNING_D);
		pidDistance = new SynchronousPIDF(
			Constants.ClosedLoop.DISTANCE_P,
			Constants.ClosedLoop.DISTANCE_I,
			Constants.ClosedLoop.DISTANCE_D);

		this.distanceTarget = distanceTarget;
		System.out.println("distanceTarget " + Double.toString(this.distanceTarget) + " " + Double.toString(distanceTarget));
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

	@Override
	protected void initialize() {
		Robot.drivetrain.resetEncoders();
		Robot.drivetrain.resetAngle();
		pidAngle.reset();
		timer.start();
		pidDistance.setSetpoint(distanceTarget);
		pidDistance.setIzone(-0.15, 0.15);
		pidAngle.setSetpoint(0.0);
	}
	
	@Override
	protected void execute() {
		leftCount = Robot.drivetrain.getEncoderTicks(Side.LEFT);
		rightCount = Robot.drivetrain.getEncoderTicks(Side.RIGHT);
		Robot.drivetrain.printEncoderData();
		
		avgCount = (int) ((leftCount + rightCount) / 2);
		angleOutput = pidAngle.calculate(Robot.drivetrain.getAngle(), timer.get());
		distanceOutput = pidDistance.calculate(avgCount, timer.get());
		
		System.out.println(Double.toString(distanceOutput) + " " + Double.toString(angleOutput));
		System.out.println(Robot.drivetrain.getAngle());
		
		Robot.drivetrain.arcadeDriveRaw(-distanceOutput, angleOutput);
	}

	@Override
	protected boolean isFinished() {
		if (pidDistance.onTarget(Constants.ClosedLoop.DISTANCE_TOLERANCE) && stopsAtSetpoint) {
//		if (stopsAtSetpoint) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	protected void end() {
		System.out.println("STOP!!!");
		Robot.drivetrain.stopDrive();
		Robot.drivetrain.resetEncoders();
		timer.stop();
		pidDistance.reset();
		pidAngle.reset();
	}
}