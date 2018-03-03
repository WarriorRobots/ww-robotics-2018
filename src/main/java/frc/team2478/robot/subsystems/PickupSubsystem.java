package frc.team2478.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.team2478.robot.Constants;
import frc.team2478.robot.commands.scoring.pickup.RunPickupWithJoystick;
import frc.team2478.robot.interfaces.TandemMotorInterface;

/**
 * Components that take the cube from the ground and load it into the robot.
 */
public class PickupSubsystem extends Subsystem implements TandemMotorInterface {

	private static final int SLAVE_MOTOR = 7;
	private static final int MASTER_MOTOR = 8;

	private WPI_TalonSRX masterMotor, slaveMotor;
	
	public PickupSubsystem() {
		masterMotor = new WPI_TalonSRX(MASTER_MOTOR);
		slaveMotor = new WPI_TalonSRX(SLAVE_MOTOR);
		masterMotor.setInverted(Constants.Inversions.PICKUP_MASTER_REVERSED);
		slaveMotor.setInverted(Constants.Inversions.PICKUP_SLAVE_REVERSED);
		slaveMotor.follow(masterMotor);
	}
	
	@Override
	public void runAtPercentage(double percent) {
		masterMotor.set(ControlMode.PercentOutput, percent);
	}
	
	@Override
	public void stop() {
		masterMotor.stopMotor();
	}

	@Override
	public void initSendable(SendableBuilder builder) {
		builder.setSmartDashboardType("subsystem-pickup");
//		builder.addDoubleArrayProperty("currentdraw", () -> {
//			double[] currentDraw = new double[2];
//			currentDraw[0] = masterMotor.getOutputCurrent();
//			currentDraw[1] = slaveMotor.getOutputCurrent();
//			return currentDraw;
//		}, null);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new RunPickupWithJoystick());
	}
}
