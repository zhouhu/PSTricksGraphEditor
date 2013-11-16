/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pstricks.graphobjects;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import org.pstricks.ui.PSTricksGraphEditor;

/**
 *
 * @author zhouhu
 */
public class UPutLabel implements PSTLabel  {
 
    /**
     * Label text
     */
    private String text;
    private String comments;
    private float width;
    private float height;
    private float x;
    private float y;

    public UPutLabel() {
        text = "new label";
        width = 0;
        height = 0;
        x = 0;
        y = 0;
        comments = "";
    }
    
    
    
    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(x, y-height, width, height);
    }

    @Override
    public void position(double dx, double dy) {
        x += dx;
        y += dy;
    }

    @Override
    public boolean contains(Point2D p) {
        return new Rectangle2D.Double(x, y-height, width, height).contains(p);
    }

    @Override
    public void draw(Graphics2D g2) {
        Color oldColour = g2.getColor();
        g2.setColor(Color.BLACK);
        g2.drawString(text,x,y);
        FontMetrics fontMetrics = g2.getFontMetrics();
        width = fontMetrics.stringWidth( text );
        height = fontMetrics.getHeight();
        g2.setColor(oldColour);
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }
    
    @Override
    public Object clone(){
        try{
            return super.clone();
        } catch (CloneNotSupportedException exception){
            return null;
        }
    }

    @Override
    public double getCentreX() {
        return x + width/2;
    }

    @Override
    public double getCentreY() {
        return y + height/2;
    }

    @Override
    public String getComments() {
        return comments;
    }

    @Override
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    @Override
    public String getCode() {
        double maxY = 0;
        double minX = 0;
        double minY = 0;
        double[] graphSize = PSTricksGraphEditor.canvas.getGraphSize();
        if(graphSize != null){
            maxY = graphSize[1]/50;
            minX = graphSize[2]/50;
            minY = graphSize[3]/50;
        }
        double codeX = (double)x/50;
        codeX -= minX;
        //double middleY = (maxY + minY)/2;
        double codeY = (double)y/50;
        //if(codeY == middleY) codeY = 0;
        //else codeY = middleY - codeY;
        if(codeY == maxY) codeY = 0;
        else codeY = maxY - codeY;
        String textCode = text.replaceAll("\\\\", "\\\\textbackslash ");
        if(!comments.isEmpty())
            return comments + System.getProperty("line.separator") + 
                    "\\uput[0]("+codeX+","+codeY+"){"+textCode+"}";
        
        return "\\uput[0]("+codeX+","+codeY+"){"+textCode+"}";
    }
}
