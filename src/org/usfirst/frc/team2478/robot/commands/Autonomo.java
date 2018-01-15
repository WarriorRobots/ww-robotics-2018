package org.usfirst.frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2478.robot.Robot;

public class Autonomo extends Command {
    public int states;
    Encoder leftEnc;
    Encoder rightEnc;
    
	
    //!!!!!!!!!!!!!!!!PUT IN ENCODER PORTS BEFORE RUNNING!!!!!!!!!!!!!!!!!!!!!!!!!!!11
    public Autonomo() {
    	requires(Robot.drivetrain);
    	
        leftEnc = new Encoder(2, 3);
        //(port number, port number, invert counting direction?, encoder accuracy[1x, 2x, or 4x])
        
        rightEnc = new Encoder(0, 1);
        
        // Encoder armEnc;
        // armEnc = new Encoder(0, 1, false, Encoder.EncodingType.k4X); 
        
        // /*requires(Robot.kLift);*/

        states = 0;
    }

    protected void initialize() {
    }

    public void autoLine(double distToAutoLine) {
        //MOVE FORWARD
        Robot.drivetrain.differentialDrive.tankDrive(0.2, 0.2);

        if(leftEnc.getDistance() >= distToAutoLine && leftEnc.getDistance() >= distToAutoLine) {
            System.out.println("acomplished!");
        }
    }

    public void switchStraight(double distToAutoLine, double heightToSwitch, double shootTime) {
        //Timer shootTimer = new Timer();
        
        switch(states){
            case 0:
                //MOVE FORWARD   
                Robot.drivetrain.differentialDrive.tankDrive(0.2, 0.2);
                if(leftEnc.getDistance() >= distToAutoLine && rightEnc.getDistance() >= distToAutoLine){
                //STOP MOVEMENT
                    Robot.drivetrain.differentialDrive.tankDrive(0, 0);
                    states++;
                }
            case 1: /* LIFT CUBE OUT OF STORAGE
                Arm.lift();
                if(armEnc.getDistance() > heightToSwitch) {
                    states++;
                }
                */
            case 2: /* //SHOOT
                shootTimer.start();
                Shooter.shoot();
                if(shootTimer.get() > shootTime) {
                    Shooter.stop();
                    shootTimer.stop();
                    Arm.dock();

                    states++;
                }

                */
            default: 
                break;



        }


    }

    public void scaleRight(double distPastSwitch,double distTurnLeft,double shootTime) {

        switch(states){
            case 0:  //MOVE FORWARD 
                Robot.drivetrain.differentialDrive.tankDrive(0.2, 0.2);
                if(leftEnc.getDistance() >= distPastSwitch&& rightEnc.getDistance() >= distPastSwitch){
                states++;
                }
                System.out.println(states);
                break;
            case 1:   //TURN LEFT
                Robot.drivetrain.differentialDrive.tankDrive(0.0, 0.2);
                if(rightEnc.getDistance() >= distTurnLeft){
                	//STOP MOVEMENT
                    Robot.drivetrain.differentialDrive.tankDrive(0.0, 0.0);
                    /*****add code to check if motor output is equal to zero and THEN increase state
                    *********/
                    states++;
                }
                break;
            case 2:  /*LIFT CUBE OUT OF STORAGE
                Arm.lift();
                if(armEnc.getDistance() > heightToScale) {
                    states++;
                }
                 */
                break;
            case 3: /* //SHOOT
                shootTimer.start();
                Shooter.Shoot();
                if(shootTimer.get() > shootTime) {
                    shootTimer.stop();
                    Arm.dock();
                    states++;
                }
                
                 */
                break;
            case 4: 
                System.out.println("Scale Right Acomplished!");
                break;
        }

    }

    public void scaleLeft(double distPastSwitch,double distTurnRight,double shootTime) {

        switch(states){
            case 0:  //MOVE FORWARD 
                Robot.drivetrain.differentialDrive.tankDrive(0.2, 0.2);
                if(leftEnc.getDistance() >= distPastSwitch && rightEnc.getDistance() > distPastSwitch){
                states++;
                }
                System.out.println(states);
                break;
            case 1:   //TURN RIGHT
                Robot.drivetrain.differentialDrive.tankDrive(0.2, 0.0);
                if(leftEnc.getDistance() > distTurnRight){
                    Robot.drivetrain.differentialDrive.tankDrive(0.0, 0.0);
                    /*****add code to check if motor output is equal to zero and THEN increase state
                    *********/
                    states++;
                    System.out.println(states);
                }
                break;
            case 2:  /*LIFT CUBE OUT OF STORAGE
                Arm.lift();
                if(armEnc.getDistance() > heightToScale) {
                    states++;
                }
                 */
                break;
            case 3: /* //SHOOT
                shootTimer.start();
                Shooter.Shoot();
                if(shootTimer.get() > shootTime) {
                    shootTimer.stop();
                    Arm.dock();
                    states++;
                }
                
                 */
                break;
            case 4: 
                System.out.println("Scale Left Acomplished!");
                break;
        }

    }    

    public void sit(){
        //DO NOT MOVE
        Robot.drivetrain.differentialDrive.tankDrive(0.0, 0.0);
    }
    protected boolean isFinished() { // needs a stop condition!
        return false;
    }

    protected void end() {
    }

    protected void interrupted() { // will activate when AlignmentMode or LockMode is run
    }
}
