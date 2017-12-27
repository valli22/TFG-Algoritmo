/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bestrobot;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author David
 */
public class Robot {
    
    private List<Float> parameters;
    private float time;

    public Robot(List<Float> neighbourParameters){
        this.parameters = neighbourParameters;
    }
    
    public Robot(){
        this.parameters = new ArrayList<>();
    }
    
    public void setTime(float timeIn){
        this.time = timeIn;
    }

    public float getTime(){
        return this.time;
    }

    public List<Float> getParameters(){
        return this.parameters;
    }
    
    @Override
    public boolean equals(Object o){
        
        Robot robotIn = (Robot) o;
        
        if(robotIn == null)
            return false;
        
        return this.parameters.get(0)==robotIn.getParameters().get(0) && this.parameters.get(1)==robotIn.getParameters().get(1) && this.parameters.get(2)==robotIn.getParameters().get(2) && this.parameters.get(3)==robotIn.getParameters().get(3);
    }
}
