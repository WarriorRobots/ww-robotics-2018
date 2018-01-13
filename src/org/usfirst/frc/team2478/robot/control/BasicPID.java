package org.usfirst.frc.team2478.robot.control;

public class BasicPID {
    
    private double Kp, Ki, Kd;
    private double prevError, setPoint, error;
    private double integral, derivative;
    private double output;
    private double izone;
    
    public static double ALWAYS_TRUE = -1;
    public static double ALWAYS_FALSE = 0;
    
    public BasicPID(double p, double i, double d) {
    	this(p, i, d, 0.0);
    }
    
    public BasicPID(double p, double i, double d, double setPoint){
    	this.setPID(p,i,d);
        this.setPoint = setPoint;
        integral = 0;
        izone = ALWAYS_TRUE;
    }
    
    public void setTarget(double target){
        setPoint = target;
    }
    
    /**NOTE: Set your izone to ALWAYS_TRUE if you want to always
    		do the integral.*/
    public void setIzone(double izone){
    	this.izone = izone;
    }
    
    public void setPID(double p, double i, double d){
        this.Kp = p;
        this.Ki = i;
        this.Kd = d;
    }
    
    //NOTE: Check the izone note above
    public void calculate(double dt, double value){
        error = setPoint - value;
        if((error<=izone && error >=-izone)|izone==ALWAYS_TRUE)
        	integral += (Ki*error*dt);
        derivative = (error-prevError)/dt;
        prevError = error;
        output = (Kp*error)+integral+(Kd*derivative);
    }
    
    public double getOutput(){
        return output;
    }
    
    public double[] getPID() {
    	double[] out = new double[3];
    	out[0]=Kp;out[1]=Ki;out[2]=Kd;
    	return out;
    }
    
}