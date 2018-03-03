package frc.team2478.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.team2478.robot.Constants;
import frc.team2478.robot.commands.scoring.feed.RunFeedWithJoystick;
import frc.team2478.robot.interfaces.TandemMotorInterface;

/**
 * Components that receive the cube from the pickup mechanism and feed it into the shooter, and related sensors.
 */
public class FeedSubsystem extends Subsystem implements TandemMotorInterface {

	private static final int SLAVE_MOTOR = 9; // right
	private static final int MASTER_MOTOR = 10; // left
	private static final int INFARED_SENSOR_ID = 9;
	
	private WPI_TalonSRX masterMotor, slaveMotor;
	private DigitalInput infaredSensor;
	
	public FeedSubsystem() {
		masterMotor = new WPI_TalonSRX(MASTER_MOTOR);
		slaveMotor = new WPI_TalonSRX(SLAVE_MOTOR);
		masterMotor.setInverted(Constants.Inversions.FEED_MASTER_REVERSED);
		slaveMotor.setInverted(Constants.Inversions.FEED_SLAVE_REVERSED);
//		slaveMotor.follow(masterMotor); //**
		infaredSensor = new DigitalInput(INFARED_SENSOR_ID);
	}

	@Override
	public void runAtPercentage(double percent) {
		masterMotor.set(ControlMode.PercentOutput, percent);
		slaveMotor.set(ControlMode.PercentOutput, -percent);
	}

	@Override
	public void stop() {
		masterMotor.stopMotor();
	}
		
	/**
	 * Detects if a cube is loaded into the lower half of the robot.
	 * @return True if a cube is present, false otherwise.
	 */
	public boolean isCubeLoaded() {
		// the infared sensor returns whether the cube is not present
		return !infaredSensor.get();
	}
	
	@Override
	public void initSendable(SendableBuilder builder) {
		builder.setSmartDashboardType("subsystem-feed");
//		builder.addDoubleArrayProperty("currentdraw", () -> {
//			double[] currentDraw = new double[2];
//			currentDraw[0] = masterMotor.getOutputCurrent();
//			currentDraw[1] = slaveMotor.getOutputCurrent();
//			return currentDraw;
//		}, null);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new RunFeedWithJoystick());
	}
}
