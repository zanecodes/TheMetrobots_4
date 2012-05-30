package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.*;

public class PIDSourceEncoder implements PIDSource{
    
    Encoder e;
    
    public PIDSourceEncoder( int portUP, int portDown ){
        
        e = new Encoder( portUP, portDown );
        e.start();
        
    }
    
    public double pidGet(){
        
        return 1 / e.getRate();
        
    }
    
}
