/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pstricks.graphobjects;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author zhouhu
 */
public class LineEdge extends AbstractEdge{

    public LineEdge() {
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setStroke(getLineStroke());
        Color oldColour = g2.getColor();
        g2.setColor(getLineColour());

        // Antialiasing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
        Line2D edge = new Line2D.Double(getConnectionPoints()[0],
                                  getConnectionPoints()[1]);
        g2.draw(edge);
        
        // compute the middle point
        float x = (float) (getConnectionPoints()[0].getX() + getConnectionPoints()[1].getX())/2;
        float y = (float) (getConnectionPoints()[0].getY() + getConnectionPoints()[1].getY())/2;
        Point2D midpoint = new Point2D.Double(x,y);
        
        // draw arrow
        getStartArrow().draw(g2, midpoint,getConnectionPoints()[0], 
                 getLinewidth(), getLineColour());
        
        getEndArrow().draw(g2, midpoint,getConnectionPoints()[1], 
                 getLinewidth(), getLineColour());

        FontMetrics fontMetrics = g2.getFontMetrics();
        // String representation
        String arrows = getArrowString();
        if (!arrows.equals("-")){
            float width = fontMetrics.stringWidth(arrows);
            g2.setColor(getLineColour());
            g2.drawString(arrows,  x - width/2, y - 5.0f - fontMetrics.getHeight());
        }
        
        // draw label
        if(!getLabel().isEmpty()){
            float width = fontMetrics.stringWidth(getLabel());
            g2.setColor(Color.BLACK);
            g2.drawString(getLabel(), x - width/2, y - 5.0f);
        }
        
        g2.setColor(oldColour);
    }
    
        
    @Override
    public Rectangle2D getBounds(Graphics2D g2){
        Line2D conn = new Line2D.Double(getConnectionPoints()[0],
                                        getConnectionPoints()[1]);      
        Rectangle2D r = new Rectangle2D.Double();
        r.setFrameFromDiagonal(conn.getX1(), conn.getY1(),
                               conn.getX2(), conn.getY2());
        return r;
    }
    
    @Override
    public boolean contains(Point2D point) {
        return new Line2D.Double(
                getConnectionPoints()[0],
                getConnectionPoints()[1]).ptSegDist(point) < 3;
    }
    
    @Override
    public String getCode() {

        String str = "\\ncline[linewidth=" + getLinewidth() + 
                ", linecolor=" + getLineColourString() +
                ", linestyle=" + getLineStyle()+"]" +
                "{" + getArrowString() + "}{" +
                getStart().getNodeID() + "}{" + getEnd().getNodeID() + "}";
        
        String comments = getComments();
        String newLine = System.getProperty("line.separator");
        
        if(!comments.isEmpty()){
            if(getLabel().isEmpty()) return comments + newLine + str;
            String labelCode = getLabel().replaceAll("\\\\", "\\\\textbackslash ");
            return comments + newLine + str + newLine + 
                    getLabelPosition()+"{"+labelCode+"}";
        }
        
        if(getLabel().isEmpty()) return str;
        String labelCode = getLabel().replaceAll("\\\\", "\\\\textbackslash ");
        return str+newLine+getLabelPosition()+"{"+labelCode+"}";
    }
}
