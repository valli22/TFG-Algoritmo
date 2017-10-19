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
    private final float MIN_WHEEL_SEPARATION = 1;
    private final float MAX_WHEEL_SEPARATION = 40;
    private final float MIN_WHEEL_RADIUS = 0.1f;
    private final float MAX_WHEEL_RADIUS = 2;
    private final float MIN_DISTANCE_TO_WHEELS = 0;
    private final float MAX_DISTANCE_TO_WHEELS = 40;
    private final float MIN_SENSOR_DISTANCE = 1;
    private final float MAX_SENSOR_DISTANCE = 5;
    private final float MIN_SENSOR_SEPARATION = 0;
    private final float MAX_SENSOR_SEPARATION = 44;
    
    public RandomConstructor(){   
    }
    
    public Robot produceNumbers(){
        
        List<Float> ret = new ArrayList<>();
        ret.add((float) (Math.random()*(MAX_WHEEL_SEPARATION-MIN_WHEEL_SEPARATION)+MIN_WHEEL_SEPARATION));
        ret.add((float) (Math.random()*(MAX_WHEEL_RADIUS-MIN_WHEEL_RADIUS)+MIN_WHEEL_RADIUS));
        ret.add((float) (Math.random()*(MAX_DISTANCE_TO_WHEELS-MIN_DISTANCE_TO_WHEELS)+MIN_DISTANCE_TO_WHEELS));
        ret.add((float) (Math.random()*(MAX_SENSOR_DISTANCE-MIN_SENSOR_DISTANCE)+MIN_SENSOR_DISTANCE));
        ret.add((float) (Math.random()*(MAX_SENSOR_SEPARATION-MIN_SENSOR_SEPARATION)+MIN_SENSOR_SEPARATION));
        return new Robot(ret);
    
    }
}
