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
public class LocalSearch {
    
    private Robot initialSolution;
    private List<Robot> neighbors;
    private ObjectiveFunction objectiveFunction;
    
    public LocalSearch(Robot solutionIn) throws IOException{
        this.initialSolution = solutionIn;
        this.objectiveFunction = new ObjectiveFunction(2*2*(float)Math.PI, "F:\\TFG\\Git\\TFG-RobotSiguelineas\\Circuitos\\circuito.txt");
    }
    
    public Robot bestTime(){
    
        searchNeighbors();
        int best = neighborsTime();
        if(best == -1)
            return this.initialSolution;
        else
            return this.neighbors.get(best);
        
    }
    
    private void searchNeighbors(){
        neighbors = new ArrayList();
        List<Float> newNeighbourParameters = new ArrayList<>();
        for (int j = 0; j < initialSolution.getParameters().size(); j++) {
            if(j!=0){
                newNeighbourParameters.add(initialSolution.getParameters().get(j));
            }else{
                newNeighbourParameters.add(initialSolution.getParameters().get(j)+0.1f);
            }
        }
        neighbors.add(new Robot(newNeighbourParameters));
        newNeighbourParameters = new ArrayList<>();
        for (int j = 0; j < initialSolution.getParameters().size(); j++) {
            if(j!=0){
                newNeighbourParameters.add(initialSolution.getParameters().get(j));
            }else{
                newNeighbourParameters.add(initialSolution.getParameters().get(j)-0.1f);
            }
        }
        neighbors.add(new Robot(newNeighbourParameters));
        
        newNeighbourParameters = new ArrayList<>();
        for (int j = 0; j < initialSolution.getParameters().size(); j++) {
            if(j!=1){
                newNeighbourParameters.add(initialSolution.getParameters().get(j));
            }else{
                newNeighbourParameters.add(initialSolution.getParameters().get(j)+0.1f);
            }
        }
        neighbors.add(new Robot(newNeighbourParameters));
        newNeighbourParameters = new ArrayList<>();
        for (int j = 0; j < initialSolution.getParameters().size(); j++) {
            if(j!=1){
                newNeighbourParameters.add(initialSolution.getParameters().get(j));
            }else{
                newNeighbourParameters.add(initialSolution.getParameters().get(j)-0.1f);
            }
        }
        neighbors.add(new Robot(newNeighbourParameters));
        
        newNeighbourParameters = new ArrayList<>();
        for (int j = 0; j < initialSolution.getParameters().size(); j++) {
            if(j!=2){
                newNeighbourParameters.add(initialSolution.getParameters().get(j));
            }else{
                newNeighbourParameters.add(initialSolution.getParameters().get(j)+0.1f);
            }
        }
        neighbors.add(new Robot(newNeighbourParameters));
        newNeighbourParameters = new ArrayList<>();
        for (int j = 0; j < initialSolution.getParameters().size(); j++) {
            if(j!=2){
                newNeighbourParameters.add(initialSolution.getParameters().get(j));
            }else{
                newNeighbourParameters.add(initialSolution.getParameters().get(j)-0.1f);
            }
        }
        neighbors.add(new Robot(newNeighbourParameters));
        
        newNeighbourParameters = new ArrayList<>();
        for (int j = 0; j < initialSolution.getParameters().size(); j++) {
            if(j!=2){
                newNeighbourParameters.add(initialSolution.getParameters().get(j));
            }else{
                newNeighbourParameters.add(initialSolution.getParameters().get(j)+0.1f);
            }
        }
        neighbors.add(new Robot(newNeighbourParameters));
        newNeighbourParameters = new ArrayList<>();
        for (int j = 0; j < initialSolution.getParameters().size(); j++) {
            if(j!=2){
                newNeighbourParameters.add(initialSolution.getParameters().get(j));
            }else{
                newNeighbourParameters.add(initialSolution.getParameters().get(j)-0.1f);
            }
        }
        neighbors.add(new Robot(newNeighbourParameters));
        
        newNeighbourParameters = new ArrayList<>();
        for (int j = 0; j < initialSolution.getParameters().size(); j++) {
            if(j!=3){
                newNeighbourParameters.add(initialSolution.getParameters().get(j));
            }else{
                newNeighbourParameters.add(initialSolution.getParameters().get(j)+0.1f);
            }
        }
        neighbors.add(new Robot(newNeighbourParameters));
        newNeighbourParameters = new ArrayList<>();
        for (int j = 0; j < initialSolution.getParameters().size(); j++) {
            if(j!=3){
                newNeighbourParameters.add(initialSolution.getParameters().get(j));
            }else{
                newNeighbourParameters.add(initialSolution.getParameters().get(j)-0.1f);
            }
        }
        neighbors.add(new Robot(newNeighbourParameters));
        
        /*
        for (int i = 0; i < inicialSolution.getParameters().size(); i++) {
            List<Float> newNeighbourParameters = new ArrayList<>();
            for (int j = 0; j < inicialSolution.getParameters().size(); j++) {
                if(i!=j){
                    newNeighbourParameters.add(inicialSolution.getParameters().get(j));
                }else{
                    newNeighbourParameters.add(inicialSolution.getParameters().get(j)+0.1f);
                }
            }
            neighbors.get(0).add(new Neighbour(newNeighbourParameters));
        }
        neighbors.add(new ArrayList<>());
        for (int i = 0; i < inicialSolution.getParameters().size(); i++) {
            List<Float> newNeighbourParameters = new ArrayList<>();
            for (int j = 0; j < inicialSolution.getParameters().size(); j++) {
                if(i==j || i+1==j || (j==0 && i==inicialSolution.getParameters().size()-1)){
                    newNeighbourParameters.add(inicialSolution.getParameters().get(j)+0.1f);
                }else{
                    newNeighbourParameters.add(inicialSolution.getParameters().get(j));
                }
            }
            neighbors.get(1).add(new Neighbour(newNeighbourParameters));
        }
        neighbors.add(new ArrayList<>());
        for (int i = 0; i < inicialSolution.getParameters().size(); i++) {
            List<Float> newNeighbourParameters = new ArrayList<>();
            for (int j = 0; j < inicialSolution.getParameters().size(); j++) {
                if(i==j || i+1==j || i+2==j || (j==0 && i==inicialSolution.getParameters().size()-1)){
                    newNeighbourParameters.add(inicialSolution.getParameters().get(j)+0.1f);
                }else{
                    newNeighbourParameters.add(inicialSolution.getParameters().get(j));
                }
            }
            neighbors.get(2).add(new Neighbour(newNeighbourParameters));
        }
        */
        
        /*
        modificacion de -0.1 a sueltos
        neighbors.add(new ArrayList<>());
        for (int i = 0; i < parameters.size(); i++) {
            List<Float> newNeighbourParameters = new ArrayList<>();
            for (int j = 0; j < parameters.size(); j++) {
                if(i!=j){
                    newNeighbourParameters.add(parameters.get(j));
                }else{
                    newNeighbourParameters.add(parameters.get(j)-0.1f);
                }
            }
            neighbors.get(1).add(new Neighbour(newNeighbourParameters));
        }
        */
    
    }
    
    private int neighborsTime(){
        int best = 0;
        for (int i = 0; i < neighbors.size(); i++) {
            float time = objectiveFunction.race(this.neighbors.get(i).getParameters().get(0), this.neighbors.get(i).getParameters().get(1), this.neighbors.get(i).getParameters().get(2), this.neighbors.get(i).getParameters().get(3));
            this.neighbors.get(i).setTime(time);
            if(this.neighbors.get(best).getTime()>this.neighbors.get(i).getTime())
                best = i;
        }
        if(this.initialSolution.getTime()<this.neighbors.get(best).getTime())
            best = -1;
        
        return best;
    }
}
