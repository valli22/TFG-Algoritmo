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
    private static float averageWheelSeparation = 16;
    private static float averageWheelRadius = 2;
    private static float averageDistanceToAxis = 3;
    private static float averageSensorSeparation = 4;
    private static float rangeWheelSeparation = 5;
    private static float rangeWheelRadius = 1;
    private static float rangeDistanceToAxis = 3;
    private static float rangeSensorSeparation = 2;
    public static final float MIN_WHEEL_SEPARATION = averageWheelSeparation - rangeWheelSeparation;
    public static final float MAX_WHEEL_SEPARATION = averageWheelSeparation + rangeWheelSeparation;
    public static final float MIN_WHEEL_RADIUS = averageWheelRadius - rangeWheelRadius;
    public static final float MAX_WHEEL_RADIUS = averageWheelRadius + rangeWheelRadius;
    public static final float MIN_DISTANCE_TO_AXIS = averageDistanceToAxis - rangeDistanceToAxis;
    public static final float MAX_DISTANCE_TO_AXIS = averageDistanceToAxis + rangeDistanceToAxis;
    public static final float MIN_SENSOR_SEPARATION = averageSensorSeparation - rangeSensorSeparation;
    public static final float MAX_SENSOR_SEPARATION = averageSensorSeparation + rangeSensorSeparation;
    
    public RandomConstructor(){   
    }
    
    public Robot produceNumbers(){
        
        List<Float> ret = new ArrayList<>();
        ret.add((float) (Math.random()*(MAX_WHEEL_SEPARATION-MIN_WHEEL_SEPARATION)+MIN_WHEEL_SEPARATION));
        ret.add((float) (Math.random()*(MAX_WHEEL_RADIUS-MIN_WHEEL_RADIUS)+MIN_WHEEL_RADIUS));
        ret.add((float) (Math.random()*(MAX_DISTANCE_TO_AXIS-MIN_DISTANCE_TO_AXIS)+MIN_DISTANCE_TO_AXIS));
        ret.add((float) (Math.random()*(MAX_SENSOR_SEPARATION-MIN_SENSOR_SEPARATION)+MIN_SENSOR_SEPARATION));
        return new Robot(ret);
    
    }
}
