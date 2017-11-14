/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photo;

/**
 *
 * @author lamon
 */
public class DNA {

    // x, y, width, height, r, g, b, a
    private double[] dna = new double[8];

    public DNA() {
        // create random DNA
        for (int i = 0; i < dna.length; i++) {
            dna[i] = Math.random();
        }
    }

    public DNA(double[] genes) {
        dna = genes;
        System.out.println("this is a test");
    }

    public double getGene(int i) {
        return dna[i];
    }

    public void mutate() {
        // mutate part of the DNA
        int spot = (int) (Math.random() * 8);
        dna[spot] = Math.random();
    }
}
