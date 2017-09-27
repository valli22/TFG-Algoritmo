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
    float[] leftSensorVector = new float[4];
    float[] rightSensorVector = new float[4];
    float[] leftSensorPosition = new float[4];
    float[] rightSensorPosition = new float[4];
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
        this.rot = 0.0f;
        setSensors();
        float[] c = new float[3];
        c[0] = 3;
        c[1] = 4;
        c[2] = 5;
        translate(null, c);
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
        
        float[] vectorTranslate = new float[3];
        vectorTranslate[0] = x;
        vectorTranslate[1] = 0.0f;
        vectorTranslate[2] = z;
        this.model = translate(indentityMatrix4x4(),vectorTranslate);
        this.model = rotateY(model,rot);
        
        this.leftSensorPosition = mulMatrix(model, leftSensorVector);
        this.rightSensorPosition = mulMatrix(model,rightSensorVector);
        
        leftWheel = wheelSpeed;
        rightWheel = wheelSpeed;
        
        //añadir bucle y condicion de parada
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
    
    //matriz por matriz
    private float[][] mulMatrix(float[][] m1, float[][] m2){
        
        if(m1[0].length != m2.length)
            throw new RuntimeException("Cannot multiply this matrix");
        
        float[][] ret = new float[m1.length][m2[0].length];
        
        for (int i = 0; i < ret.length; i++) {
            for (int j = 0; j < ret[i].length; j++) {
                
                for (int k = 0; k < m1[0].length; k++) {
                    ret[i][j] += m1[i][k]*m2[k][j];
                }
                
            }
        }
        return ret;
    }
    
    //matriz por vector
    private float[] mulMatrix(float[][] m1, float[] m2){
        
        if(m1[0].length != m2.length)
            throw new RuntimeException("Cannot multiply this matrix");
        
        float[] ret = new float[m1.length];
        
        for (int i = 0; i < ret.length; i++) {                
            for (int k = 0; k < m1[0].length; k++) {
                ret[i] += m1[i][k]*m2[k];
            }
        }
        return ret;
    }
    
    private float[][] indentityMatrix4x4(){
        
        float[][] ret = new float[4][4];
        for (int i = 0; i < ret.length; i++) {
            for (int j = 0; j < ret[i].length; j++) {
                if(i==j){
                    ret[i][j] = 1;
                }else{
                    ret[i][j]=0;
                }
            }
        }
        
        return ret;
        
    }
    
    private float[][] translate(float[][] matrix, float[] vector){
        
        float[][] matrixTranslate = indentityMatrix4x4();
        matrixTranslate[0][3] = vector[0];
        matrixTranslate[1][3] = vector[1];
        matrixTranslate[2][3] = vector[2];
        
        return mulMatrix(matrix, matrixTranslate);
        
    }
    
    private float[][] rotateY(float[][] matrix,float grades){
        
        float[][] matrixRotate = indentityMatrix4x4();
        matrixRotate[0][0] = (float) Math.cos(Math.toRadians(grades));
        matrixRotate[0][2] = (float) Math.sin(Math.toRadians(grades));
        matrixRotate[2][0] = (float) -Math.sin(Math.toRadians(grades));
        matrixRotate[2][2] = (float) Math.cos(Math.toRadians(grades));
        
        return mulMatrix(matrix, matrixRotate);
        
    }
    
}
