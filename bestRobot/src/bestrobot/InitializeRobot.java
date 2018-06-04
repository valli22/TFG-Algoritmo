/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bestrobot;

import java.io.IOException;

/**
 *
 * @author David
 */
public class InitializeRobot {
    
    public InitializeRobot(){
    }
    
    public Robot firstRobot() throws IOException{
        Robot newRobot;
        do{
        RandomConstructor constructor = new RandomConstructor();
        newRobot = constructor.produceNumbers();
        ObjectiveFunction obj = new ObjectiveFunction(BestRobot.robotSpeed*2*(float)Math.PI, BestRobot.circuitePath);
        newRobot.setTime(obj.race(newRobot.getParameters().get(0), newRobot.getParameters().get(1), newRobot.getParameters().get(2), newRobot.getParameters().get(3)));
        }while(newRobot.getTime()<0.1 || newRobot.getTime()>69);
        return newRobot;
    }
    
}
