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
public class DNA {

    char[] dna;
    double fitness = 0;
    
    public DNA(int length) {
        dna = new char[length];
        for(int i = 0; i < length; i++){
            dna[i] = (char)(randInt(32,126));
        }
        
    }
    
    public int randInt(int min, int max){
        return (int)(Math.random()*(max-min+1) + min);
    }

    double calcFitness(String target) {
        double fitness = 0;
        for(int i = 0; i < dna.length; i++){
            if(dna[i] == target.charAt(i)){
                fitness++;
            }
        }
        this.fitness = fitness;
        return Math.pow(fitness/(double)dna.length,1);
    }
    
    public String toString(){
        return String.valueOf(dna, 0, dna.length);
    }
    
    public double getFitness(){
        return this.fitness;
    }
    
    public DNA crossover(DNA otherParent){
        int midpoint = (int)(Math.random()*dna.length);
        DNA child = new DNA(dna.length);
        for(int i = 0; i < dna.length; i++){
            if(i < midpoint){
                child.dna[i] = this.dna[i];
            }else{
                child.dna[i] = otherParent.dna[i];
            }
        }
        return child;
    }
    
    public void mutate(double rate){
        for(int i = 0; i < dna.length; i++){
            double rand = Math.random();
            if(rand <= rate){
                dna[i] = (char)(randInt(32,126)); 
            }
        }
    }
    
}
