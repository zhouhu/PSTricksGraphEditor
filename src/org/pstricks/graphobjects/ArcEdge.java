/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pstricks.graphobjects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;
import org.pstricks.ui.GraphCanvas;

/**
 * Implemented by quadratic curve
 * @author zhouhu
 */
public class ArcEdge extends AbstractEdge {

    
    // the quadratic contrl point
    private double ctrlX;
    private double ctrlY;
    
    // mouse drag point to adjust the angle
    private double miceX;
    private double miceY;
    
    private boolean onDrag;
    
    // arc angle
    private double arcAngle;
    
    public ArcEdge() {
        ctrlX = 0;
        ctrlY = 0;
        arcAngle = 30;
        onDrag = false;
    }
    

    @Override
    public void draw(Graphics2D g2) {
        
        Color oldColour = g2.getColor();
        // Antialiasing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        
        // point (x1,y1)  connects to start node
        // point (x2,y2)  connects to end node
        double x1 = getConnectionPoints()[0].getX();
        double x2 = getConnectionPoints()[1].getX();
        double y1 = getConnectionPoints()[0].getY();
        double y2 = getConnectionPoints()[1].getY();
        
        // The distance between point(x1,y1) and point(x2,y2)
        double distance = Math.sqrt(Math.abs(x1-x2)*Math.abs(x1-x2) + 
                                    Math.abs(y1-y2)*Math.abs(y1-y2));
        double h = Math.tan(Math.toRadians(arcAngle)) * distance/2;
        //ctrlX = x1 + distance/2;
        //ctrlY = y1 - h;
        QuadCurve2D arc = new QuadCurve2D.Double(x1, y1, ctrlX, ctrlY, x2, y2);
        FontMetrics fontMetrics = g2.getFontMetrics();
        
        // When arc on drag draw the agnle lines
        if(onDrag){
            g2.setColor(GraphCanvas.selectorColour);
            g2.setStroke(new BasicStroke(1,
			BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
			1.f, new float[]{5.0f, 3.0f}, 0));
            g2.draw(new Line2D.Double(x1, y1, x2, y2));
            g2.draw(new Line2D.Double(x1,y1,ctrlX,ctrlY));
            g2.draw(new Line2D.Double(x2,y2,ctrlX,ctrlY));
            String arcAngleStr = "Arc Angle: " + arcAngle;
            float width = fontMetrics.stringWidth(arcAngleStr);
            g2.drawString(arcAngleStr, (float)((x1+x2)/2 - width/2), (float)((y1+y2)/2));
            onDrag = false;
        }
        
        g2.setStroke(getLineStroke());
        g2.setColor(getLineColour());
        g2.draw(arc);
        
        // compute label and arrow position
        // middle point of (x1, y1) (x2, y2)
        float mx1 = (float) (x1+x2)/2;
        float my1 = (float) (y1+y2)/2;
        // middle point of ctrl point to (mx1,my1)
        float mx = (float) (mx1+ctrlX)/2;
        float my = (float) (my1+ctrlY)/2;
        
        // draw label
        if(!getLabel().isEmpty()){
            // Draw lable, labels colour balck
            g2.setColor(Color.BLACK);
            float width = fontMetrics.stringWidth(getLabel());
            g2.drawString(getLabel(), mx - width/2, my - 5.0f);
        }
        Point2D ctrlPoint = new Point2D.Double(ctrlX, ctrlY);
        // draw arrow
        getStartArrow().draw(g2, ctrlPoint,getConnectionPoints()[0], 
                 getLinewidth(), getLineColour());
        
        getEndArrow().draw(g2, ctrlPoint,getConnectionPoints()[1], 
                 getLinewidth(), getLineColour());
        
        // draw arrow string
        String arrows = getArrowString();
        float width = fontMetrics.stringWidth(arrows);
        if (!arrows.equals("-")){
            g2.setColor(getLineColour());
            g2.drawString(arrows, mx - width/2, my - 5.0f - fontMetrics.getHeight());
        }
        
        
        g2.setColor(oldColour);
    }
    
    @Override
    public boolean contains(Point2D point) {
        double x1 = getConnectionPoints()[0].getX();
        double x2 = getConnectionPoints()[1].getX();
        double y1 = getConnectionPoints()[0].getY();
        double y2 = getConnectionPoints()[1].getY();
        
        // middle point of (x1, y1) (x2, y2)
        double mx1 = (x1+x2)/2;
        double my1 = (y1+y2)/2;
        // middle point of ctrl point to (mx1,my1)
        double mx = (mx1+ctrlX)/2;
        double my = (my1+ctrlY)/2;
        
        if(new Line2D.Double(x1,y1,mx,my).ptSegDist(point) < 5 || 
           new Line2D.Double(x2,y2,mx,my).ptSegDist(point) < 5) 
            
            return true;
        
 
            return false;
    }
    
    @Override
    public Rectangle2D getBounds(Graphics2D g2){
        double x1 = getConnectionPoints()[0].getX();
        double x2 = getConnectionPoints()[1].getX();
        double y1 = getConnectionPoints()[0].getY();
        double y2 = getConnectionPoints()[1].getY();
 
        return new QuadCurve2D.Double(x1, y1, ctrlX, ctrlY, x2, y2).getBounds2D();
    }
    
    /**
     * Used for drawing the selector point on the canvas
     * @return 
     */
    public Point2D getCtrlPoint(){
        return new Point2D.Double(ctrlX,ctrlY);
    }
    
    /**
     * Set Control Point position
     * @param p 
     */
    public void setCtrlPoint(Point2D p){
        ctrlX = p.getX();
        ctrlY = p.getY();
    }
    
    /**
     * User drag the arc edge, to adjust the arc angle
     * Calculate the angle
     * @param p 
     */
    public void setMouseDragePoint(Point2D p){
        miceX = p.getX();
        miceY = p.getY();
        
        double angle = 20.0;
        
        //Calculate the angle 
        
        
        if(angle>30){
            arcAngle = 30;
        } else if (angle<-30){
            arcAngle = -30;
        } else {
            arcAngle = angle;
        }
    }
    
    /**
     * @return the arcAngle
     */
    public double getArcAngle() {
        return arcAngle;
    }

    /**
     * @param arcAngle the arcAngle to set
     */
    public void setArcAngle(double arcAngle) {
        this.arcAngle = arcAngle;
    }
    
    public void setOnDrag(boolean b) {
        onDrag = b;
    }

    /**
     * @return the onDrag
     */
    public boolean isOnDrag() {
        return onDrag;
    }

    @Override
    public String getCode() {
        
        String str = 
                "\\ncarc[arcangle=" + arcAngle + ", linewidth=" + getLinewidth() + 
                ", linecolor=" + getLineColourString() +
                ", linestyle=" + getLineStyle()+ "]" + "{" + getArrowString() +
                "}{"+ getStart().getNodeID() +"}{" + getEnd().getNodeID()+"}";
        
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
