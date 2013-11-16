package org.pstricks.graphobjects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.io.Serializable;

/**
 *
 * @author zhouhu
 */
public class ArrowHead implements Serializable {
    
    /**
     * Default arrow style "-"
     */
    public static final String DEFAULT_ARROW    = "-";
    
    /**
     * Left arrow "<"
     */
    public static final String LEFT_ARROW       = "<";
    
    /**
     * Right arrow ">"
     */
    public static final String RIGHT_ARROW      = ">";
    
    /**
     * Double left arrow "<<"
     */
    public static final String DOUBLE_LEFT_ARROW    = "<<";
    
    /**
     * Double right arrow ">>"
     */
    public static final String DOUBLE_RIGHT_ARROW   = ">>";
    
    /**
     * Bar end arrow "|*"
     */
    public static final String BAR_END_ARROW    = "|*";
    
    /**
     * Bar in arrow "|"
     */
    public static final String BAR_IN_ARROW     = "|";
    
    /**
     * Left square bracket arrow "["
     */
    public static final String LEFT_SQUARE_BRACKET  = "[";
    
    /**
     * Right square bracket arrow "]"
     */
    public static final String RIGHT_SQUARE_BRACKET = "]";
    
    /**
     * Left round bracket arrow "("
     */
    public static final String LEFT_ROUND_BRACKET   = "(";
    
    /**
     * Right round bracket arrow ")"
     */
    public static final String RIGHT_ROUND_BRACKET  = ")";
    
    /**
     * Circle end arrow "o"
     */
    public static final String CIRCLE_END_ARROW = "o";
    
    /**
     * Circle in arrow "oo"
     */
    public static final String CIRCLE_IN_ARROW  = "oo";
    
    /**
     * Disk end arrow "*"
     */
    public static final String DISK_END_ARROW   = "*";
    
    /**
     * Disk in arrow "**"
     */
    public static final String DISK_IN_ARROW    = "**";
    
    /**
     * Round end arrow "c"
     */
    public static final String ROUND_END_ARROW  = "c";
    
    /**
     * Round in arrow "cc"
     */
    public static final String ROUND_IN_ARROW   = "cc";
    
    /**
     * Square end arrow "C"
     */
    public static final String SQUARE_END_ARROW = "C";
    
    
    private String arrowStyle;
    

    
    public ArrowHead() {
        arrowStyle = DEFAULT_ARROW;
    }
    
    public void setArrowStyle(String arrowStyle){
        this.arrowStyle = arrowStyle;
    }
    
    public String getArrowStyle(){
        return arrowStyle;
    }

    /**
     * 
     * @param g2
     * @param ph head point
     * @param pe end point
     * @param linewidth
     * @param lineColour 
     */
    public void draw(Graphics2D g2, Point2D ph, Point2D pe, float lineWidth, Color lineColour){
        
        if(arrowStyle.equals(DEFAULT_ARROW)) return;
        
        
        // used for compute the arrow head angle
        final double ARROW_ANGLE = Math.PI / 6;
        final double ARROW_LENGTH = 8;
        double dx = pe.getX() - ph.getX();
        double dy = pe.getY() - ph.getY();
        double angle = Math.atan2(dy, dx);
        
        // compute the arrow angle when edge moves
        double x1 = pe.getX() - ARROW_LENGTH * Math.cos(angle - ARROW_ANGLE);
        double y1 = pe.getY() - ARROW_LENGTH * Math.sin(angle - ARROW_ANGLE);
        double x2 = pe.getX() - ARROW_LENGTH * Math.cos(angle + ARROW_ANGLE);
        double y2 = pe.getY() - ARROW_LENGTH * Math.sin(angle + ARROW_ANGLE);
        
        // draw a basic < or > shap to represents an arrow head
        GeneralPath path = new GeneralPath();
        path.moveTo((float) pe.getX(), (float) pe.getY());
        path.lineTo((float) x1, (float) y1);
        
        path.moveTo((float) x2, (float) y2);
        path.lineTo((float) pe.getX(), (float) pe.getY()); 
        
        Color oldColor = g2.getColor();
        
        g2.setColor(lineColour);
       
        g2.draw(path);
        g2.setColor(oldColor);
    }
        
}
