package frc.team2478.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.team2478.robot.Constants;
import frc.team2478.robot.Robot;
import frc.team2478.robot.commands.autonomous.TurnAuto;
import frc.team2478.robot.commands.autonomous.untested.CameraAuto;
import frc.team2478.robot.commands.autonomous.untested.DriveAutoNoResetDist;
import frc.team2478.robot.commands.autonomous.untested.PickupAuto;
import frc.team2478.robot.commands.autonomous.untested.RackCubeAuto;
import frc.team2478.robot.commands.autonomous.untested.TurnAutoNoReset;
import frc.team2478.robot.commands.drive.StopDrive;
import frc.team2478.robot.commands.pneumatics.ClosePickup;
import frc.team2478.robot.commands.pneumatics.LowerHood;
import frc.team2478.robot.commands.pneumatics.OpenPickup;
import frc.team2478.robot.commands.pneumatics.RaiseHood;
import frc.team2478.robot.commands.scoring.StopAllScoringMotors;
import frc.team2478.robot.commands.scoring.feed.RunFeedAtDefault;
import frc.team2478.robot.commands.scoring.shooter.RunShooterAtVelocity;

public class TestCase extends CommandGroup {

	public TestCase() {
		
		addSequential(new RighttoRightScale());
		addParallel(new OpenPickup());
		addParallel(new PickupAuto());
		addSequential(new CameraAuto());
		addParallel(new StopAllScoringMotors());
		addParallel(new StopDrive());
		addParallel(new ClosePickup());
		addParallel(new RaiseHood());
		addParallel(new TurnAutoNoReset(0));
		addSequential(new WaitCommand(1));
		addSequential(new RackCubeAuto());
		addParallel(new StopAllScoringMotors());
		addSequential(new DriveAutoNoResetDist());
		addParallel(new RunShooterAtVelocity(Constants.ShooterRig.AUTO_SPEED)); // rev shooter early, to reduce wasted time
		addSequential(new TurnAuto(-110));
		addParallel(new RunFeedAtDefault()); // rev feed to launch cube
		addSequential(new WaitCommand(1.5)); // wait a short time to ensure cube has exited robot
		addParallel(new LowerHood()); // lower hood to allow safe turning
		addSequential(new StopAllScoringMotors()); // stop the shooter to prevent power drain
	}
	
	@Override
	protected void end() {
		Robot.drivetrain.resetAngle();
		Robot.drivetrain.stopDrive();
		Robot.shooter.stop();
	}
	
}