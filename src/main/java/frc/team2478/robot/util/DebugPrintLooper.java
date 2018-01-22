package frc.team2478.robot.util;

/**
 * Allows access to methods that reduce the amount of printing every loop.
 * <p>Use this class to avoid having the RoboRIO network overloaded.
 * @author Alex Vuong
 */
public class DebugPrintLooper {
	
	private int m_loops;
	
	/**
	 * Implementation of System.out.println that saves memory by running every 10 loops.
	 * @param text String to be printed.
	 */
	public void println(String text) {
		this.println(text, 10);
	}
	
	/**
	 * Implementation of System.out.println that saves memory by running every specified number of loops.
	 * @param text String to be printed.
	 * @param loopCount How many loops to be waited between each console print
	 */
	public void println(String text, double loopCount) {
		if (m_loops >= loopCount) {
			m_loops = 0;
			System.out.println(text);
		} else {
			m_loops++;
		}
	}
	
}
