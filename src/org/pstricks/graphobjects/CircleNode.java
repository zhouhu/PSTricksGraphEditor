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
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author zhouhu
 */
public class CircleNode extends AbstractNode{

    private float size;
    // Line linewidth
    private float linewidth;
    public static final float DEFAULT_LINEWIDTH = 1;
    // Line style (none, dotted, dashed)
    private String lineStyle;
    private Stroke lineStroke;
 
        
    public CircleNode() {
        linewidth = DEFAULT_LINEWIDTH;
        // Circle node \pscnode has default size 25
        size = 25;
        lineStyle = AbstractEdge.DEFAULT_LINE_STYLE;
        setLabel(getNodeID());
        setLineStroke();
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setStroke(lineStroke);
        Color oldColour = g2.getColor();
        FontMetrics fontMetrics = g2.getFontMetrics();
        if(fontMetrics.stringWidth( getLabel()) > 25){
            size = fontMetrics.stringWidth( getLabel()) + 6;
        } else {
            size = 25;
        }
        
        g2.setColor(getNodeColour());
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.draw(new Ellipse2D.Double(getPositionX(), getPositionY(), size, size));
        
        g2.setColor(Color.BLACK);
        g2.drawString(getLabel(), getPositionX() + size/2 - fontMetrics.stringWidth(getLabel())/2, getPositionY() + size/2);
        
        g2.setColor(oldColour);
    }
    
    private void setLineStroke() {
        if(lineStyle.equals(AbstractEdge.DOTTED_STYLE)){
            lineStroke = new BasicStroke(linewidth,
			BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER,
			1.f, new float[]{0,linewidth+3}, 0);
	}else if(lineStyle.equals(AbstractEdge.DASHED_STYLE)){
            lineStroke = new BasicStroke(linewidth,
			BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
			1.f, new float[]{5.0f, 3.0f}, 0);
	}else{
            lineStroke = new BasicStroke(linewidth,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
        }
    }
    
    public float getLinewidth(){
        return linewidth/50;
    }
    
    public void setLinewidth(float linewidth){
        this.linewidth = linewidth;
        setLineStroke();
    }
    
    @Override
    public void setSize(float size){
        return;
    }
    
    @Override
    public float getSize(){
        return size;
    }
    
    @Override
    public boolean contains(Point2D point) {
        return new Ellipse2D.Double(getPositionX(), getPositionY(), size, size).contains(point);
    }

    @Override
    public Point2D getConnectionPoint(Point2D point) {
        float dx = (float) (point.getX() - getCentreX());
        float dy = (float) (point.getY() - getCentreY());
        
        double distance = Math.sqrt(dx * dx + dy * dy);
        if (distance == 0) 
            return point;
        else return new Point2D.Double(
            getCentreX() + dx * (size / 2) / distance,
            getCentreY() + dy * (size / 2) / distance);
    }

    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(getPositionX(), getPositionY(), size, size);
    }

    /**
     * @return the lineStyle
     */
    public String getLineStyle() {
        return lineStyle;
    }

    /**
     * @param lineStyle the lineStyle to set
     */
    public void setLineStyle(String lineStyle) {
        this.lineStyle = lineStyle;
        setLineStroke();
    }

    @Override
    public float getCentreX(){
        return getPositionX() + size/2;
    }
    
    @Override
    public float getCentreY(){
        return getPositionY() + size/2;
    }
    
    @Override
    public String getCode() {
        double x = getCentreX()/50;
        double y = getCentreY()/50;
        
        double maxY = 0;
        double minX = 0;
        double minY = 0;
        double[] graphSize = getGraphSize();
        if(graphSize != null){
            maxY = graphSize[1]/50;
            minX = graphSize[2]/50;
            minY = graphSize[3]/50;
        }
        
        x -= minX;
        
        //double middleY = (maxY + minY)/2;
        
        //if(y == middleY) y = 0;
        //else y = middleY - y;
        
        if(y == maxY) y = 0;
        else y = maxY - y;
        
        String comments = getComments();
        String newLine = System.getProperty("line.separator");
        
        String nameCode = getLabel().replaceAll("\\\\", "\\\\textbackslash ");
        if (!comments.isEmpty())
            return comments + newLine + "\\cnodeput[linecolor="+
                    getNodeColourString()+",linewidth=" + getLinewidth() +
                    ",linestyle="+lineStyle+"](" 
                    + x +","+y+"){"+getNodeID()+"}{"+nameCode+"}";
        
        return "\\cnodeput[linecolor="+getNodeColourString()+",linewidth=" + 
                getLinewidth() + ",linestyle="+lineStyle+"](" 
                + x +","+y+"){"+getNodeID()+"}{"+nameCode+"}";
        
    }
}
