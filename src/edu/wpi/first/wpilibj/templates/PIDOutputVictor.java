package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.*;

public class PIDOutputVictor implements PIDOutput{
    
    Victor v;
    
    public PIDOutputVictor( int port ){
        
        v = new Victor( port );
        
    }
    
    public void pidWrite( double output ){
        
        v.set( output );
        
    }
    
}
