/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author lamon
 */
public class ImagePanel extends JPanel{
    private BufferedImage img = null;
    
    @Override
    public void paintComponent(Graphics g){
        g.clearRect(0, 0, getWidth(), getHeight());
        if(img != null){
           int imgW = img.getWidth();
           int imgH = img.getHeight();
           
           // get ratio
           double ri = (1.0*imgW)/(1.0*imgH);
           double rs = (1.0*getWidth())/(1.0*getHeight());
           // scale image to look correct on screen
           if(ri < rs){
               g.drawImage(img, getWidth()/2 - (imgW * getHeight()/imgH)/2, 0, imgW * getHeight()/imgH, getHeight(), null); 
           }else{
               g.drawImage(img, 0, getHeight()/2 - (imgH * getWidth()/imgW)/2, getWidth(), imgH * getWidth()/imgW, null);
           }
           
        }
    }
    
    public void setImage(BufferedImage img){
        this.img = img;
        repaint();
    }
    
    public BufferedImage getImage(){
        return img;
    }
}
