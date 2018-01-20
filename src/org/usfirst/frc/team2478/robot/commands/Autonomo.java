package org.usfirst.frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.Encoder;

public class Autonomo extends CommandBase {
    public int states;
    Encoder leftEnc;
    Encoder rightEnc;
    
	
    //!!!!!!!!!!!!!!!!PUT IN ENCODER PORTS BEFORE RUNNING!!!!!!!!!!!!!!!!!!!!!!!!!!!11
    public Autonomo() {
    	requires(drivetrain);
    	requires(motionSensors);

        states = 0;
        System.out.println("constructor of Autonomo ==========================================================");
    }

    protected void initialize() {
    }

    public void autoLine(double distToAutoLine) {
        //MOVE FORWARD
//        double angle = Robot.motionSensors.navx.getAngle();
        
//        Robot.pidLoop.setSetpoint(0);
        
//        double output = Robot.pidLoop.calculate(angle, Robot.timer.get());
        
//        Robot.drivetrain.differentialDrive.tankDrive((0.5 + output), (0.5 - output));
        
        if(leftEnc.get() >= distToAutoLine && leftEnc.get() >= distToAutoLine) {
            System.out.println("acomplished!");
            drivetrain.tankDriveSquared(0, 0);
        }
    }

    public void switchStraight(double distToAutoLine, double heightToSwitch, double shootTime) {
        //Timer shootTimer = new Timer();
        
        switch(states){
            case 0:
                //MOVE FORWARD   
                drivetrain.tankDriveSquared(0.2, 0.2);
                if(leftEnc.getDistance() >= distToAutoLine && rightEnc.getDistance() >= distToAutoLine){
                //STOP MOVEMENT
                    drivetrain.tankDriveSquared(0, 0);
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
                drivetrain.tankDriveSquared(0.2, 0.2);
                if(leftEnc.getDistance() >= distPastSwitch&& rightEnc.getDistance() >= distPastSwitch){
                states++;
                }
                System.out.println(states);
                break;
            case 1:   //TURN LEFT
                drivetrain.tankDriveSquared(0.0, 0.2);
                if(rightEnc.getDistance() >= distTurnLeft){
                	//STOP MOVEMENT
                    drivetrain.tankDriveSquared(0.0, 0.0);
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
                drivetrain.tankDriveSquared(0.2, 0.2);
                if(leftEnc.getDistance() >= distPastSwitch && rightEnc.getDistance() > distPastSwitch){
                states++;
                }
                System.out.println(states);
                break;
            case 1:   //TURN RIGHT
                drivetrain.tankDriveSquared(0.2, 0.0);
                if(leftEnc.getDistance() > distTurnRight){
                    drivetrain.tankDriveSquared(0.0, 0.0);
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
        drivetrain.tankDriveSquared(0.0, 0.0);
    }
    protected boolean isFinished() { // needs a stop condition!
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {}
}