/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bestrobot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author David
 */
public class ObjectiveFunction {
    
    float wheelSpeed, wheelSeparation, wheelRadius;
    float distanceToWheels;
    float robotHigh, robotWidth;
    float sensorDistance, sensorSeparation;
    List<List<Float>> circuite;
    
    float x,z,rot;
    float leftWheel, rightWheel;
    static final float THRESHOLD = 0.15f;
    static final float DT = 0.1f;
    float[] leftSensorVector,rightSensorVector = new float[4];
    float[] leftSensorPosition,rightSensorPosition = new float[4];
    float[][] model = new float[4][4];
    
    public ObjectiveFunction(float wheelSpeed, float wheelSeparation, float wheelRadius, float distanceToWheels, float robotHigh, float robotWidth,float sensorDistance, float sensorSeparation, String path) throws FileNotFoundException, IOException{
    
        this.wheelSpeed = wheelSpeed;
        this.leftWheel = wheelSpeed;
        this.rightWheel = wheelSpeed;
        this.wheelSeparation = wheelSeparation;
        this.wheelRadius = wheelRadius;
        this.distanceToWheels = distanceToWheels;
        this.robotHigh = robotHigh;
        this.robotWidth = robotWidth;
        this.sensorDistance = sensorDistance;
        this.sensorSeparation = sensorSeparation;
        setCircuite(path);
        this.x = this.circuite.get(0).get(0);
        this.z = this.circuite.get(0).get(2);
        rot = 0.0f;
        setSensors();
        
    }
    
    private void setCircuite(String path) throws FileNotFoundException, IOException{
        this.circuite = new ArrayList<>();
        String line;
        FileReader f = new FileReader(path);
        BufferedReader b = new BufferedReader(f);
        while((line=b.readLine())!=null){
            
            List<Float> vertex = new ArrayList<>();
            line = line.replace(',','.');
            int separation = line.indexOf(".",line.indexOf(".")+1);
            String xString = line.substring(0, separation);
            String zString = line.substring(separation+1);
            vertex.add(Float.parseFloat(xString));
            vertex.add(0f);
            vertex.add(Float.parseFloat(zString));
            this.circuite.add(vertex);
            
        }
    }
    
    private void setSensors(){
        
        this.leftSensorVector[0] = -this.sensorSeparation/2f;
        this.leftSensorVector[1] = 0f;
        this.leftSensorVector[2] = -(this.sensorDistance+this.distanceToWheels);
        this.leftSensorVector[3] = 1;
        this.leftSensorPosition = this.leftSensorVector;
        this.rightSensorVector[0] = this.sensorSeparation/2f;
        this.rightSensorVector[1] = 0f;
        this.rightSensorVector[2] = -(this.sensorDistance+this.distanceToWheels);
        this.rightSensorVector[3] = 1;
        this.rightSensorPosition = this.rightSensorVector;
        
    }
    
    public float race(){
        
        movementController();
        x -= (rightWheel + leftWheel) * ((wheelRadius * Math.sin(Math.toRadians(rot)))/2) * DT;
        z -= (rightWheel + leftWheel) * ((wheelRadius * Math.cos(Math.toRadians(rot)))/2) * DT;
        rot += (rightWheel - leftWheel) * (wheelRadius/wheelSeparation) * DT;
        
        return 0;
        
    }
    
    private void movementController(){
        
        //Calculo sobre si debe parar la rueda izquierda
        for (int i = 0; i < circuite.size(); i++) {
            float distance = (float) Math.sqrt(Math.pow(circuite.get(i).get(0)-this.leftSensorPosition[0], 2)+Math.pow(circuite.get(i).get(2)-this.leftSensorPosition[2], 2));
            if(distance<THRESHOLD){
                leftWheel=0;
                break;
            }
        }
        
        //Calculo sobre si debe parar la rueda derecha
        for (int i = 0; i < circuite.size(); i++) {
            float distance = (float) Math.sqrt(Math.pow(circuite.get(i).get(0)-this.rightSensorPosition[0], 2)+Math.pow(circuite.get(i).get(2)-this.rightSensorPosition[2], 2));
            if(distance<THRESHOLD){
                rightWheel=0;
                break;
            }
        }
    }
    
}
