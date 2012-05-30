package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.*;

//Main robot class
class TheMetrobots extends IterativeRobot {

    //For drive controls
    DriveTrain drive;
    boolean slowDriveOverride;
    
    //Joysticks to be used
    EventJoystick gamePad;
    EventJoystick shooterJS;
    
    //For displaying feedback on the driverstation
    DriverStationLCD ds;
    
    //Air Compressor
    Compressor c;
    
    //Classes to control robot manipulators
    BridgeArm arm;
    //Collector collector;
    //Shooter shooter;
                
    
    //Called once when robot is created (constructor)
    public void robotInit() {
        
        //Initializes classes with motor ports and initial states
        drive = new DriveTrain(1,2,3,4);
        drive.setInvertedMotors(false, false, true, true);
        slowDriveOverride = false;
        
        ds = DriverStationLCD.getInstance();
        
        //Starts the compressor and runs it automatically
        c = new Compressor(1, 1);
        c.start();
        
        arm = new BridgeArm( 3, 2 );
        arm.setArmPosition(BridgeArm.RELAXED_POSITION);
        
        //collector = new Collector( 5, 6 );
        //collector.setArmBeltState(Collector.BELT_OFF);
        //collector.setMainBeltState(Collector.BELT_OFF);
        
        //shooter = new Shooter( 7 );
        //shooter.setSpeed(Shooter.SPEED_OFF);
        
        gamePad = new EventJoystick( 2, 10 );
        shooterJS = new EventJoystick( 1, 11 );
                        
    }

    public void autonomousInit() {
        
    }
    
    public void autonomousPeriodic() {

        
        
    }
    
    public void autonomousContinuous() {

        
        
    }

    public void teleopInit() {

        arm.setArmPosition(BridgeArm.RELAXED_POSITION);
        
    }
    
    public void teleopPeriodic() {
        
        
        
    }
    
