package frc.team2478.robot.util;

import frc.team2478.robot.Robot;

public class ShooterFeedHandler {
	public static int count = 0;
	
	public ShooterFeedHandler() {}
	
	public void periodic() {
		//shooter.setPID(0.04, 0, 0);
		double triggerVal = Robot.oi.getXboxRightTrigger();
		if (triggerVal > 0.7) {
			count+=1;
			Robot.shooter.setTargetPercentage(-triggerVal);
			if(count>50)
				Robot.feed.setTargetPercentage(triggerVal);
		}
		else {
			count=0;
			if (Math.abs(Robot.oi.getXboxLeftY()) > 0.3)
//				shooter.setTargetVelocity(-oi.getXboxLeftY(10000)); // so full stick would get 10000, an arbatrary value that worked on the old shooter setup
				Robot.shooter.setTargetPercentage(Robot.oi.getXboxLeftY());
			else Robot.shooter.stop();
			if (Math.abs(Robot.oi.getXboxRightY()) > 0.3)
				Robot.feed.setTargetPercentage(-Robot.oi.getXboxRightY());
			else Robot.feed.stop();
		}
		
		new DebugPrintLooper().println(Double.toString(Robot.oi.getXboxLeftY()));
		new DebugPrintLooper().println(Double.toString(Robot.oi.getXboxRightY()));
	}

}
