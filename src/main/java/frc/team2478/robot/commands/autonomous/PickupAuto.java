package frc.team2478.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2478.robot.Constants;
import frc.team2478.robot.Robot;

/**
 * Run the pickup wheels, terminating when the cube light sensor is triggered.
 * @author westwood
 *
 */
public class PickupAuto extends Command {

//	private int count = 0;
//	private boolean toggle = false;
	
	public PickupAuto() {
		requires(Robot.pickup);
		requires(Robot.feed);
		requires(Robot.shooter);
	}
	
	@Override
	protected void execute() {
//		count++;
//		if (count % 10 == 0) {
//			toggle = !toggle;
//		}
//		if (count % 10 == 0) {
//		} else if (count % 25 == 0) {
//			Robot.pickup.runAtPercentage(-Constants.ShooterRig.PICKUP_PERCENT_SPEED);
//		} else {
//			Robot.pickup.stop();
//		}
		
			Robot.pickup.runAtPercentage(Constants.ShooterRig.PICKUP_PERCENT_SPEED);
		
		Robot.feed.runAtPercentage(Constants.ShooterRig.FEED_PERCENT_SPEED);
		Robot.shooter.runAtPercentage(-0.1);
	}
	
	@Override
	protected void interrupted() {
		this.end();
	}
	
	@Override
	protected void end() {
		Robot.pickup.stop();
		Robot.feed.stop();
		Robot.shooter.stop();
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.feed.isCubeLoaded();
	}

}
