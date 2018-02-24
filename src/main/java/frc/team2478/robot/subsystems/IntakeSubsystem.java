package frc.team2478.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.team2478.robot.interfaces.MotorInterface;

/**
 * Instantiates intake motors, and provides methods for using them.
 */
public class IntakeSubsystem extends Subsystem implements MotorInterface {

	private final int MASTER_MOTOR = 9;
	private final int SLAVE_MOTOR = 10;

	private WPI_TalonSRX masterMotor, slaveMotor;
	
	public IntakeSubsystem() {
		masterMotor = new WPI_TalonSRX(MASTER_MOTOR);
		slaveMotor = new WPI_TalonSRX(SLAVE_MOTOR);
		
		//slaveMotor.setInverted(true);
		slaveMotor.follow(masterMotor);
	}
	
	@Override
	public void setTargetPercentage(double percent) {
		masterMotor.set(ControlMode.PercentOutput, percent);
	}
	
	@Override
	public void stop() {
		masterMotor.stopMotor();
	}
	
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
