/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bestrobot;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

/**
 *
 * @author David
 */
public class BestRobot {

    /**
     * @param args the command line arguments
     */
    public static int typeOfAlgorithm = 0;
    /*
    0 = best
    1 = first
    */
    private static int numEjecuciones = 10;
    
    private static float[] neighbordChangeArray = {0.05f,0.1f,0.15f,0.2f};
    public static float neighbordChangeVal;
    
    /*
    Busq{
        1 = best - busqueda local 0.05
        2 = best - busqueda local 0.1 
        3 = best - busqueda local 0.15
        4 = best - busqueda local 0.2
        5 = first - busqueda local 0.05
        6 = first - busqueda local 0.1
        7 = first - busqueda local 0.15
        8 = first - busqueda local 0.2
    }
    
    Inst{
        1 = circuito 1 - speed 2
        2 = circuito 1 - speed 2.5
        3 = circuito 1 - speed 3
        4 = circuito 2 - speed 2
        5 = circuito 2 - speed 2.5
        6 = circuito 2 - speed 3
        7 = circuito 3 - speed 2
        8 = circuito 3 - speed 2.5
        9 = circuito 3 - speed 3
    }
    */
    private static String prefixName = "";
    // valores anteriores 2,3,4
    private static float[] speedArray = {2,2.5f,3};
    public static float robotSpeed;
    /*
    F:\TFG - Algoritmo\TFG-Algoritmo\circuito.txt
    F:\TFG - Algoritmo\TFG-Algoritmo\circuito2Grande.txt
    F:\TFG - Algoritmo\TFG-Algoritmo\circuitoDerechaGrande.txt
    */
    private static String[] pathArray = {"F:\\TFG - Algoritmo\\TFG-Algoritmo\\circuito.txt","F:\\TFG - Algoritmo\\TFG-Algoritmo\\circuito2Grande.txt","F:\\TFG - Algoritmo\\TFG-Algoritmo\\circuitoDerechaGrande.txt"};
    public static String circuitePath;
    public static String resultsPath = "F:\\TFG - Algoritmo\\TFG-Algoritmo\\Results\\";
    public static String resultsPathOptimo = "F:\\TFG - Algoritmo\\TFG-Algoritmo\\ResultsOptimo\\";
    
