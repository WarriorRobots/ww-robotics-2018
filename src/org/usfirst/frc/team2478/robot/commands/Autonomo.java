package org.usfirst.frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2478.robot.Robot;

public class Autonomo extends Command {
    public int states;
	
    //!!!!!!!!!!!!!!!!PUT IN ENCODER PORTS BEFORE RUNNING!!!!!!!!!!!!!!!!!!!!!!!!!!!11
    public Autonomo() {
    	requires(Robot.drivetrain);

        Encoder leftEnc;
        leftEnc = new Encoder(2, 3, false, Encoder.EncodingType.k4X);
        //(port number, port number, invert counting direction?, encoder accuracy[1x, 2x, or 4x])
        
        Encoder rightEnc;
        rightEnc = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
        
        // Encoder armEnc;
        // armEnc = new Encoder(0, 1, false, Encoder.EncodingType.k4X); 
        
        // /*requires(Robot.kLift);*/

        states = 0;
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.drivetrain.differentialDrive.tankDrive(Robot.oi.getLeftSpeed(), Robot.oi.getRightSpeed());
    }

    public void autoLine(float distToAutoLine) {
        //MOVE FORWARD
        Robot.drivetrain.differentialDrive.tankDrive(0.2, 0.2);

        if(leftEnc.getDistance() && leftEnc.getDistance() >= distToAutoLine) {
            println("acomplished!");
        }
    }

    public void switch(float distToAutoLine, float heightToSwitch, float shootTime) {
        shootTimer = new Timer();
        
        switch(states){
            case 0:
                //MOVE FORWARD   
                Robot.drivetrain.differentialDrive.tankDrive(0.2, 0.2);
                if(leftEnc.getDistance() && rightEnc.getDistance() >= distToAutoLine){
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

    public void scaleRight(float distPastSwitch,float distTurnLeft,float shootTime) {

        switch(states){
            case 0:  //MOVE FORWARD 
                Robot.drivetrain.differentialDrive.tankDrive(0.2, 0.2);
                if(leftEnc.getDistance() && rightEnc.getDistance() > distPastSwitch){
                states++;
                }
            println(states);
                break;
            case 1:   //TURN RIGHT
                Robot.drivetrain.differentialDrive.tankDrive(0.2, 0.0)
                if(lefEnc.getDistance() > distTurnRight){
                    states++;
                }
                break;
            case 2:  //STOP MOVEMENT
                Robot.drivetrain.differentialDrive.tankDrive(0.0, 0.0);
                /*****add code to check if motor output is equal to zero and THEN increase state
                *********/
                states++;
            case 3:  /*LIFT CUBE OUT OF STORAGE
                Arm.lift();
                if(armEnc.getDistance() > heightToScale) {
                    states++;
                }
                 */
                break;
            case 4: /* //SHOOT
                shootTimer.start();
                Shooter.Shoot();
                if(shootTimer.get() > shootTime) {
                    shootTimer.stop();
                    Arm.dock();
                    states++;
                }
                
                 */
                break;
            case 5: 
                println("Scale Right Acomplished!");
                break;
        }

    }

    public void scaleLeft(float distPastSwitch,float distTurnRight,float shootTime) {

        switch(states){
            case 0:  //MOVE FORWARD 
                Robot.drivetrain.differentialDrive.tankDrive(0.2, 0.2);
                if(leftEnc.getDistance() && rightEnc.getDistance() > distPastSwitch){
                states++;
                }
                println(states);
                break;
            case 1:   //TURN RIGHT
                Robot.drivetrain.differentialDrive.tankDrive(0.2, 0.0)
                if(lefEnc.getDistance() > distTurnRight){
                    Robot.drivetrain.differentialDrive.tankDrive(0.0, 0.0);
                    /*****add code to check if motor output is equal to zero and THEN increase state
                    *********/
                    states++;
                    println(states);
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
                println("Scale Left Acomplished!");
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
