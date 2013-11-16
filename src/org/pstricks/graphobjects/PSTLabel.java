/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pstricks.graphobjects;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 *
 * @author zhouhu
 */
public interface PSTLabel extends Serializable, Cloneable{

    public Rectangle2D getBounds();

    public void position(double d, double d0);

    public boolean contains(Point2D p);

    public void draw(Graphics2D g2);

    public String getCode();
    
    public String getText();
    
    public void setText(String text);
    
    public double getCentreX();
    
    public double getCentreY();
    
    /**
     * @param comments the comments to set
     */
    public void setComments(String comments);
    
    /**
     * @return the comments
     */
    public String getComments();
    
    public Object clone();
    
}
