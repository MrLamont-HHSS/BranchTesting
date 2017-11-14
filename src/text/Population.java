/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text;

import java.util.ArrayList;

/**
 *
 * @author lamon
 */
public class Population {

    private double mutationRate;
    private DNA[] population;
    private String target;
    private String bestSoFar = "";
    private double avgFitness = 0;
    private double cutoff = 0.5;

    public Population(String target, double mutationRate, double cutoff, int popSize) {
        this.target = target;
        this.mutationRate = mutationRate;
        this.cutoff = cutoff;
        population = new DNA[popSize];
        for (int i = 0; i < population.length; i++) {
            population[i] = new DNA(target.length());
        }
    }

    public int randInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public String[] getList() {
        String[] list = new String[population.length];
        for (int i = 0; i < population.length; i++) {
            list[i] = population[i].toString();
        }
        return list;
    }

    public void calcFitness() {
        int best = 0;
        double bestFit = 0;
        double totalFitness = 0;
        for (int i = 0; i < population.length; i++) {
            double fitness = population[i].calcFitness(target);
            totalFitness += fitness;
            if (fitness > bestFit) {
                best = i;
                bestFit = fitness;
            }
        }
        avgFitness = totalFitness / population.length;
        bestSoFar = population[best].toString();
    }

    public boolean isSolved() {
        return target.equals(bestSoFar);
    }

    public String getBestSoFar() {
        return this.bestSoFar;
    }

    public double getAverageFitness() {
        return this.avgFitness;
    }

    private DNA acceptReject(double maxFitness, DNA[] parents) {
        double prob = 0;
        DNA parent = null;
        int count = 0;
        do {
            prob = Math.random() * maxFitness;
            parent = parents[randInt(0, parents.length - 1)];
            if (prob <= parent.fitness) {
                return parent;
            } 
            count++;
        } while (count < 10000);
        // took to long, just go with the last selected parent.
        return parent;
    }

    // breeding algorithm
    public void crossover() {

        // determine the top performing individuals
        DNA[] top = topPerform();

        double maxFitness = top[0].getFitness();

        // do a crossing breeding of top performers
        for (int i = 0; i < population.length; i++) {
            DNA parent1 = acceptReject(maxFitness, top);
            DNA parent2 = acceptReject(maxFitness, top);
            population[i] = parent1.crossover(parent2);
        }

    }

    public void mutate() {
        for (int i = 0; i < population.length; i++) {
            population[i].mutate(mutationRate);
        }
    }

    private boolean checkInsert(DNA[] list, DNA value) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                list[i] = value;
                return true;
                // find the spot to put it
            } else if (value.getFitness() > list[i].getFitness()) {
                // shuffle down
                for (int j = list.length - 1; j > i; j--) {
                    list[j] = list[j - 1];
                }
                // insert value
                list[i] = value;
                return true;
            }
        }
        return false;
    }

    private DNA[] topPerform() {
        DNA[] top = new DNA[(int) (population.length * cutoff)];

        // go through population looking for good cadidates
        for (int i = 0; i < population.length; i++) {
            checkInsert(top, population[i]);
        }

        return top;
    }

}
