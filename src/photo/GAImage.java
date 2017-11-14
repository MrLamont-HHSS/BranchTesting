/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author lamon
 */
public class GAImage {

    Ellipse[] pixels = new Ellipse[1000];
    BufferedImage img;
    BufferedImage target;
    double fitness = 0;

    public GAImage(BufferedImage target) {
        this.target = target;
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = new Ellipse();
        }
        img = new BufferedImage(target.getWidth(), target.getHeight(), BufferedImage.TYPE_INT_ARGB);
        calcFitness();
    }

    public GAImage breed(GAImage parent2) {
        int crosspoint = (int) (Math.random() * pixels.length);
        GAImage temp = new GAImage(target);
        for (int i = 0; i < pixels.length; i++) {
            if (i < crosspoint) {
                pixels[i] = this.pixels[i].copy();
            } else {
                pixels[i] = parent2.pixels[i].copy();
            }
        }
        temp.calcFitness();
        return temp;
    }

    public void mutate(double rate, int maxMutations) {
        int count = 0;
        for (Ellipse pixel : pixels) {
            double rand = Math.random();
            if (rand < rate) {
                pixel.mutate();
                count++;
            }
            if(count == maxMutations) break;
        }
        calcFitness();
    }

    public BufferedImage getImage() {
        Graphics g = img.createGraphics();
        g.clearRect(0, 0, img.getWidth(), img.getHeight());
        for (Ellipse pixel : pixels) {
            pixel.draw(g, img.getWidth(), img.getHeight());
        }
        return img;
    }

    private void calcFitness() {
        double value = 0;
        getImage();
        for (int x = 0; x < target.getWidth(); x++) {
            for (int y = 0; y < target.getHeight(); y++) {
                // get colour values for Targets
                double bT = (target.getRGB(x, y) & 0xFF)/255.0;
                double gT = (target.getRGB(x, y) >> 8 & 0xFF)/255.0;
                double rT = (target.getRGB(x, y) >> 16 & 0xFF)/255.0;
                double aT = (target.getRGB(x, y) >> 24 & 0xFF)/255.0;

                // get colour values for Image
                double bI = (img.getRGB(x, y) & 0xFF)/255.0;
                double gI = (img.getRGB(x, y) >> 8 & 0xFF)/255.0;
                double rI = (img.getRGB(x, y) >> 16 & 0xFF)/255.0;
                double aI = (img.getRGB(x, y) >> 24 & 0xFF)/255.0;

                double dist = (bT - bI) * (bT - bI) + (gT - gI) * (gT - gI)
                        + (rT - rI) * (rT - rI) + (aT - aI) * (aT - aI);
                value += dist;
            }
        }
        fitness = value;
        System.out.println("done");
    }
    
    public double getFitness(){
        return fitness;
    }

}
