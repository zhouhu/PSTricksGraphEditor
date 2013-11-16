/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pstricks.graphobjects;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author zhouhu
 */
public class CircleEdge extends AbstractEdge{
    // Upper left point of the rectangel containning the circle
    private double x;
    private double y;
    // Circle Radiu*2 (diameter)
    private double R;
    // Code R (user sepcified R)
    private double codeR;
    
    public CircleEdge() {
      x = 0;
      y = 0;
      R = 0;
      setLabelPosition(AbstractEdge.LABEL_POSITION_BELOW);
    }

    
    @Override
    public void draw(Graphics2D g2) {
        g2.setStroke(getLineStroke());
        Color oldColour = g2.getColor();
        g2.setColor(getLineColour());

        // Antialiasing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
        
        double x1 = getStart().getPositionX();
        double y1 = getStart().getPositionY();
        // The R of the circle
        R = (getStart().getBounds().getWidth()*2);
        // comput upper rectangle left corner posision, the rectangle contains
        // the circle
        this.x = x1 + getStart().getBounds().getWidth()/2 - R/2;
        this.y = y1 + getStart().getBounds().getHeight()/2 - R;
        g2.draw(new Arc2D.Double(x, y, R, R, 300, 300, Arc2D.OPEN));
        // draw label
        FontMetrics fontMetrics = g2.getFontMetrics();
        if(!getLabel().isEmpty()){
            g2.setColor(Color.BLACK);
            float width = fontMetrics.stringWidth(getLabel());
            g2.drawString(getLabel(), (float)(x+R/2) - width/2, (float)y - 5.0f);
        }
        
        // draw arrow string
        String arrows = getArrowString();
        float width = fontMetrics.stringWidth(arrows);
        if (!arrows.equals("-")){
            g2.drawString(arrows, (float)(x+R/2) - width/2, (float)(y+R/3));
        }
        g2.setColor(oldColour);
    }

    @Override
    public boolean contains(Point2D point) {
        return (new Arc2D.Double(x, y, R, R, 300, 200, Arc2D.OPEN)
                                .getBounds2D().contains(point));
    }
    
    /**
     * Circle node connection connect itself
     * @param start
     * @param end 
     */
    
    @Override
    public void connect(Node start, Node end) {
        // connect the node it self
        setStart(start);
        setEnd(start);
    }
    
    @Override
    public Point2D[] getConnectionPoints() {
        Point2D[] points = new Point2D[2];
        points[0] = new Point2D.Double(x,y+R/10);
        points[1] = new Point2D.Double(x+R,y+R/10);
        return points;
    }

    @Override
    public Rectangle2D getBounds(Graphics2D g2) {
        return new Arc2D.Double(x, y, R, R, 300, 300, Arc2D.OPEN)
                                .getBounds();
    }
    
    @Override
    public String getCode() {
        double r = (double)(getCodeR()/100);
        String str = "\\nccircle[linewidth=" + getLinewidth() + 
                ", linecolor=" + getLineColourString() +
                ", linestyle=" + getLineStyle()+"]{"+getArrowString()+"}{"+
                getStart().getNodeID()+"}{"+r+"}";
        
        String comments = getComments();
        String newLine = System.getProperty("line.separator");
        
        
        if(!comments.isEmpty()){
            if(getLabel().isEmpty()) return comments + newLine + str;
            String labelCode = getLabel().replaceAll("\\\\", "\\\\textbackslash ");
            return comments + newLine + str + newLine + 
                    "\\nbput{"+labelCode+"}";
        }
        
        if(getLabel().isEmpty()) return str;
        String labelCode = getLabel().replaceAll("\\\\", "\\\\textbackslash ");
        return str+newLine+"\\nbput{"+labelCode+"}";
    }

    /**
     * @return the codeR
     */
    public double getCodeR() {
        if(codeR <= 0){
            return R;
        }
        return codeR;
    }

    /**
     * @param codeR the codeR to set
     */
    public void setCodeR(double codeR) {
        if(codeR <= 0){
            this.codeR = R;
        } else {
            this.codeR = codeR;
        }
    }
}
