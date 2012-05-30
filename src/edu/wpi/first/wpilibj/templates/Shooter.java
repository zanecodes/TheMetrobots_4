package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Victor;

//Class to control a 1-motor shooter
public class Shooter {
    
    //Shooter state enumerations
    public static final double SPEED_KEY = 0.0;
    public static final double SPEED_FENDER = 0.0;
    public static final double SPEED_HAIL_MARY = 0.0;
    public static final double SPEED_OFF = 0.0;
    public static final double ADJUSTMENT_INCREMENT = 0.01;
    
    Victor shooterWheel;
    
    double shooterSpeed;
    
    double adjustment;
    
    public Shooter( int wheelPort ){
        
        //Initializes class
        
        shooterWheel = new Victor( wheelPort );
        
        shooterSpeed = 0.0;
        
        adjustment = 0.0;
        
    }
    
    //Sets the speed of the motor
    public void setSpeed( double speed ){
        
        shooterSpeed = speed;
        
        //Adjustment is reset so that when one of the enumerated values
        //is desired, the shooter gets set to that value immediately
        adjustment = 0.0;
        
    }
    
    /*Sets the manual adjustment value
     * 
     * The adjustment is in place so that the operator may increment or decrement the enumerated values
     * on the fly.  It gets added to the shooterSpeed for final motor output
     * 
     */
    public void setAdjustment( double value ){
        
        adjustment = value;
        
    }
    
    public void setMotor(){
        
        shooterWheel.set( shooterSpeed + adjustment );
        
    }
    
}
