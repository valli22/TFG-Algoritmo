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
    private List<Float> minParam = new ArrayList<>();
    private List<Float> maxParam = new ArrayList<>();
    
    public LocalSearch(Robot solutionIn) throws IOException{
        this.initialSolution = solutionIn;
        this.objectiveFunction = new ObjectiveFunction(2*2*(float)Math.PI, BestRobot.circuitePath);
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
        int best = neighborsTime();
        if(best == -1)
            return this.initialSolution;
        else
            return this.neighbors.get(best);
        
    }
    
    private void searchNeighbors(){
        neighbors = new ArrayList();
        switch(BestRobot.typeOfSearch){
            case 0:
                for (int i = 0; i < initialSolution.getParameters().size(); i++) {
                    SearchParameters(i, 0.1f);
                }for (int i = 0; i < initialSolution.getParameters().size(); i++) {
                    SearchParameters(i, -0.1f);
                }
                break;
                
            case 1:
                
                for (int i = 0; i < initialSolution.getParameters().size(); i++) {
                    int j =(i==initialSolution.getParameters().size()-1)? 0:i+1; 
                    SearchParameters(i, j, 0.1f);
                }
                for (int i = 0; i < initialSolution.getParameters().size(); i++) {
                    int j =(i==initialSolution.getParameters().size()-1)? 0:i+1; 
                    SearchParameters(i, j, -0.1f);
                }
                SearchParameters(0, 2, 0.1f);
                SearchParameters(0, 2, -0.1f);
                
                SearchParameters(1, 3, 0.1f);
                SearchParameters(1, 3, -0.1f);
                
                break;
            
            case 2:
                for (int i = 0; i < initialSolution.getParameters().size(); i++) {
                    int j =(i==initialSolution.getParameters().size()-1)? 0:i+1;
                    int k =(j==initialSolution.getParameters().size()-1)? 0:j+1;;
                    SearchParameters(i, j, k, 0.1f);
                }
                for (int i = 0; i < initialSolution.getParameters().size(); i++) {
                    int j =(i==initialSolution.getParameters().size()-1)? 0:i+1;
                    int k =(j==initialSolution.getParameters().size()-1)? 0:j+1;;
                    SearchParameters(i, j, k, -0.1f);
                }
                break;
            case 3:
                SearchParameters(0, 1, 2, 3, 0.1f);
                
                SearchParameters(0, 1, 2, 3, -0.1f);
                break;
            
            case 4:
                for (int i = 0; i < initialSolution.getParameters().size(); i++) {
                    int j =(i==initialSolution.getParameters().size()-1)? 0:i+1; 
                    SearchParametersAlternate(i, j, 0.1f);
                }
                for (int i = 0; i < initialSolution.getParameters().size(); i++) {
                    int j =(i==initialSolution.getParameters().size()-1)? 0:i+1; 
                    SearchParametersAlternate(j, i, 0.1f);
                }
                SearchParametersAlternate(0, 2, 0.1f);
                SearchParametersAlternate(2, 0, 0.1f);
                
                SearchParametersAlternate(1, 3, 0.1f);
                SearchParametersAlternate(3, 1, 0.1f);
                break;
            case 5:
                for (int i = 0; i < initialSolution.getParameters().size(); i++) {
                    int j =(i==initialSolution.getParameters().size()-1)? 0:i+1;
                    int k =(j==initialSolution.getParameters().size()-1)? 0:j+1;;
                    SearchParametersAlternate(i, j, k, 0.1f);
                }
                for (int i = 0; i < initialSolution.getParameters().size(); i++) {
                    int j =(i==initialSolution.getParameters().size()-1)? 0:i+1;
                    int k =(j==initialSolution.getParameters().size()-1)? 0:j+1;;
                    SearchParametersAlternate(i, j, k, -0.1f);
                }
                for (int i = 0; i < initialSolution.getParameters().size(); i++) {
                    int j =(i==initialSolution.getParameters().size()-1)? 0:i+1;
                    int k =(j==initialSolution.getParameters().size()-1)? 0:j+1;;
                    SearchParametersAlternate(j, i, k, 0.1f);
                }
                for (int i = 0; i < initialSolution.getParameters().size(); i++) {
                    int j =(i==initialSolution.getParameters().size()-1)? 0:i+1;
                    int k =(j==initialSolution.getParameters().size()-1)? 0:j+1;;
                    SearchParametersAlternate(j, i, k, -0.1f);
                }
                for (int i = 0; i < initialSolution.getParameters().size(); i++) {
                    int j =(i==initialSolution.getParameters().size()-1)? 0:i+1;
                    int k =(j==initialSolution.getParameters().size()-1)? 0:j+1;;
                    SearchParametersAlternate(k, j, i, 0.1f);
                }
                for (int i = 0; i < initialSolution.getParameters().size(); i++) {
                    int j =(i==initialSolution.getParameters().size()-1)? 0:i+1;
                    int k =(j==initialSolution.getParameters().size()-1)? 0:j+1;;
                    SearchParametersAlternate(k, j, i, -0.1f);
                }
                break;
            case 6:
                SearchParametersAlternate(0, 1, 2, 3, 0.1f);
                SearchParametersAlternate(0, 1, 2, 3, -0.1f);
                
                SearchParametersAlternate(1, 0, 2, 3, 0.1f);
                SearchParametersAlternate(1, 0, 2, 3, -0.1f);
                
                SearchParametersAlternate(2, 1, 0, 3, 0.1f);
                SearchParametersAlternate(2, 1, 0, 3, -0.1f);
                
                SearchParametersAlternate(3, 1, 2, 0, 0.1f);
                SearchParametersAlternate(3, 1, 2, 0, -0.1f);
                
                break;
            default: System.err.println("Incorrect type of search");
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
