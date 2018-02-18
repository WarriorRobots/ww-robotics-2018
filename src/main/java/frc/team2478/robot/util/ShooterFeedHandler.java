package frc.team2478.robot.util;

import frc.team2478.robot.Robot;

/**
 * Handler for runnng the shooter and feed
 * @author Jarexe
 *
 */
public class ShooterFeedHandler {
	/**
	 * Used as a delay between starting to rev the shooter and running the feed.
	 */
	public static int count = 0;
	
	/**
	 * Makes a thing.
	 */
	public ShooterFeedHandler() {}
	
	/**
	 * Call within {@code Robot.teleopPeriodic()}
	 * <p> Right stick on the xbox controller controls feed.
	 * Left stick on the xbox controller controls shooter.
	 * Right trigger makes the shooter rev up and then does feed.
	 * (COMMENTED OUT: Also debug prints to the console.)
	 * @param none
	 */
	public void periodic() {
		//vshooter.setPID(0.04, 0, 0);
		double triggerVal = Robot.oi.getXboxRightTrigger();
		if (triggerVal > 0.7) {
			//Rev up shooter and then shoot on 50th loop
			count+=1;
			Robot.shooter.setTargetPercentage(-triggerVal); // takes opposite because the shooter runs backwards
			if(count>50)
				Robot.feed.setTargetPercentage(triggerVal);
		}
		else {
			// restart rev up
			count=0;
			
			//NOTE: the opposite signs are reversed here because the xbox sticks go negative as you push forward and negative as you pull backwards
			
			// control shooter
			if (Math.abs(Robot.oi.getXboxLeftY()) > 0.3)
//				shooter.setTargetVelocity(-oi.getXboxLeftY(10000)); // so full stick would get 10000, an arbatrary value that worked on the old shooter setup
				Robot.shooter.setTargetPercentage(Robot.oi.getXboxLeftY());
			else Robot.shooter.stop();
			
			// control feed
			if (Math.abs(Robot.oi.getXboxRightY()) > 0.3)
				Robot.feed.setTargetPercentage(-Robot.oi.getXboxRightY());
			else Robot.feed.stop();
		}
		
//		new DebugPrintLooper().println(Double.toString(Robot.oi.getXboxLeftY()));
//		new DebugPrintLooper().println(Double.toString(Robot.oi.getXboxRightY()));
	}

}
