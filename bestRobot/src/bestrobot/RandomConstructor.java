/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bestrobot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author David
 */
public class RandomConstructor {
    private float minWheelSeparation;
    private float maxWheelSeparation;
    private float minWheelRadius;
    private float maxWheelRadius;
    private float minDistanceToWheels;
    private float maxDistanceToWheels;
    private float minRobotHigh;
    private float maxRobotHigh;
    private float minRobotWidth;
    private float maxRobotWidth;
    private float minSensorDistance;
    private float maxSensorDistance;
    private float minSensorSeparation;
    private float maxSensorSeparation;
    
    public RandomConstructor(){
        
    }
    
    public List<Float> produceNumbers(){
        
        List<Float> ret = new ArrayList<>();
        ret.add((float) (Math.random()*(maxWheelSeparation-minWheelSeparation)+minWheelSeparation));
        ret.add((float) (Math.random()*(maxWheelRadius-minWheelRadius)+minWheelRadius));
        ret.add((float) (Math.random()*(maxDistanceToWheels-minDistanceToWheels)+minDistanceToWheels));
        ret.add((float) (Math.random()*(maxRobotHigh-minRobotHigh)+minRobotHigh));
        ret.add((float) (Math.random()*(maxRobotWidth-minRobotWidth)+minRobotWidth));
        ret.add((float) (Math.random()*(maxSensorDistance-minSensorDistance)+minSensorDistance));
        ret.add((float) (Math.random()*(maxSensorSeparation-minSensorSeparation)+minSensorSeparation));
        return ret;
    
    }
}
