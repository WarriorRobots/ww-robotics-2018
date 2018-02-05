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

	private AHRS m_navx;

	public NavxSubsystem() {
		try {
			m_navx = new AHRS(I2C.Port.kMXP);
		} catch (RuntimeException ex) {
			DriverStation.reportError("Error instantiating navX MXP: ", true);
		}
	}
	
	@Override
    public void initDefaultCommand() {}

	@Override
	public double getAccelX() {
		return m_navx.getWorldLinearAccelX();
	}

	@Override
	public double getAccelY() {
		return m_navx.getWorldLinearAccelY();
	}

	@Override
	public double getAccelZ() {
		return m_navx.getWorldLinearAccelZ();
	}

	@Override
	public double getPitch() {
		return m_navx.getPitch();
	}

	@Override
	public double getRoll() {
		return m_navx.getRoll();
	}

	@Override
	public double getYaw() {
		return m_navx.getYaw();
	}

	@Override
	public void resetYaw() {
		m_navx.zeroYaw();
	}

	@Override
	public double getAngle() {
		return m_navx.getAngle();
	}

	@Override
	public void resetAngle() {
		m_navx.zeroYaw();
	}
}