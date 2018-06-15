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
    
    public void permute(){
        float permutation;
        
        do{
         permutation = (float) Math.random()*(BestRobot.neighbordChangeVal-(-BestRobot.neighbordChangeVal))+(-BestRobot.neighbordChangeVal);
        }while(this.parameters.get(0)+permutation<=RandomConstructor.MIN_WHEEL_SEPARATION);
        this.parameters.set(0, this.parameters.get(0)+permutation);
        
        do{
        permutation = (float) Math.random()*(BestRobot.neighbordChangeVal-(-BestRobot.neighbordChangeVal))+(-BestRobot.neighbordChangeVal);
        }while(this.parameters.get(1)+permutation<=RandomConstructor.MIN_WHEEL_RADIUS);
        this.parameters.set(1, this.parameters.get(1)+permutation);
        
        do{
        permutation = (float) Math.random()*(BestRobot.neighbordChangeVal-(-BestRobot.neighbordChangeVal))+(-BestRobot.neighbordChangeVal);
        }while(this.parameters.get(2)+permutation<=RandomConstructor.MIN_DISTANCE_TO_AXIS);
        this.parameters.set(2, this.parameters.get(2)+permutation);
        
        do{
        permutation = (float) Math.random()*(BestRobot.neighbordChangeVal-(-BestRobot.neighbordChangeVal))+(-BestRobot.neighbordChangeVal);
        }while(this.parameters.get(3)+permutation<=RandomConstructor.MIN_SENSOR_SEPARATION);
        this.parameters.set(3, this.parameters.get(3)+permutation);
    }
    
    @Override
    public boolean equals(Object o){
        
        Robot robotIn = (Robot) o;
        
        if(robotIn == null)
            return false;
        
        return this.parameters.get(0)==robotIn.getParameters().get(0) && this.parameters.get(1)==robotIn.getParameters().get(1) && this.parameters.get(2)==robotIn.getParameters().get(2) && this.parameters.get(3)==robotIn.getParameters().get(3);
    }
}
