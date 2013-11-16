/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pstricks.graphobjects;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import org.pstricks.ui.PSTricksGraphEditor;

/**
 *
 * @author zhouhu
 */
public abstract class AbstractNode implements Node {
        
    // Node nodeColour
    private Color nodeColour;
    private static final Color DEFAULT_COLOUR = Color.BLACK;
    
    // Node size
    private float size;
    public static final float DEFAULT_SIZE = 12;
    
    // Node frame postion (enclosed rectangle upper left postion)
    private float x;
    private float y;
    

    // Node ID
    private String nodeID;
    // comments
    private String comments;
    // label
    private String label;
    // label position
    
    public static final String LABEL_POSITION_RIGHT = "r";
    
    public static final String LABEL_POSITION_UP = "u";
    
    public static final String LABEL_POSITION_LEFT = "l";
    
    public static final String LABEL_POSITION_DOWN = "d";
    
    public static final String LABEL_POSITION_UP_RIGHT = "ur";
    
    public static final String LABEL_POSITION_UP_LEFT = "ul";
    
    public static final String LABEL_POSITION_DOWN_RIGHT = "dr";
    
    public static final String LABEL_POSITION_DOWN_LEFT = "dl";
    
    
    private String labelPosition;
    
    public AbstractNode() {
        nodeColour = DEFAULT_COLOUR;
        size = DEFAULT_SIZE;
        x = 0;
        y = 0;
        nodeID = "";
        comments = "" ;
        label = "";
        labelPosition = LABEL_POSITION_UP;
    }

    @Override
    public Point2D getConnectionPoint(Point2D point) {
        
        double dx = point.getX() - getCentreX();
        double dy = point.getY() - getCentreY();
        
        double distance = Math.sqrt(dx * dx + dy * dy);
        if (distance == 0) 
            return point;
        else return new Point2D.Double(
            getCentreX() + dx * (size / 2) / distance,
            getCentreY() + dy * (size / 2) / distance);
    }

    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(x, y, size, size);
    }
    
    @Override
    public boolean contains(Point2D point) {
        return new Rectangle2D.Double(x, y, size, size).contains(point);
    }

    @Override
    public Color getNodeColour() {
        return nodeColour;
    }

    @Override
    public void setNodeColour(Color colour) {
        this.nodeColour = colour;
    }
    
    @Override
    public String getNodeColourString(){
        
        String colour = "black";
        if (nodeColour.getRGB() == Color.DARK_GRAY.getRGB()){
            colour = "darkgray";
        }else if (nodeColour.getRGB() == Color.GRAY.getRGB()){
            colour = "gray";
        }else if (nodeColour.getRGB() == Color.LIGHT_GRAY.getRGB()){
            colour = "lightgray";
        }else if (nodeColour.getRGB() == Color.WHITE.getRGB()){
            colour = "white";
        }else if (nodeColour.getRGB() == Color.RED.getRGB()){
            colour = "red";
        }else if (nodeColour.getRGB() == Color.GREEN.getRGB()){
            colour = "green";
        }else if (nodeColour.getRGB() == Color.BLUE.getRGB()){
            colour = "blue";
        }else if (nodeColour.getRGB() == Color.CYAN.getRGB()){
            colour = "cyan";
        }else if (nodeColour.getRGB() == Color.MAGENTA.getRGB()){
            colour = "magenta";
        }else if (nodeColour.getRGB() == Color.YELLOW.getRGB()){
            colour = "yellow";
        }
        
        return colour;
    }

    @Override
    public float getSize() {
        return size;
    }

    @Override
    public void setSize(float size) {
        // When node size < 5, it becomes too samll to select with mouse
        if(size > 5) this.size = size;
        else this.size = 5;
    }

    @Override
    public float getPositionX() {
        return x;
    }

    @Override
    public float getPositionY() {
        return y;
    }

    @Override
    public void setPositionX(float x){
        this.x = x;
    }
    
    @Override
    public void setPositionY(float y){
        this.y = y;
    }

        
    @Override
    public void position(float dx, float dy) {
        x += dx;
        y += dy;
    }
    
    @Override
    public Object clone(){
        //System.out.println("wocao");
        Graph.nodeCounter++;
        //System.out.println(Graph.nodeCounter++);
        try{
            return super.clone();
        } catch (CloneNotSupportedException exception){
            return null;
        }
    }


    @Override
    public String getNodeID() {
        return nodeID;
    }

    @Override
    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

    @Override
    public float getCentreX(){
        return  (x + size/2);
    }
    
    @Override
    public float getCentreY(){
        return  (y + size/2);
    }

    @Override
    public String getComments() {
        return comments;
    }

    @Override
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    public double[] getGraphSize(){
         return PSTricksGraphEditor.canvas.getGraphSize();
    }

    /**
     * @return the label
     */
    @Override
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the labelPosition
     */
    @Override
    public String getLabelPosition() {
        return labelPosition;
    }

    /**
     * @param labelPosition the labelPosition to set
     */
    @Override
    public void setLabelPosition(String labelPosition) {
        this.labelPosition = labelPosition;
    }
}
