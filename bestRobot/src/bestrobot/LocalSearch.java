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
    
    public float valueChange = 0.1f;
    
    private Robot initialSolution;
    private List<Robot> neighbors;
    private ObjectiveFunction objectiveFunction;
    private List<Float> minParam = new ArrayList<>();
    private List<Float> maxParam = new ArrayList<>();
    
    public LocalSearch(Robot solutionIn) throws IOException{
        valueChange = BestRobot.neighbordChangeVal;
        this.initialSolution = solutionIn;
        this.objectiveFunction = new ObjectiveFunction(BestRobot.robotSpeed*2*(float)Math.PI, BestRobot.circuitePath);
        minParam.add(RandomConstructor.MIN_WHEEL_SEPARATION);
        minParam.add(RandomConstructor.MIN_WHEEL_RADIUS);
        minParam.add(RandomConstructor.MIN_DISTANCE_TO_AXIS);
        minParam.add(RandomConstructor.MIN_SENSOR_SEPARATION);
        
        maxParam.add(RandomConstructor.MAX_WHEEL_SEPARATION);
        maxParam.add(RandomConstructor.MAX_WHEEL_RADIUS);
        maxParam.add(RandomConstructor.MAX_DISTANCE_TO_AXIS);
        maxParam.add(RandomConstructor.MAX_SENSOR_SEPARATION);
    }
    
    public Robot bestTime(){
    
        searchNeighbors();
        int best;
        if(BestRobot.typeOfAlgorithm==0){
            best = neighborsTimeBest();
        }else{
            best = neighborsTimeFirst();
        }
        if(best == -1)
            return this.initialSolution;
        else
            return this.neighbors.get(best);
        
    }
    
    private void searchNeighbors(){
        neighbors = new ArrayList();
        for (int i = 0; i < initialSolution.getParameters().size(); i++) {
            int j =(i==initialSolution.getParameters().size()-1)? 0:i+1;
            int k =(j==initialSolution.getParameters().size()-1)? 0:j+1;;
            SearchParametersAlternate(i, j, k, valueChange);
        }
        for (int i = 0; i < initialSolution.getParameters().size(); i++) {
            int j =(i==initialSolution.getParameters().size()-1)? 0:i+1;
            int k =(j==initialSolution.getParameters().size()-1)? 0:j+1;;
            SearchParametersAlternate(i, j, k, -valueChange);
        }
        for (int i = 0; i < initialSolution.getParameters().size(); i++) {
            int j =(i==initialSolution.getParameters().size()-1)? 0:i+1;
            int k =(j==initialSolution.getParameters().size()-1)? 0:j+1;;
            SearchParametersAlternate(j, i, k, valueChange);
        }
        for (int i = 0; i < initialSolution.getParameters().size(); i++) {
            int j =(i==initialSolution.getParameters().size()-1)? 0:i+1;
            int k =(j==initialSolution.getParameters().size()-1)? 0:j+1;;
            SearchParametersAlternate(j, i, k, -valueChange);
        }
        for (int i = 0; i < initialSolution.getParameters().size(); i++) {
            int j =(i==initialSolution.getParameters().size()-1)? 0:i+1;
            int k =(j==initialSolution.getParameters().size()-1)? 0:j+1;;
            SearchParametersAlternate(k, j, i, valueChange);
        }
        for (int i = 0; i < initialSolution.getParameters().size(); i++) {
            int j =(i==initialSolution.getParameters().size()-1)? 0:i+1;
            int k =(j==initialSolution.getParameters().size()-1)? 0:j+1;;
            SearchParametersAlternate(k, j, i, -valueChange);
        }
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
    private void SearchParameters(int parameter, float addicion){
                List<Float> newNeighbourParameters = new ArrayList<>();
                for (int j = 0; j < initialSolution.getParameters().size(); j++) {
                    if(j==parameter && initialSolution.getParameters().get(j)<maxParam.get(j) && initialSolution.getParameters().get(j)>minParam.get(j)){
                        newNeighbourParameters.add(initialSolution.getParameters().get(j)+addicion);
                    }else{
                        newNeighbourParameters.add(initialSolution.getParameters().get(j));
                    }
                }
                neighbors.add(new Robot(newNeighbourParameters));
    }
    private void SearchParameters(int parameter1,int parameter2, float addicion){
                List<Float> newNeighbourParameters = new ArrayList<>();
                for (int j = 0; j < initialSolution.getParameters().size(); j++) {
                    if((parameter1==j || parameter2 == j) && initialSolution.getParameters().get(j)<maxParam.get(j) && initialSolution.getParameters().get(j)>minParam.get(j)){
                        newNeighbourParameters.add(initialSolution.getParameters().get(j)+addicion);
                    }else{
                        newNeighbourParameters.add(initialSolution.getParameters().get(j));
                    }
                }
                neighbors.add(new Robot(newNeighbourParameters));
    }
    private void SearchParameters(int parameter1,int parameter2,int parameter3, float addicion){
                List<Float> newNeighbourParameters = new ArrayList<>();
                for (int j = 0; j < initialSolution.getParameters().size(); j++) {
                    if((parameter1==j || parameter2 == j || parameter3 ==j) && initialSolution.getParameters().get(j)<maxParam.get(j) && initialSolution.getParameters().get(j)>minParam.get(j)){
                        newNeighbourParameters.add(initialSolution.getParameters().get(j)+addicion);
                    }else{
                        newNeighbourParameters.add(initialSolution.getParameters().get(j));
                    }
                }
                neighbors.add(new Robot(newNeighbourParameters));
    }
    private void SearchParameters(int parameter1,int parameter2,int parameter3,int parameter4, float addicion){
                List<Float> newNeighbourParameters = new ArrayList<>();
                for (int j = 0; j < initialSolution.getParameters().size(); j++) {
                    if((parameter1==j || parameter2 == j || parameter3 ==j || parameter4 == j) && initialSolution.getParameters().get(j)<maxParam.get(j) && initialSolution.getParameters().get(j)>minParam.get(j)){
                        newNeighbourParameters.add(initialSolution.getParameters().get(j)+addicion);
                    }else{
                        newNeighbourParameters.add(initialSolution.getParameters().get(j));
                    }
                }
                neighbors.add(new Robot(newNeighbourParameters));
    }
    
    private void SearchParametersAlternate(int parameter1,int parameter2, float addicion){
                List<Float> newNeighbourParameters = new ArrayList<>();
                for (int j = 0; j < initialSolution.getParameters().size(); j++) {
                    if(parameter1==j && initialSolution.getParameters().get(j)<maxParam.get(j) && initialSolution.getParameters().get(j)>minParam.get(j)){
                        newNeighbourParameters.add(initialSolution.getParameters().get(j)+addicion);
                    }else if(parameter2 == j && initialSolution.getParameters().get(j)<maxParam.get(j) && initialSolution.getParameters().get(j)>minParam.get(j)){
                        newNeighbourParameters.add(initialSolution.getParameters().get(j)-addicion);
                    }else{
                        newNeighbourParameters.add(initialSolution.getParameters().get(j));
                    }
                }
                neighbors.add(new Robot(newNeighbourParameters));
    }
    private void SearchParametersAlternate(int parameter1,int parameter2,int parameter3, float addicion){
                List<Float> newNeighbourParameters = new ArrayList<>();
                for (int j = 0; j < initialSolution.getParameters().size(); j++) {
                    if(parameter1==j && initialSolution.getParameters().get(j)<maxParam.get(j) && initialSolution.getParameters().get(j)>minParam.get(j)){
                        newNeighbourParameters.add(initialSolution.getParameters().get(j)-addicion);
                    }else if((parameter2 == j ||parameter3 == j) && initialSolution.getParameters().get(j)<maxParam.get(j) && initialSolution.getParameters().get(j)>minParam.get(j)){
                        newNeighbourParameters.add(initialSolution.getParameters().get(j)+addicion);
                    }else {
                        newNeighbourParameters.add(initialSolution.getParameters().get(j));
                    }
                }
                neighbors.add(new Robot(newNeighbourParameters));
    }
    private void SearchParametersAlternate(int parameter1,int parameter2,int parameter3,int parameter4, float addicion){
                List<Float> newNeighbourParameters = new ArrayList<>();
                for (int j = 0; j < initialSolution.getParameters().size(); j++) {
                    if(parameter1==j && initialSolution.getParameters().get(j)<maxParam.get(j) && initialSolution.getParameters().get(j)>minParam.get(j)){
                        newNeighbourParameters.add(initialSolution.getParameters().get(j)-addicion);
                    }else if(( parameter2 == j || parameter3 ==j || parameter4 == j) && initialSolution.getParameters().get(j)<maxParam.get(j) && initialSolution.getParameters().get(j)>minParam.get(j)){
                        newNeighbourParameters.add(initialSolution.getParameters().get(j)+addicion);
                    }else{
                        newNeighbourParameters.add(initialSolution.getParameters().get(j));
                    }
                }
                neighbors.add(new Robot(newNeighbourParameters));
    }
    private int neighborsTimeBest(){
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
    private int neighborsTimeFirst(){
        int best = 0;
        for (int i = 0; i < neighbors.size(); i++) {
            float time = objectiveFunction.race(this.neighbors.get(i).getParameters().get(0), this.neighbors.get(i).getParameters().get(1), this.neighbors.get(i).getParameters().get(2), this.neighbors.get(i).getParameters().get(3));
            this.neighbors.get(i).setTime(time);
            if(this.initialSolution.getTime()>this.neighbors.get(i).getTime()){
                best = i;
                break;
            }
        }
        if(this.initialSolution.getTime()<this.neighbors.get(best).getTime())
            best = -1;
        
        return best;
    }
}
