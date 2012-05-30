package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Victor;

public class DriveTrain {
    
    static final double SLOW_DRIVE_MULTIPLIER = 0.4;
    static final double PID_MULTIPLIER = 60 / (360 * 4);
    
    static final double PID_P = 0.0;
    static final double PID_I = 0.0;
    static final double PID_D = 0.0;
    
    Victor frontLeft;
    Victor backLeft;
    Victor frontRight;
    Victor backRight;
    
    //PIDController frontLeftPID;
    //PIDController backLeftPID;
    //PIDController frontRightPID;
    //PIDController backRightPID;
    
    double frontLeftSpeed;
    double backLeftSpeed;
    double frontRightSpeed;
    double backRightSpeed;
    
    int fLInverter;
    int bLInverter;
    int fRInverter;
    int bRInverter;
    
    //boolean isPID;
    
    DriveTrain( int frontLeftPort, int backLeftPort, int frontRightPort, int backRightPort ){
        
        frontLeft = new Victor( frontLeftPort );
        backLeft = new Victor( backLeftPort );
        frontRight = new Victor( frontRightPort );
        backRight = new Victor( backRightPort );
        
        //frontLeftPID = new PIDController( PID_P, PID_I, PID_D, (PIDSource)(new PIDSourceEncoder( 9, 2 )), (PIDOutput)(new PIDOutputVictor( frontLeftPort )) );
        //backLeftPID = new PIDController( PID_P, PID_I, PID_D, (PIDSource)(new PIDSourceEncoder( 3, 4 )), (PIDOutput)(new PIDOutputVictor( frontLeftPort )) );
        //frontRightPID = new PIDController( PID_P, PID_I, PID_D, (PIDSource)(new PIDSourceEncoder( 5, 6 )), (PIDOutput)(new PIDOutputVictor( frontLeftPort )) );
        //backRightPID = new PIDController( PID_P, PID_I, PID_D, (PIDSource)(new PIDSourceEncoder( 7, 8 )), (PIDOutput)(new PIDOutputVictor( frontLeftPort )) );
        
        fLInverter = 1;
        bLInverter = 1;
        fRInverter = 1;
        bRInverter = 1;
        
        //disablePID();
        
    }
    
    /*public void enablePID(){
        
        frontLeftPID.enable();
        backLeftPID.enable();
        frontRightPID.enable();
        backRightPID.enable();
        
        isPID = true;
        
    }
    
    public void disablePID(){
        
        frontLeftPID.disable();
        backLeftPID.disable();
        frontRightPID.disable();
        backRightPID.disable();
        
        isPID = false;
        
    }*/
    
    public void setMotors(){
        /*
        if( isPID ){
            
            frontLeftPID.setSetpoint( frontLeftSpeed * PID_MULTIPLIER );
            backLeftPID.setSetpoint( backLeftSpeed * PID_MULTIPLIER );
            frontRightPID.setSetpoint( frontRightSpeed * PID_MULTIPLIER );
            backRightPID.setSetpoint( backRightSpeed * PID_MULTIPLIER );
            
        }
        else{*/
            
            frontLeft.set(frontLeftSpeed);
            backLeft.set(backLeftSpeed);
            frontRight.set(frontRightSpeed);
            backRight.set(backRightSpeed);
            
            
        //}
    }
    
    public void tank( double leftSide, double rightSide ){
        
        frontLeftSpeed = ( ( leftSide ) * fLInverter );
        backLeftSpeed = ( ( leftSide ) * bLInverter );
        frontRightSpeed = ( ( rightSide ) * fRInverter );
        backRightSpeed = ( ( rightSide ) * bRInverter );
        
    }
    
    public void arcade( double straight, double turn ){
        
        frontLeftSpeed = ( ( straight + turn ) * fLInverter );
        backLeftSpeed = ( ( straight + turn ) * bLInverter );
        frontRightSpeed = ( ( straight - turn ) * fRInverter );
        backRightSpeed = ( ( straight - turn ) * bRInverter );
        
    }
    
    public void meccanumXYTurn( double x, double y, double turn ){
        
        frontLeftSpeed = ( ( x + y/* + turn*/ ) * fLInverter );
        backLeftSpeed = ( ( -x + y + turn * 37 / (27 + 37) ) * bLInverter );
        frontRightSpeed = ( ( -x + y - turn * 27 / (27 + 37) ) * fRInverter );
        backRightSpeed = ( ( x + y - turn ) * bRInverter );
        
    }
    
    public void meccanumRLSrafe( double leftSide, double rightSide, double strafe ){
        
        frontLeftSpeed = ( ( leftSide + strafe ) * fLInverter );
        backLeftSpeed = ( ( leftSide - strafe ) * bLInverter );
        frontRightSpeed = ( ( rightSide - strafe ) * fRInverter );
        backRightSpeed = ( ( rightSide + strafe ) * bRInverter );
        
    }
    
    public void setInvertedMotors( boolean fl, boolean bl, boolean fr, boolean br ){
        
        fLInverter = fl ? -1 : 1;
        bLInverter = bl ? -1 : 1;
        fRInverter = fr ? -1 : 1;
        bRInverter = br ? -1 : 1;

    }
    
}
