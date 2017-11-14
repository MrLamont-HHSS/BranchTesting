/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text;

/**
 *
 * @author lamon
 */
public class GeneticAlgorithm {

   
    private double mutationRate = 0.01;
    private int populationSize = 500;
    private double cutoff = 0.5;
    private String target = "To be or not to be.";
    
    
    public void run(){
        Population pop = new Population(target, mutationRate, cutoff, populationSize);
        
        int generation = 0;
        while(!pop.getBestSoFar().equals(target)){
            pop.calcFitness();
            pop.crossover();
            pop.mutate();
            generation++;
            System.out.printf("Generation: %d    AverageFitness: %.2f%n", generation, pop.getAverageFitness()*100);
            System.out.println("Best so far: " + pop.getBestSoFar());
            System.out.println("");
        }
        System.out.println("DONE!");
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GeneticAlgorithm ga = new GeneticAlgorithm();
        ga.run();
    }
    
}
