/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bestrobot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author David
 */
public class BestRobot {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        
        Robot newRobot = new InitializeRobot().firstRobot();
        Robot bestRobotATM = null;
        
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
        
        System.out.println(newRobot.getTime());
        int round = 0;
        while(!newRobot.equals(bestRobotATM)){
            bestRobotATM = newRobot;
            LocalSearch lc = new LocalSearch(bestRobotATM);
            newRobot = lc.bestTime();
            System.out.println(round+" = "+newRobot.getTime());
            round++;
        }
    }   
}
