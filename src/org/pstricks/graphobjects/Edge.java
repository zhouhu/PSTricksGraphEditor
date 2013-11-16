package org.pstricks.graphobjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 * Edge Interface
 * @author zhouhu
 */
public interface Edge extends Serializable, Cloneable {
   
    /**
     * Draw the edge. (Line, Arc and Circle)
     * @param Graphics2D g2
     */
    public void draw(Graphics2D g2);

    /**
     * @return the lineStroke
     */
    public Stroke getLineStroke();

    /**
     * Whether the edge contains the given point.
     * @param Point2D point
     * @return true: edge contains point, otherwise false;
     */
    public boolean contains(Point2D point);

    /**
     * Connects the edge with 2 nodes.
     * @param Node start
     * @param Node end
     */
    public void connect(Node start, Node end);

    /**
     * Get the start node.
     * @return Node start
     */
    public Node getStart();

    /**
     * Get the end node.
     * @return Node end
     */
    public Node getEnd();

    /**
    * Set the start node
    * @param start
    */
    public void setStart(Node start);

    /**
    * Set the end node
    * @param end 
    */
    public void setEnd(Node end);

    /**
     * Get the edge connect 2 points from the be connected 2 nodes 
     * @return 2 connection point
     */
    public Point2D[] getConnectionPoints();

    /**
     * Get the smallest rectangle that bounds this edge and labels.
     * @return Rectangle2D
     */
    public Rectangle2D getBounds(Graphics2D g2);

    /**
    * Set edge linewidth (1pt - 20pt)
    * @param thickness 
    */
    public void setLinewidth(float linewidth);

    /**
    * Get edge linewidth
    * @return 
    */
    public float getLinewidth();

    /**
    * Set line colour
    * @param colour 
    */
    public void setLineColour(Color lineColour);

    /**
    * Get line colour
    * @return 
    */
    public Color getLineColour();

    /**
    * Get line colour string (black, red ...)
    * @return 
    */
    public String getLineColourString();

    /**
    * Set edge label
    */
    public void setLabel(String label);

    /**
    * Get edge label
    * @return 
    */
    public String getLabel();

    /**
    * Set label position
    * Refer to PSTricks user manual
    * @param posion 
    */
    public void setLabelPosition(String labelPosition);

    /**
    * Get label position
    * @return 
    */
    public String getLabelPosition();

    /**
    * Set start arrow
    */
    public void setStartArrow(ArrowHead startArrow);

    /**
    * Get start arrow as string translated as PSTricks code
    * @return 
    */
    public ArrowHead getStartArrow();

    /**
    * Set end arrow
    */
    public void setEndArrow(ArrowHead endArrow);

    /**
    * Get end arrow as string translated as PSTricks code
    * @return 
    */
    public ArrowHead getEndArrow();

    /**
    * Get the string representation of arrows, (e.g. -, <->, ->, -o, etc)
    * @return 
    */
    public String getArrowString();

    /**
    * Set line style
    * @param style 
    */
    public void setLineStyle(String style);

    /**
    * Get line style
    * @return 
    */
    public String getLineStyle();

    /**
    * Get PStricks code representation of this edge
    * @return 
    */
    public String getCode();
   
    /**
     * @return the comments
     */
    public String getComments();
    
    /**
     * @param comments the comments to set
     */
    public void setComments(String comments);
   
   public Object clone();
}
