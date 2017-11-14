/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photo;

import java.awt.image.BufferedImage;

/**
 *
 * @author lamon
 */
public class Population {
    private GAImage[] images;
    private int maxFitness = 0;
    private BufferedImage bestImage;
    private BufferedImage target;
    private double mutationRate;
    private int maxMutations;
    private double cutoff;
    private long generation = 0;
    private double avgFitness = 0;
    private double bestFitness = Double.MAX_VALUE;
    
    
    public Population(BufferedImage target, double mutationRate, int maxMuts, double cutoff, int popSize) {
        this.target = target;
        this.mutationRate = mutationRate;
        this.maxMutations = maxMuts;
        this.cutoff = cutoff;
        
        images = new GAImage[popSize];
        for(int i = 0; i < images.length; i++){
            images[i] = new GAImage(target); 
        }
        
        calcFitness();
    }
    
    public void mutate(){
        for(GAImage i: images){
            i.mutate(this.mutationRate, this.maxMutations);
        }
    }
    
    private void calcFitness(){
        double bestFit = -1;
        int bestSpot = 0;
        double sum = 0;
        for(int i = 0; i < images.length; i++){
            double fit = images[i].getFitness();
            if(bestFit == -1 || fit < bestFit){
                bestFit = fit;
                bestSpot = i;
            }
            sum += fit;
        }
        this.avgFitness = sum/images.length;
        this.bestFitness = bestFit;
        this.bestImage = this.images[bestSpot].getImage();
    }
    
    public BufferedImage getBest(){
        return this.bestImage;
    }
    
}
