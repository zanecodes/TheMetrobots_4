package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Victor;

//Class to control a two-part belt system
public class Collector {
    
    static final double BELT_SPEED = 1.0;

    //Belt State enumerations
    static final int BELT_OFF = 0;
    static final int BELT_FORWARD = 1; 
    static final int BELT_REVERSE = 2;

    Victor armBelt;
    Victor mainBelt;
    
    int armBeltState;
    int mainBeltState;
    
    Collector( int mainPort, int armPort ){
        
        //Initializes class
        
        armBelt = new Victor( armPort );
        mainBelt = new Victor( mainPort );
        
        armBeltState = BELT_OFF;
        mainBeltState = BELT_OFF;
        
    }
    
    //Set the two sections independently
    public void setArmBeltState( int state ){
        
        armBeltState = (state == BELT_FORWARD ? 1 : ( state == BELT_REVERSE ? -1 : 0 ) );
        
    }
    
    public void setMainBeltState( int state ){
        
        mainBeltState = (state == BELT_FORWARD ? 1 : ( state == BELT_REVERSE ? -1 : 0 ) );
        
    }
    
    
    
    //Set the two sections together
    public void setBothBeltStates( int state ){
        
        armBeltState = (state == BELT_FORWARD ? 1 : ( state == BELT_REVERSE ? -1 : 0 ) );

        mainBeltState = (state == BELT_FORWARD ? 1 : ( state == BELT_REVERSE ? -1 : 0 ) );

    }
    
    
    
    //Fire the motors in the system
    public void setMotors(){
        
        armBelt.set( Collector.BELT_SPEED * (armBeltState == BELT_FORWARD ? 1 : ( armBeltState == BELT_REVERSE ? -1 : 0 ) ) );
        mainBelt.set( Collector.BELT_SPEED * (mainBeltState == BELT_FORWARD ? 1 : ( mainBeltState == BELT_REVERSE ? -1 : 0 ) ) );

        
    }
    
}
