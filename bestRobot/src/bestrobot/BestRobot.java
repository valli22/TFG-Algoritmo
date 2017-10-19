/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bestrobot;

import java.io.IOException;
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
