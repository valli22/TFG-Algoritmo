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
    
    private float wheelSpeed;
    private float sensorSeparation;
    private float distanceToAxis;
    private List<List<Float>> circuite;
    
    private float x,z,rot;
    private float leftWheel, rightWheel;
    //private final float THRESHOLD = 0.15f;
    private final float THRESHOLD = 0.3f;
    //private final float DT = 0.01f;
    private final float DT = 0.005f;
    private final float THRESHOLD_STOP_CONDITION = 2f;
    private float[] leftSensorVector = new float[4];
    private float[] rightSensorVector = new float[4];
    private float[] leftSensorPosition = new float[4];
    private float[] rightSensorPosition = new float[4];
    private float[][] model = new float[4][4];
    
    private float time;
    private final float TIME_TO_STOP = 70;
    
    public ObjectiveFunction(float wheelSpeed, String path) throws FileNotFoundException, IOException{
    
        
        this.wheelSpeed = wheelSpeed;
        this.leftWheel = wheelSpeed;
        this.rightWheel = wheelSpeed;
        
        setCircuite(path);
        
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
        this.leftSensorVector[2] = -(this.distanceToAxis);
        this.leftSensorVector[3] = 1;
        this.leftSensorPosition = this.leftSensorVector;
        this.rightSensorVector[0] = this.sensorSeparation/2f;
        this.rightSensorVector[1] = 0f;
        this.rightSensorVector[2] = -(this.distanceToAxis);
        this.rightSensorVector[3] = 1;
        this.rightSensorPosition = this.rightSensorVector;
        
    }
    
    public float race(float wheelSeparation, float wheelRadius, float distanceToAxis, float sensorSeparation){
        
        this.time = 0;
        this.distanceToAxis = distanceToAxis;
        this.sensorSeparation = sensorSeparation;
        setSensors();
        resetParameters();
        
        while(Math.sqrt(Math.pow(circuite.get(circuite.size()-5).get(0)-this.leftSensorPosition[0], 2)+Math.pow(circuite.get(circuite.size()-5).get(2)-this.leftSensorPosition[2], 2)) > THRESHOLD_STOP_CONDITION && Math.sqrt(Math.pow(circuite.get(circuite.size()-5).get(0)-this.rightSensorPosition[0], 2)+Math.pow(circuite.get(circuite.size()-5).get(2)-this.rightSensorPosition[2], 2)) > THRESHOLD_STOP_CONDITION){
        //while(Math.sqrt(Math.pow(circuite.get(50).get(0)-this.leftSensorPosition[0], 2)+Math.pow(circuite.get(50).get(2)-this.leftSensorPosition[2], 2)) > THRESHOLD_STOP_CONDITION && Math.sqrt(Math.pow(circuite.get(50).get(0)-this.rightSensorPosition[0], 2)+Math.pow(circuite.get(50).get(2)-this.rightSensorPosition[2], 2)) > THRESHOLD_STOP_CONDITION){
            movementController();
            x -= (rightWheel + leftWheel) * (wheelRadius * Math.sin(rot)/2) * DT;
            z -= (rightWheel + leftWheel) * (wheelRadius * Math.cos(rot)/2) * DT;
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
            
            this.time += DT;
            //System.out.println(this.time);
            if(this.time >= TIME_TO_STOP)
                break;
        }
        
        return this.time;
        
    }
    
    private void resetParameters(){
        this.leftWheel = wheelSpeed;
        this.rightWheel = wheelSpeed;
        
        this.x = this.circuite.get(0).get(0);
        this.z = this.circuite.get(0).get(2);
        this.rot = 0.0f;
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
        matrixRotate[0][0] = (float) Math.cos(grades);
        matrixRotate[0][2] = (float) Math.sin(grades);
        matrixRotate[2][0] = (float) -Math.sin(grades);
        matrixRotate[2][2] = (float) Math.cos(grades);
        
        return mulMatrix(matrix, matrixRotate);
        
    }
    
}
