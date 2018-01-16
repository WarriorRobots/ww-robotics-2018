package org.usfirst.frc.team2478.robot.deprecated;

import edu.wpi.first.wpilibj.Timer;

public abstract class BasicPID {
    
    private double Kp, Ki, Kd;
    private double prevError, setPoint, error;
    private double integral, derivative;
    private double output;
    private double izone;
    
    private double pastTime;
	protected Timer timer;
	protected boolean clockState;
    
    public static double ALWAYS_TRUE = -1;
    public static double ALWAYS_FALSE = 0;
    
    //Below makes BasicPID class with p,i,d or p,i,d,setPoint as the parameters
    //(Sets izone to true)
    ///////////////////////////////////////////////////////////////////////////
    
    public BasicPID(double p, double i, double d) {
    	this(p, i, d, 0.0);
    }
    
    public BasicPID(double p, double i, double d, double setPoint){
    	this.setPID(p,i,d);
        this.setPoint = setPoint;
        //Integral is taken as a sum of rectangles over a period of time
        //	(this method is called a Riemann sum)
        integral = 0;
        izone = ALWAYS_TRUE;
		timer = new Timer();
		timer.reset();
		clockState = false;
    }
    
    ///////////////////////////////////////////////////////////////////////////
    
    public void PIDreset() {
    	integral = 0;
    	izone = ALWAYS_TRUE;
		timer = new Timer();
		timer.reset();
		clockState = false;
    }
    
    //Below sets the setPoint under the name of 'target'
    public void setTarget(double target){
        setPoint = target;
    }
    
    /**NOTE: Set your izone to ALWAYS_TRUE if you want to always
    		do the integral.*/
    //Below sets the Izone
    //	(zone of which the the error is within the absolute value of
    //		the setPoint to then take the integral within)
    public void setIzone(double izone){
    	this.izone = izone;
    }
    
    //Below sets the PID gains 
    public void setPID(double p, double i, double d){
        this.Kp = p;
        this.Ki = i;
        this.Kd = d;
    }
    
    //NOTE: Check the izone note above
    //Calculates the PID equation
    protected void calculate(double value){
    	double t = timer.get();
    	double dt = t-pastTime;
    	pastTime = t;
    	
    	
        error = setPoint - value;
        
        if(Math.abs(error)<=izone | izone==ALWAYS_TRUE) // forces the integral to be taken within the izone or to be TRUE
        	integral += (Ki*error*dt); // uses a method called a Riemann sum (look above)
        try{
        	derivative = (error-prevError)/dt;
        }
        catch(Exception e){System.out.println(e);}
        
        prevError = error;
        
        output = (Kp*error)+integral+(Kd*derivative);
        //(look up PID equation to see math)
    }
    
    //Below forces other controls to have an update() function
    public abstract void update();
    
    //Below gets output
    public double getOutput(){
        return output;
    }
    
    //Below gets PID gains
    public double[] getPID() {
    	return new double[] {Kp,Ki,Kd};
    }
    
	public void startTimer() {
		timer.start(); 
		clockState = true;
	}
	
	public void stopTimer() {
		timer.start();
		clockState = false;
	}
	
	public void restartTimer() {
		timer.reset();
	}
	
	public boolean getTimerState() {
		return clockState;
	}
	
	public double getTimer() {
		return timer.get();
	}

}
