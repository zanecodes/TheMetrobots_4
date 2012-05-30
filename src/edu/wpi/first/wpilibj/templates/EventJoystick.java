package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;

//Wrapper class for Joystick
//Adds Button/Joystick enums + up/down events
public class EventJoystick {
    
    //Button enumerations for gamepad
    static final int GP_A = 1;
    static final int GP_B = 2;
    static final int GP_X = 3;
    static final int GP_Y = 4;
    static final int GP_LB = 5;
    static final int GP_RB = 6;
    static final int GP_BACK = 7;
    static final int GP_START = 8;
    static final int GP_LEFT_JS = 9;
    static final int GP_RIGHT_JS = 10;
    
    //Axis enumerations for gamepad
    static final int GP_LEFT_X = 1;
    static final int GP_LEFT_Y = 2;
    static final int GP_TRIGGER = 3;
    static final int GP_RIGHT_X = 4;
    static final int GP_RIGHT_Y = 5;
    static final int GP_DPAD_X = 6;
    static final int GP_DPAD_Y = 7;
    
    //Axis enumerations for attack 3 joystick
    static final int JS_X = 1;
    static final int JS_Y = 2;
    static final int JS_TROTTLE = 3;
    
    //Nested Joystick
    Joystick joystick;
    int numButtons;
    
    //For containing event data
    boolean[] oldBtnStates;
    boolean[] newBtnStates;
    boolean[] upEventStates;
    boolean[] downEventStates;
    
    EventJoystick( int port, int numButtonsSet ){
        
        //Initializes Class
        
        joystick = new Joystick( port );
        
        numButtons = numButtonsSet;
        
        oldBtnStates = new boolean[numButtons + 1];
        newBtnStates = new boolean[numButtons + 1];

        upEventStates = new boolean[numButtons + 1];
        downEventStates = new boolean[numButtons + 1];
        
        for( int i = 0; i < numButtons + 1; i++ ){
            
            oldBtnStates[i] = false;
            newBtnStates[i] = false;

            upEventStates[i] = false;
            downEventStates[i] = false;
            
        }
                
    }
    
    //Handles events
    public void update(){
        
        for( int i = 1; i < numButtons + 1; i++ ){
            
            newBtnStates[i] = joystick.getRawButton(i);
            
            upEventStates[i] = newBtnStates[i] && !oldBtnStates[i];
            
            downEventStates[i] = !newBtnStates[i] && !oldBtnStates[i];
        
            oldBtnStates[i] = newBtnStates[i];

            
        }
        
    }
    
    public double getAxis( int axis ){
        
        if( numButtons == 10 ){
            
            if( axis == EventJoystick.GP_LEFT_X || axis == EventJoystick.GP_RIGHT_X ){
                
                return joystick.getRawAxis(axis);
                
            }
            else{
                
                return -joystick.getRawAxis(axis);
                
            }
            
        }
        else{
            
            if( axis == EventJoystick.JS_X ){
                
                return joystick.getRawAxis(axis);
                
            }
            else{
                
                return -joystick.getRawAxis(axis);
                
            }
            
        }
        
        
    }
    
    public boolean getButton( int button ){
        
        return joystick.getRawButton( button );
        
    }
    
    public boolean getButtonUp( int button ){
        
        return upEventStates[button];
        
    }

    public boolean getButtonDown( int button ){
        
        return downEventStates[button];
        
    }
    
}
