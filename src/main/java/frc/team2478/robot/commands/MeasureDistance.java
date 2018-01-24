package frc.team2478.robot.commands;

import frc.team2478.robot.util.DebugPrintLooper;

public class MeasureDistance extends CommandBase {
	
	DebugPrintLooper m_printLooper;
	
	public MeasureDistance() {
		requires(motionSensors);
		m_printLooper = new DebugPrintLooper();
	}

	@Override
	protected void initialize() {
		motionSensors.resetAllSensors();
	}
	
	@Override
	protected void execute() {
		m_printLooper.println(Double.toString(motionSensors.getLeftEncCount())
							  + " " + Double.toString(motionSensors.getRightEncCount()), 4);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
	
}
