/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author lamon
 */
public class Ellipse {
    DNA dna;
    
    public Ellipse(){
        dna = new DNA();
    }
    
    public Ellipse(double[] genes){
        dna = new DNA(genes);
    }
    
    public void draw(Graphics g, int width, int height){
        Color c = new Color((float)dna.getGene(4),(float)dna.getGene(5),(float)dna.getGene(6),(float)dna.getGene(7));
        int x = (int)(width*dna.getGene(0));
        int y = (int)(height*dna.getGene(1));
        int w = (int)(width*dna.getGene(2));
        int h = (int)(height*dna.getGene(3));
        
        g.setColor(c);
        g.fillOval(x, y, w, h);
    }
    
    public void mutate(){
        dna.mutate();
    }
    
    public Ellipse copy(){
        
        double[] genes = new double[8];
        for(int i = 0; i < 8; i++){
            genes[i] = this.dna.getGene(i);
        }
        Ellipse temp = new Ellipse(genes);
        return temp;
    }
}
