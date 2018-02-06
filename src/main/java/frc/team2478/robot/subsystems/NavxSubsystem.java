package frc.team2478.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2478.robot.interfaces.AccelerometerInterface;
import frc.team2478.robot.interfaces.GyroscopeInterface;

/**
 * Instantiates motion-related sensors and provides methods for accessing their data.
 */
public class NavxSubsystem extends Subsystem implements GyroscopeInterface, AccelerometerInterface {

	private AHRS navx;

	public NavxSubsystem() {
		try {
			navx = new AHRS(I2C.Port.kMXP);
		} catch (RuntimeException ex) {
			DriverStation.reportError("Error instantiating navX MXP: ", true);
		}
	}
	
	@Override
    public void initDefaultCommand() {}

	@Override
	public double getAccelX() {
		return navx.getWorldLinearAccelX();
	}

	@Override
	public double getAccelY() {
		return navx.getWorldLinearAccelY();
	}

	@Override
	public double getAccelZ() {
		return navx.getWorldLinearAccelZ();
	}

	@Override
	public double getPitch() {
		return navx.getPitch();
	}

	@Override
	public double getRoll() {
		return navx.getRoll();
	}

	@Override
	public double getYaw() {
		return navx.getYaw();
	}

	@Override
	public void resetYaw() {
		navx.zeroYaw();
	}

	@Override
	public double getAngle() {
		return navx.getAngle();
	}

	@Override
	public void resetAngle() {
		navx.zeroYaw();
	}
}