package frc.team2478.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

/**
 * Instantiates intake motors, and provides methods for using them.
 */
public class IntakeSubsystem extends Subsystem {

	private final int SLAVE_MOTOR = 7;
	private final int MASTER_MOTOR = 8;

	private WPI_TalonSRX masterMotor, slaveMotor;
	private DigitalInput infaredSensor;
	
	public IntakeSubsystem() {
		masterMotor = new WPI_TalonSRX(MASTER_MOTOR);
		slaveMotor = new WPI_TalonSRX(SLAVE_MOTOR);
		
		masterMotor.setInverted(true);
		//slaveMotor.setInverted(true);
		slaveMotor.follow(masterMotor);
		infaredSensor = new DigitalInput(9);
	}
	
	public void setTargetPercentage(double percent) {
		masterMotor.set(ControlMode.PercentOutput, percent);
	}
	
	public void stop() {
		masterMotor.stopMotor();
	}

	/**
	 * @return Whether the cube is loaded within the feed.
	 */
	public boolean isCubeLoaded() {
		// the infared sensor returns whether the cube is not present
		return !infaredSensor.get();
	}

	// builder.addBooleanProperty("Cube Loaded?", () -> isCubeLoaded(), null);
	
	/**
	 * Dashboard setup for intake.
	 * @author Josh (borrowed from Alex)
	 */
	@Override
	public void initSendable(SendableBuilder builder) {
		builder.setSmartDashboardType("subsystem-intake");
		builder.addDoubleArrayProperty("currentdraw", () -> {
			double[] currentDraw = new double[2];
			currentDraw[0] = masterMotor.getOutputCurrent();
			currentDraw[1] = slaveMotor.getOutputCurrent();
			return currentDraw;
		}, null);
	}
	
	@Override
	protected void initDefaultCommand() {}
}
