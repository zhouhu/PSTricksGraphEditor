/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pstricks.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

/**
 *
 * @author zhouhu
 */
public class Grid {
        
    private GraphCanvas canvas;
    public boolean gridOn = true;
    public Grid(GraphCanvas canvas) {
        this.canvas = canvas;
    }
    
    public void paint(Graphics g, double zoom){
        if(!gridOn) return;
        Graphics2D g2 = (Graphics2D) g;
        double i, j;
        double width = canvas.getSize().getWidth(); //+ canvas.getSize().getWidth() * (zoom);
        double height = canvas.getSize().getHeight(); //+ canvas.getSize().getHeight()*(zoom);
        g2.setColor(new Color(200,255,255));
        g2.setStroke(new BasicStroke(0, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
        
        //Horizontal lines
        for(i = 10; i < width; i+=50){
            for(j=i; j<i+50-5; j+=10){
                g2.draw(new Line2D.Double(j,0,j,height));
            }
        }
        
        //Vertical lines
        for(i = 10; i<height; i+=50){
            for(j=i; j<i+50-5; j+=10){
                g2.draw(new Line2D.Double(0,j,width,j));
            }
        }
        
        for(i=-1; i<width; i+=50){
            g2.draw(new Line2D.Double(i,0,i,height));
        }
        
        for(j=-1; j<height; j+=50){
            g2.draw(new Line2D.Double(0,j,width,j));
        }
        
    }
    
    
}
