package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Relay;

//To control an arm powered by solenoids
public class BridgeArm {
    
    //Arm position enumerations
    static final int RELAXED_POSITION = 0;
    static final int UP_POSITION = 1;
    static final int DOWN_POSITION = 2;
    
    //Relays for solenoids
    Relay armUp;
    Relay armDown;
    int armPosition;
    
    BridgeArm( int upPort, int downPort ){
        
        //Initializes class
        
        armUp = new Relay( upPort, Relay.Direction.kReverse );
        armDown = new Relay( downPort, Relay.Direction.kForward );
        
        armPosition = RELAXED_POSITION;
        
    }
    
    //Sets requested position of the arm
    public void setArmPosition( int position ){
        
        armPosition = position;
        
    }
    
    //Fires solenoids
    public void setPiston(){
        
        if( armPosition == 1 ){
            
            armUp.set(Relay.Value.kOn);
            
        }
        else{
            
            armUp.set(Relay.Value.kOff);
            
        }
        
        if( armPosition == 2 ){
            
            armDown.set(Relay.Value.kOn);
            
        }
        else{
            
            armDown.set(Relay.Value.kOff);
            
        }
        
    }
    
}
