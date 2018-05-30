/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bestrobot;

import java.io.IOException;
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
    0 = normal: 1 ejecucion, best
    1 = Multiarranque: 2ejecuciones, best
    */
    private static String prefixName = "";
    public static int typeOfSearch = 0;
    public static String circuitePath = "F:\\TFG - Algoritmo\\TFG-Algoritmo\\circuito.txt";
    public static String resultsPath = "F:\\TFG - Algoritmo\\TFG-Algoritmo\\Results\\";
    public static void main(String[] args) throws IOException{
        
        switch(typeOfAlgorithm){
            case 0:prefixName = "Normal ";
                break;
            case 1: prefixName = "Multiarranque ";
            default:break;
        }
        for (int i = 0; i < 2; i++) {
        
            float startTime = System.nanoTime();
            Robot newRobot = new InitializeRobot().firstRobot();
            Robot bestRobotATM = null;
            IOFile costeTiempoFile = new IOFile(prefixName+"Tiempo Coste it "+i);
            //prueba con robot de parametros conocidos
            /*
            List<Float> param = new ArrayList<>();
            param.add(16f);
            param.add(2f);
            param.add(3f);
            param.add(4f);
            newRobot = new Robot(param);
            ObjectiveFunction obj = new ObjectiveFunction(2*2*(float)Math.PI, "F:\\TFG\\Git\\TFG-RobotSiguelineas\\Circuitos\\circuito.txt");
            newRobot.setTime(obj.race(newRobot.getParameters().get(0), newRobot.getParameters().get(1), newRobot.getParameters().get(2), newRobot.getParameters().get(3)));
            */
            //fin de prueba con robot de parametros conocidos
            costeTiempoFile.WriteLine(String.valueOf((System.nanoTime()-startTime)/1000000000)+" "+String.valueOf(newRobot.getTime()));
            System.out.println(String.valueOf((System.nanoTime()-startTime)/1000000000)+" "+String.valueOf(newRobot.getTime()));
            int round = 0;
            while(!newRobot.equals(bestRobotATM)){
                bestRobotATM = newRobot;
                LocalSearch lc = new LocalSearch(bestRobotATM);
                newRobot = lc.bestTime();
                System.out.println(String.valueOf((System.nanoTime()-startTime)/1000000000)+" "+String.valueOf(newRobot.getTime()));
                costeTiempoFile.WriteLine(String.valueOf((System.nanoTime()-startTime)/1000000000)+" "+String.valueOf(newRobot.getTime()));
                round++;
            }
            costeTiempoFile.CloseFile();
            IOFile finalParametersFile = new IOFile(prefixName+"Parametros Finales it "+i);
            finalParametersFile.WriteLine(String.valueOf(newRobot.getParameters().get(0)));
            finalParametersFile.WriteLine(String.valueOf(newRobot.getParameters().get(1)));
            finalParametersFile.WriteLine(String.valueOf(newRobot.getParameters().get(2)));
            finalParametersFile.WriteLine(String.valueOf(newRobot.getParameters().get(3)));
            finalParametersFile.CloseFile();
            if(typeOfAlgorithm!=1)
                break;
        }
    }   
}
