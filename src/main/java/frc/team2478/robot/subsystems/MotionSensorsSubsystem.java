package frc.team2478.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Instantiates motion-related sensors and provides methods for accessing their data.
 */
public final class MotionSensorsSubsystem extends Subsystem {

	private AHRS m_navx;
	private Encoder m_leftEnc, m_rightEnc;

	public MotionSensorsSubsystem() {
		m_leftEnc = new Encoder(2,3); //remove magic numbers
		m_rightEnc = new Encoder(0,1);
		
		try {
			m_navx = new AHRS(I2C.Port.kMXP);
		} catch (RuntimeException ex) {
			DriverStation.reportError("Error instantiating navX MXP: ", true);
		}
	}

	/**
	 * Gets current angle of NavX, which will count past 360 and -360.
	 * @return Current angle in degrees.
	 */
	public double getNavxAngle() {
		return m_navx.getAngle();
	}
	
	/**
	 * Resets all sensors in the NavX.
	 */
	public void resetNavx() {
		m_navx.reset();
	}

	/**
	 * Gets current count of the left encoder.
	 * @return Current count of left encoder, in clicks. <b>TO-DO: Calculate clicks per rotation.</b>
	 */
	public double getLeftEncCount() {
		return m_leftEnc.get();
	}
	
	/**
	 * Gets current count of the right encoder.
	 * @return Current count of right encoder, in clicks.
	 */
	public double getRightEncCount() {
		return -m_rightEnc.get();
	}
	
	/**
	 * Resets count of both encoders.
	 */
	public void resetEncoders() {
		m_leftEnc.reset();
		m_rightEnc.reset();
	}
	
	/**
	 * Resets NavX and encoder values: combination of resetNavx() and resetEncoders().
	 */
	public void resetAllSensors() {
		this.resetNavx();
		this.resetEncoders();
	}
	
	/**
	 * Sends all information into a string.
	 * @return String containing debug information (send to println)
	 */
	public String debugAllSensors() {
		return "LEFT: " + Double.toString(getLeftEncCount()) +
			  " RIGHT: " + Double.toString(getRightEncCount()) +
			  " ANGLE: " + Double.toString(getNavxAngle());
	}
	
	@Override
    public void initDefaultCommand() {}
}