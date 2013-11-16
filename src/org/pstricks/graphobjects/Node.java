/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pstricks.graphobjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 *
 * @author zhouhu
 */
public interface Node extends Serializable, Cloneable {
    
    
    /**
     * Draw the node (DotNode, CircleNode, FrameNode...)
     * @param g2 
     */
    public void draw(Graphics2D g2);
    
    /**
     * Whether the node contains the given point.
     * @param Point2D point
     * @return True Contains / False Not
     */
    public boolean contains(Point2D point);
    
    /**
     * Move the node to new a position
     * Add node at a position
     * @param dx
     * @param dy 
     */
    public void position(float dx, float dy);
    
    /**
     * Get the connecting point of the node
     * @param point
     * @return connecting point 
     */
    public Point2D getConnectionPoint(Point2D point);
    
    /**
     * Get the smallest rectangle that bounding the node and label
     * @return Rectangle2D
     */
    public Rectangle2D getBounds();
    
    /**
     * Get color string value (black, red ... PSTricks set colors)
     * @return 
     */
    public Color getNodeColour();
    
    /**
     * Set colour value
     */
    public void setNodeColour(Color colour);
    
    /**
     * Get the string value of node colour (black, red, ...)
     * @return 
     */
    public String getNodeColourString();
    
    /**
     * Get node size
     */
    public float getSize();
    
    /**
     * Set node size
     */
    public void setSize(float size);
    
    /**
     * Get node frame left upper conner position X
     * @return X
     */
    public float getPositionX();
    
    /**
     * Get node frame left upper conner position Y
     * @return Y
     */
    public float getPositionY();

    /**
     * Set node frame left upper conner position X
     */
    public void setPositionX(float x);
    
    /**
     * Set node frame left upper conner position Y 
     */
    public void setPositionY(float y);
    
    /**
     * Node centre position x
     * @return 
     */
    public float getCentreX();
    
    /**
     * Node centre position y
     * @return 
     */
    public float getCentreY();
    
    /**
     * Get PSTricks codes
     * @return 
     */
    public String getCode();
    
    
    /**
     * @return the nodeID
     */
    public String getNodeID();

    /**
     * @param nodeID the nodeID to set
     */
    public void setNodeID(String nodeID);
    
    /**
     * @return the comments
     */
    public String getComments();
    
    /**
     * @param comments the comments to set
     */
    public void setComments(String comments);
    
    /**
     * @return the label 
     */
    public String getLabel();
    
    /**
     * Set the label
     * @param label 
     */
    public void setLabel(String label);
    
    /**
     * @return the labelPosition
     */
    public String getLabelPosition();

    /**
     * @param labelPosition the labelPosition to set
     */
    public void setLabelPosition(String labelPosition);
    
    public Object clone();
}