    public static void main(String[] args) throws IOException{
        /*
        for (int k = 0; k < numEjecuciones; k++) {
            for (int l = 0; l < 2; l++) {
                typeOfAlgorithm = l;
                for (int m = 0; m < neighbordChangeArray.length; m++) {
                    neighbordChangeVal= neighbordChangeArray[m];
                    for (int i = 0; i < pathArray.length; i++) {
                        circuitePath = pathArray[i];
                        for (int j = 0; j < speedArray.length; j++) {
                            robotSpeed = speedArray[j];
                            prefixName = "Busq "+String.valueOf(typeOfAlgorithm*neighbordChangeArray.length+m+1)+"-Inst "+String.valueOf(i*speedArray.length+j+1)+"-it "+String.valueOf(k)+" ";

                            // ejecucion del programa
                            float startTime = System.nanoTime();
                            Robot newRobot = new InitializeRobot().firstRobot();
                            Robot bestRobotATM = null;
                            IOFile costeTiempoFile = new IOFile(prefixName+"Tiempo Coste",0);

                            //fin de prueba con robot de parametros conocidos
                            
                            costeTiempoFile.WriteLine(String.valueOf((System.nanoTime()-startTime)/1000000000)+" "+String.valueOf(newRobot.getTime()));
                            //System.out.println(String.valueOf((System.nanoTime()-startTime)/1000000000)+" "+String.valueOf(newRobot.getTime()));
                            int round = 0;
                            int stuned = 0;
                            while(!newRobot.equals(bestRobotATM) && stuned < 11){
                                bestRobotATM = newRobot;
                                LocalSearch lc = new LocalSearch(bestRobotATM);
                                newRobot = lc.bestTime();
                                //System.out.println(String.valueOf((System.nanoTime()-startTime)/1000000000)+" "+String.valueOf(newRobot.getTime()));
                                costeTiempoFile.WriteLine(String.valueOf((System.nanoTime()-startTime)/1000000000)+" "+String.valueOf(newRobot.getTime()));
                                if(new DecimalFormat("#.###").format(newRobot.getTime()).equals(new DecimalFormat("#.###").format(bestRobotATM.getTime()))){
                                    stuned++;
                                }else{
                                    stuned = 0;
                                }
                                round++;
                            }
                            costeTiempoFile.CloseFile();
                            IOFile finalParametersFile = new IOFile(prefixName+"Parametros Finales",1);
                            finalParametersFile.WriteLine(String.valueOf(newRobot.getParameters().get(0)));
                            finalParametersFile.WriteLine(String.valueOf(newRobot.getParameters().get(1)));
                            finalParametersFile.WriteLine(String.valueOf(newRobot.getParameters().get(2)));
                            finalParametersFile.WriteLine(String.valueOf(newRobot.getParameters().get(3)));
                            finalParametersFile.CloseFile();
                            
                            System.out.println(prefixName);
                        }
                    }
                }
            }        
        }
        */
        
        
        /*
        typeOfAlgorithm = 1;
        neighbordChangeVal= neighbordChangeArray[1];
        circuitePath = pathArray[2];
        robotSpeed = speedArray[0];
        prefixName = "Especial first";
        // ejecucion del programa
        float startTime = System.nanoTime();
        //Robot newRobot = new InitializeRobot().firstRobot();
        Robot bestRobotATM = null;
        IOFile costeTiempoFile = new IOFile(prefixName+"Tiempo Coste",0);
        //prueba con robot de parametros conocidos
        
        List<Float> param = new ArrayList<>();
        param.add(16f);
        param.add(2f);
        param.add(3f);
        param.add(4f);
        Robot newRobot = new Robot(param);
        ObjectiveFunction obj = new ObjectiveFunction(robotSpeed*2*(float)Math.PI, circuitePath);
        newRobot.setTime(obj.race(newRobot.getParameters().get(0), newRobot.getParameters().get(1), newRobot.getParameters().get(2), newRobot.getParameters().get(3)));
        
        //fin de prueba con robot de parametros conocidos
        costeTiempoFile.WriteLine(String.valueOf((System.nanoTime()-startTime)/1000000000)+" "+String.valueOf(newRobot.getTime()));
        //System.out.println(String.valueOf((System.nanoTime()-startTime)/1000000000)+" "+String.valueOf(newRobot.getTime()));
        int round = 0;
        int stuned = 0;
        while(!newRobot.equals(bestRobotATM) && stuned < 11){
            bestRobotATM = newRobot;
            LocalSearch lc = new LocalSearch(bestRobotATM);
            newRobot = lc.bestTime();
            //System.out.println(String.valueOf((System.nanoTime()-startTime)/1000000000)+" "+String.valueOf(newRobot.getTime()));
            costeTiempoFile.WriteLine(String.valueOf((System.nanoTime()-startTime)/1000000000)+" "+String.valueOf(newRobot.getTime()));
            if(new DecimalFormat("#.###").format(newRobot.getTime()).equals(new DecimalFormat("#.###").format(bestRobotATM.getTime()))){
                stuned++;
            }else{
                stuned = 0;
            }
            round++;
        }
        costeTiempoFile.CloseFile();
        IOFile finalParametersFile = new IOFile(prefixName+"Parametros Finales",1);
        finalParametersFile.WriteLine(String.valueOf(newRobot.getParameters().get(0)));
        finalParametersFile.WriteLine(String.valueOf(newRobot.getParameters().get(1)));
        finalParametersFile.WriteLine(String.valueOf(newRobot.getParameters().get(2)));
        finalParametersFile.WriteLine(String.valueOf(newRobot.getParameters().get(3)));
        finalParametersFile.CloseFile();

        System.out.println(prefixName);
        */
        
        
        //ILS
        typeOfAlgorithm = 0;
        neighbordChangeVal= neighbordChangeArray[1];
        circuitePath = pathArray[0];
        robotSpeed = speedArray[0];
        prefixName = "Especial best ILS ";
        // ejecucion del programa
        float startTime = System.nanoTime();
        //Robot newRobot = new InitializeRobot().firstRobot();
        Robot bestRobotATM = null;
        IOFile costeTiempoFile = new IOFile(prefixName+"Tiempo Coste",0);
        //prueba con robot de parametros conocidos
        
        List<Float> param = new ArrayList<>();
        param.add(16f);
        param.add(2f);
        param.add(3f);
        param.add(4f);
        Robot newRobot = new Robot(param);
        ObjectiveFunction obj = new ObjectiveFunction(robotSpeed*2*(float)Math.PI, circuitePath);
        newRobot.setTime(obj.race(newRobot.getParameters().get(0), newRobot.getParameters().get(1), newRobot.getParameters().get(2), newRobot.getParameters().get(3)));
        
        //fin de prueba con robot de parametros conocidos
        costeTiempoFile.WriteLine(String.valueOf((System.nanoTime()-startTime)/1000000000)+" "+String.valueOf(newRobot.getTime()));
        //System.out.println(String.valueOf((System.nanoTime()-startTime)/1000000000)+" "+String.valueOf(newRobot.getTime()));
        int round = 0;
        int stuned = 0;
        while(!newRobot.equals(bestRobotATM) && stuned < 11){
            bestRobotATM = newRobot;
            LocalSearch lc = new LocalSearch(bestRobotATM);
            newRobot = lc.bestTime();
            //System.out.println(String.valueOf((System.nanoTime()-startTime)/1000000000)+" "+String.valueOf(newRobot.getTime()));
            costeTiempoFile.WriteLine(String.valueOf((System.nanoTime()-startTime)/1000000000)+" "+String.valueOf(newRobot.getTime()));
            if(new DecimalFormat("#.###").format(newRobot.getTime()).equals(new DecimalFormat("#.###").format(bestRobotATM.getTime()))){
                stuned++;
            }else{
                stuned = 0;
            }
            round++;
        }
        //Se permuta la solucion actual
        Robot bestWorldRobot = newRobot;
        newRobot.permute();
        obj = new ObjectiveFunction(robotSpeed*2*(float)Math.PI, circuitePath);
        newRobot.setTime(obj.race(newRobot.getParameters().get(0), newRobot.getParameters().get(1), newRobot.getParameters().get(2), newRobot.getParameters().get(3)));
        costeTiempoFile.WriteLine(String.valueOf((System.nanoTime()-startTime)/1000000000)+" "+String.valueOf(newRobot.getTime()));
        
        bestRobotATM = null;
        round = 0;
        stuned = 0;
        while(!newRobot.equals(bestRobotATM) && stuned < 11){
            bestRobotATM = newRobot;
            LocalSearch lc = new LocalSearch(bestRobotATM);
            newRobot = lc.bestTime();
            //System.out.println(String.valueOf((System.nanoTime()-startTime)/1000000000)+" "+String.valueOf(newRobot.getTime()));
            costeTiempoFile.WriteLine(String.valueOf((System.nanoTime()-startTime)/1000000000)+" "+String.valueOf(newRobot.getTime()));
            if(new DecimalFormat("#.###").format(newRobot.getTime()).equals(new DecimalFormat("#.###").format(bestRobotATM.getTime()))){
                stuned++;
            }else{
                stuned = 0;
            }
            round++;
        }
        
        if(bestWorldRobot.getTime()<newRobot.getTime())
            newRobot = bestWorldRobot;
        
        costeTiempoFile.CloseFile();
        IOFile finalParametersFile = new IOFile(prefixName+"Parametros Finales",1);
        finalParametersFile.WriteLine(String.valueOf(newRobot.getParameters().get(0)));
        finalParametersFile.WriteLine(String.valueOf(newRobot.getParameters().get(1)));
        finalParametersFile.WriteLine(String.valueOf(newRobot.getParameters().get(2)));
        finalParametersFile.WriteLine(String.valueOf(newRobot.getParameters().get(3)));
        finalParametersFile.CloseFile();

        System.out.println(prefixName);
        
    }
        
        
}
