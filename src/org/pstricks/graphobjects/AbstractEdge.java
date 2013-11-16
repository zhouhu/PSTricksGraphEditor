/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pstricks.graphobjects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author zhouhu
 */
public abstract class AbstractEdge implements Edge{
    
    // Line colour
    private Color lineColour;
    public static final Color DEFAULT_LINE_COLOUR = Color.BLACK;
    
    
    // Line style (none, dotted, dashed)
    private String lineStyle;
    public static final String DOTTED_STYLE = "dotted";
    public static final String DASHED_STYLE = "dashed";
    public static final String SOLID_STYLE = "solid";
    public static final String DEFAULT_LINE_STYLE = SOLID_STYLE;
    private Stroke lineStroke;
    
    // Label
    private String label;
    private String labelPosition;
    public static final String LABEL_POSITION_ON = "\\ncput*";
    public static final String LABEL_POSITION_ABOVE = "\\naput";
    public static final String LABEL_POSITION_BELOW = "\\nbput";
    
    // Line linewidth
    private float linewidth;
    public static final float DEFAULT_LINEWIDTH = 1;

    // Connected with 2 nodes
    private Node start;
    private Node end;
    
     // Arrow heads
    private ArrowHead startArrow;
    private ArrowHead endArrow;
    
    // Comments
    private String comments;
    
    // Dafault Constructor
    public AbstractEdge() {
        lineStyle = DEFAULT_LINE_STYLE;
        linewidth = DEFAULT_LINEWIDTH;
        lineColour = DEFAULT_LINE_COLOUR;
        label = "";
        labelPosition = LABEL_POSITION_ABOVE;
        startArrow = new ArrowHead();
        endArrow = new ArrowHead();
        comments = "";
        setLineStroke();
    }
    
    @Override
    public void connect(Node s, Node e){  
        start = s;
        end = e;
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
    public Stroke getLineStroke() {
        return lineStroke;
    }

    private void setLineStroke() {
        if(lineStyle.equals(DOTTED_STYLE)){
            lineStroke = new BasicStroke(linewidth,
			BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER,
			1.f, new float[]{0,linewidth+3}, 0);
	}else if(lineStyle.equals(DASHED_STYLE)){
            lineStroke = new BasicStroke(linewidth,
			BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
			1.f, new float[]{5.0f, 3.0f}, 0);
	}else{
            lineStroke = new BasicStroke(linewidth,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
        }
    }
    
    @Override
    public Node getStart() {
        return start;
    }

    @Override
    public Node getEnd() {
        return end;
    }
    
    @Override
    public void setStart(Node start){
        this.start = start;
    }
    
    @Override
    public void setEnd(Node end){
        this.end = end;
    }
    
    @Override
    public float getLinewidth(){
        return linewidth/50;
    }
    
    @Override
    public void setLinewidth(float linewidth){
        this.linewidth = linewidth;
        setLineStroke();
    }
    
    @Override
    public Color getLineColour(){
        return lineColour;
    }
    
    @Override
    public void setLineColour(Color lineColour){
        this.lineColour = lineColour;
    }
    
    @Override
    public String getLineColourString(){
        
        String colour = "black";
        if (lineColour.getRGB() == Color.DARK_GRAY.getRGB()){
            colour = "darkgray";
        }else if (lineColour.getRGB() == Color.GRAY.getRGB()){
            colour = "gray";
        }else if (lineColour.getRGB() == Color.LIGHT_GRAY.getRGB()){
            colour = "lightgray";
        }else if (lineColour.getRGB() == Color.WHITE.getRGB()){
            colour = "white";
        }else if (lineColour.getRGB() == Color.RED.getRGB()){
            colour = "red";
        }else if (lineColour.getRGB() == Color.GREEN.getRGB()){
            colour = "green";
        }else if (lineColour.getRGB() == Color.BLUE.getRGB()){
            colour = "blue";
        }else if (lineColour.getRGB() == Color.CYAN.getRGB()){
            colour = "cyan";
        }else if (lineColour.getRGB() == Color.MAGENTA.getRGB()){
            colour = "magenta";
        }else if (lineColour.getRGB() == Color.YELLOW.getRGB()){
            colour = "yellow";
        }
        
        return colour;
    }
    
    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setLabelPosition(String labelPosition) {
        this.labelPosition = labelPosition;
    }

    @Override
    public String getLabelPosition() {
        return labelPosition;
    }

    @Override
    public void setLineStyle(String style) {
        lineStyle = style;
        setLineStroke();
    }

    @Override
    public String getLineStyle() {
        return lineStyle;
    }
    
    @Override
    public void setStartArrow(ArrowHead startArrow) {
        this.startArrow = startArrow;
    }

    @Override
    public ArrowHead getStartArrow() {
        return startArrow;
    }

    @Override
    public void setEndArrow(ArrowHead endArrow) {
        this.endArrow = endArrow;
    }

    @Override
    public ArrowHead getEndArrow() {
        return endArrow;
    }
    
    @Override
    public String getArrowString(){
        String arrows = "-";
        String arrowStart = startArrow.getArrowStyle();
        String arrowEnd = endArrow.getArrowStyle();
        if(!arrowStart.equals(ArrowHead.DEFAULT_ARROW) &&
           !arrowEnd.equals(ArrowHead.DEFAULT_ARROW)){
            arrows = arrowStart+"-"+arrowEnd;
        }else if(!arrowStart.equals(ArrowHead.DEFAULT_ARROW) ||
           !arrowEnd.equals(ArrowHead.DEFAULT_ARROW)){
            arrows = arrowStart+arrowEnd;
        }
        return arrows;
    }
    
    @Override
    public Point2D[] getConnectionPoints(){
        Rectangle2D startBounds = start.getBounds();
        Rectangle2D endBounds = end.getBounds();
        
        Point2D startCenter = new Point2D.Double(
                startBounds.getCenterX(), startBounds.getCenterY());
        Point2D endCenter = new Point2D.Double(
                endBounds.getCenterX(), endBounds.getCenterY());
        
        Point2D[] points = new Point2D[2];
        points[0] = getStart().getConnectionPoint(endCenter);
        points[1] = getEnd().getConnectionPoint(startCenter);
        return points;
    }

    @Override
    public String getComments() {
        return comments;
    }

    @Override
    public void setComments(String comments) {
        this.comments = comments;
    }
}
