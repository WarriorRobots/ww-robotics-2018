package frc.team2478.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Constants;
import frc.team2478.robot.Robot;
import frc.team2478.robot.util.SynchronousPIDF;
import frc.team2478.robot.util.annotations.Debug;
import frc.team2478.robot.util.enums.RobotSide;

/**
 * When run, the robot will drive straight at the provided distance,
 * using a PID loop to stay on-course.
 */
public class DriveAuto extends Command {
	
	private int distanceTargetClicks, leftCount, rightCount, avgCount;
	private double angleOutput, distanceOutput;
	
	@Debug
	private boolean stopsAtSetpoint = true;

	private SynchronousPIDF pidAngle, pidDistance;
	private Timer timer;
	
	/**
	 * Create a new instance of {@link DriveAuto}.
	 * @param inches  How many inches to travel.
	 */
	public DriveAuto(double inches) {
		requires(Robot.drivetrain);

		pidAngle = new SynchronousPIDF( // default vals
			Constants.AutonomoDrive.COURSECORRECTION_P,
			Constants.AutonomoDrive.COURSECORRECTION_I,
			Constants.AutonomoDrive.COURSECORRECTION_D);
		pidDistance = new SynchronousPIDF(
			Constants.AutonomoDrive.DISTANCE_P,
			Constants.AutonomoDrive.DISTANCE_I,
			Constants.AutonomoDrive.DISTANCE_D);

		this.distanceTargetClicks = Constants.AutonomoDrive.InchesToClicks(inches);
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
	
	@Override
	protected void initialize() {
		Robot.drivetrain.resetEncoders();
		Robot.drivetrain.resetAngle();
		pidAngle.reset();
		timer.start();
		pidDistance.setSetpoint(distanceTargetClicks);
		pidDistance.setOutputRange(-0.75, 0.75);
		pidDistance.setIzone(-0.15, 0.15);
		pidAngle.setSetpoint(0.0);
	}
	
	@Override
	protected void execute() {
		leftCount = Robot.drivetrain.getEncoderTicks(RobotSide.LEFT);
		rightCount = Robot.drivetrain.getEncoderTicks(RobotSide.RIGHT);
		
		avgCount = (int) ((leftCount + rightCount) / 2);
		angleOutput = pidAngle.calculate(Robot.drivetrain.getAngle(), timer.get());
		distanceOutput = pidDistance.calculate(avgCount, timer.get());
		
		Robot.drivetrain.arcadeDriveRaw(-distanceOutput, angleOutput);
	}

	@Override
	protected boolean isFinished() {
		if (pidDistance.onTarget(Constants.AutonomoDrive.DISTANCE_TOLERANCE) && stopsAtSetpoint) {
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