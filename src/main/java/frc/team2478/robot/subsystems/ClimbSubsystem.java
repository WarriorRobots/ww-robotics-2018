package frc.team2478.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

/**
 * Instantiates the climb subsystem, including motors and any pneumatics,
 * and provides methods for using them.
 */
public class ClimbSubsystem extends Subsystem {

	private WPI_TalonSRX masterMotor, slaveMotor;
	private final int MASTER_MOTOR = 13;
	private final int SLAVE_MOTOR = 14; // may need to be removed
	
	public ClimbSubsystem() {
		masterMotor = new WPI_TalonSRX(MASTER_MOTOR);
		slaveMotor = new WPI_TalonSRX(SLAVE_MOTOR);
		
		slaveMotor.follow(masterMotor);
	}
	
	public void runMotorAtPercentage(double percent) {
		masterMotor.set(ControlMode.PercentOutput, percent);
	}
	
	public void stop() {
		masterMotor.stopMotor();
	}
	
	@Override
	protected void initDefaultCommand() {}
	
	@Override
	public void initSendable(SendableBuilder builder) {
		builder.setSmartDashboardType("subsystem-climb");
		builder.addDoubleArrayProperty("currentdraw", () -> {
			double[] currentDraw = new double[2];
			currentDraw[0] = masterMotor.getOutputCurrent();
			currentDraw[1] = slaveMotor.getOutputCurrent();
			return currentDraw;
		}, null);
	}
}