    public void teleopContinuous() {
        
        //Updates joysticks for event handling
        gamePad.update();
        shooterJS.update();

        
        //Sets the arm position based on the operator joystick
        if( shooterJS.getAxis(EventJoystick.JS_Y) >= 0.95 ){
            
            arm.setArmPosition(BridgeArm.DOWN_POSITION);
        
        }
        
        if( shooterJS.getAxis(EventJoystick.JS_Y) <= -0.95 ){
            
            arm.setArmPosition(BridgeArm.UP_POSITION);

        }
        
        //Fires the solenoids for the arm
        arm.setPiston();
        
        
        
        
        //Uses joystick events to set states for Collector
        /*if( shooterJS.getButton(1) ){
            
            collector.setBothBeltStates(Collector.BELT_FORWARD);
            
        }
        else{
            
            collector.setBothBeltStates(Collector.BELT_OFF);
            
        }
        
        if( shooterJS.getButtonDown(4) ){
            
            collector.setArmBeltState(Collector.BELT_FORWARD);
            collector.setMainBeltState(Collector.BELT_OFF);

        }
        
        if( shooterJS.getButtonDown(5) ){
            
            collector.setArmBeltState(Collector.BELT_OFF);
            collector.setMainBeltState(Collector.BELT_FORWARD);

        }
        
        if( shooterJS.getButtonDown(3) ){
            
            collector.setArmBeltState(Collector.BELT_REVERSE);
            collector.setMainBeltState(Collector.BELT_REVERSE);

        }
        
        if( shooterJS.getButtonDown(2) ){
            
            collector.setBothBeltStates(Collector.BELT_OFF);
            
        }*/
        
        //Fires the motors for the collector
        //collector.setMotors();
        
        
        
        
        //Uses joystick events to set states for shooter
        /*if( shooterJS.getButtonDown(6)){
            
            shooter.setAdjustment( shooter.adjustment + Shooter.ADJUSTMENT_INCREMENT );
            
        }
        
        if( shooterJS.getButtonDown(7)){
            
            shooter.setAdjustment( shooter.adjustment - Shooter.ADJUSTMENT_INCREMENT );
            
        }
        
        if( shooterJS.getButtonDown(11)){
            
            shooter.setSpeed( Shooter.SPEED_HAIL_MARY );
            
        }
        
        if( shooterJS.getButtonDown(10)){
            
            shooter.setSpeed( Shooter.SPEED_KEY );
            
        }
        
        if( shooterJS.getButtonDown(9)){
            
            shooter.setSpeed( Shooter.SPEED_FENDER );
            
        }
        
        if( shooterJS.getButtonDown(8)){
            
            shooter.setSpeed( Shooter.SPEED_OFF );
            
        }*/
        
        //Fires shooter motor
        //shooter.setMotor();
        
        
        
        //Handles drive controls
        slowDriveOverride = ( gamePad.getButton(EventJoystick.GP_A) || gamePad.getButton(EventJoystick.GP_B) || gamePad.getButton(EventJoystick.GP_X) || gamePad.getButton(EventJoystick.GP_Y) || gamePad.getButton(EventJoystick.GP_LB) || gamePad.getButton(EventJoystick.GP_RB) );

        if(slowDriveOverride){ //Fine Tune Driving mechanics
            
            double slowX = ( gamePad.getButton(EventJoystick.GP_RB)? 1 : 0 ) - ( gamePad.getButton(EventJoystick.GP_LB)? 1 : 0 );
            double slowY = ( gamePad.getButton(EventJoystick.GP_Y)? 1 : 0 ) - ( gamePad.getButton(EventJoystick.GP_A)? 1 : 0 );
            double slowTurn = ( gamePad.getButton(EventJoystick.GP_B)? 1 : 0 ) - ( gamePad.getButton(EventJoystick.GP_X)? 1 : 0 );
            
            drive.meccanumXYTurn( slowX * DriveTrain.SLOW_DRIVE_MULTIPLIER, slowY * DriveTrain.SLOW_DRIVE_MULTIPLIER, slowTurn * DriveTrain.SLOW_DRIVE_MULTIPLIER );
            
        }
        else{ //Default Driving mechanics
            
            drive.meccanumXYTurn( gamePad.getAxis( EventJoystick.GP_LEFT_X ), gamePad.getAxis( EventJoystick.GP_LEFT_Y ), gamePad.getAxis( EventJoystick.GP_RIGHT_X) );
            //drive.meccanumRLSrafe( gamePad.getAxis( EventJoystick.GP_LEFT_Y ), gamePad.getAxis( EventJoystick.GP_RIGHT_Y ), gamePad.getAxis( EventJoystick.GP_TRIGGER) );
        
        }
        
        //Fires the drive motors
        drive.setMotors();
        
        //Displays output
        
        //Competition Outputs
        /*ds.println(DriverStationLCD.Line.kMain6, 1, "Arm: " + (arm.armPosition == BridgeArm.UP_POSITION ? "Up" : ( arm.armPosition == BridgeArm.DOWN_POSITION ? "Down" : "Neutral" ) ) );
        ds.println(DriverStationLCD.Line.kUser2, 1, "Main Belt: " + ( collector.mainBeltState == Collector.BELT_FORWARD ? "Forward" : ( collector.mainBeltState == Collector.BELT_REVERSE ? "Reverse" : "Off" ) ) );
        ds.println(DriverStationLCD.Line.kUser3, 1, "Arm Belt: " + ( collector.armBeltState == Collector.BELT_FORWARD ? "Forward" : ( collector.armBeltState == Collector.BELT_REVERSE ? "Reverse" : "Off" ) ) );
        ds.println(DriverStationLCD.Line.kUser4, 1, "Shooter: " + Double.toString( shooter.shooterSpeed + shooter.adjustment ) );
        ds.println(DriverStationLCD.Line.kUser5, 1, "" );
        ds.println(DriverStationLCD.Line.kUser6, 1, "" );*/
        
        //Testing outputs
        
        ds.println(DriverStationLCD.Line.kMain6, 1, "Test - 3" );
        ds.println(DriverStationLCD.Line.kUser2, 1, Double.toString(drive.frontLeftSpeed));
        ds.println(DriverStationLCD.Line.kUser3, 1, Double.toString(drive.frontLeftSpeed));
        ds.println(DriverStationLCD.Line.kUser4, 1, Double.toString(drive.frontLeftSpeed));
        ds.println(DriverStationLCD.Line.kUser5, 1, Double.toString(drive.frontLeftSpeed));
        ds.println(DriverStationLCD.Line.kUser6, 1, Integer.toString(arm.armPosition) );
        
        ds.updateLCD();
        
        Timer.delay( 0.020 );
        
    }
    
    public void disabledInit() {
        
        
        
    }
    
    public void disabledPeriodic() {
        
        
        
    }
    
    public void disabledContinuous() {
        
        
        
    }
    
    
}
