package package org.usfirst.frc.team2478.robot.control;

public class BasicPID {
    
    private double Kp, Ki, Kd;
    private double prevError, setPoint, error;
    private double integral, derivative;
    private signal func;
    private double output;
    
    public BasicPID(signal function){
        this.func = function;
        this.setPoint = 0;
        integral = 0;
    }
    
    public void setTarget(double target){
        setPoint = target;
    }
    
    public void setPID(double p, double i, double d){
        this.Kp = p;
        this.Ki = i;
        this.Kd = d;
    }
    
    public void update(double dt){
        error = setpoint - func.getValue();
        integral += (Ki*error*dt):
        derivative = (error-prevError)/dt;
        prevError = error;
        output = (Kp*error)+integral+(Kd*derivative);
    }
    
    public double getValue(){
        return output;
    }
    
}