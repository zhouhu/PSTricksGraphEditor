package org.pstricks.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;
import org.pstricks.graphobjects.ArcEdge;
import org.pstricks.graphobjects.CircleEdge;
import org.pstricks.graphobjects.Edge;
import org.pstricks.graphobjects.Graph;
import org.pstricks.graphobjects.PSTLabel;
import org.pstricks.graphobjects.LineEdge;
import org.pstricks.graphobjects.Node;

/**
 * Draw and displays graph
 * @author zhouhu
 */
public final class GraphCanvas extends JPanel implements MouseListener,
                                              MouseMotionListener{
    public final Grid grid;
    private boolean mouseIn = false;
    private final PSTricksGraphEditor window;
    
    public Object selected;
    private Point2D lastMousePoint;
    private Point2D selecterBandStart;
    private Point2D dragStartPoint;
    private Rectangle2D dragStartBounds;
    public static final Color selectorColour = new Color(0,200,0);
    
    private double[] graphSize;

    public GraphCanvas(PSTricksGraphEditor window) {
        grid = new Grid(this);
        setBackground(Color.WHITE);
        this.window = window;
        addListeners();
    }
    
    private void addListeners(){
        addMouseListener(this);
        addMouseMotionListener(this);
    }
        
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        grid.paint(g,0);
        Graphics2D g2 = (Graphics2D) g;
        //Rectangle2D bounds = getBounds();
        Rectangle2D graphBounds = PSTricksGraphEditor.graph.getBounds(g2);
        graphSize = new double[4];
        graphSize[0] = graphBounds.getMaxX();
        graphSize[1] = graphBounds.getMaxY();
        graphSize[2] = graphBounds.getMinX();
        graphSize[3] = graphBounds.getMinY();
        
        PSTricksGraphEditor.graph.draw(g2);
        
        if (selected instanceof Node){
            Rectangle2D selecterBounds = ((Node) selected).getBounds();
            drawSelector(g2, selecterBounds.getMinX(), selecterBounds.getMinY());
            drawSelector(g2, selecterBounds.getMinX(), selecterBounds.getMaxY());
            drawSelector(g2, selecterBounds.getMaxX(), selecterBounds.getMinY());
            drawSelector(g2, selecterBounds.getMaxX(), selecterBounds.getMaxY());
        }
        
        if (selected instanceof PSTLabel){
            Rectangle2D selecterBounds = ((PSTLabel) selected).getBounds();
            drawSelector(g2, selecterBounds.getMinX(), selecterBounds.getMinY());
            drawSelector(g2, selecterBounds.getMinX(), selecterBounds.getMaxY());
            drawSelector(g2, selecterBounds.getMaxX(), selecterBounds.getMinY());
            drawSelector(g2, selecterBounds.getMaxX(), selecterBounds.getMaxY());
        }
        
        if (selected instanceof Edge){
            if(selected instanceof LineEdge){
                Line2D line = new Line2D.Double(
                        ((LineEdge) selected).getConnectionPoints()[0],
                        ((LineEdge) selected).getConnectionPoints()[1]);
            
                drawSelector(g2, line.getX1(), line.getY1());
                drawSelector(g2, line.getX2(), line.getY2());
            }
            
            if(selected instanceof ArcEdge){
                drawSelector(g2, ((ArcEdge) selected).getConnectionPoints()[0].getX(), ((Edge) selected).getConnectionPoints()[0].getY());
                drawSelector(g2, ((ArcEdge) selected).getConnectionPoints()[1].getX(), ((Edge) selected).getConnectionPoints()[1].getY());
                drawSelector(g2, ((ArcEdge) selected).getCtrlPoint().getX(), ((ArcEdge) selected).getCtrlPoint().getY());
            }
            
            if(selected instanceof CircleEdge){
                drawSelector(g2, ((CircleEdge) selected).getConnectionPoints()[0].getX(),((CircleEdge) selected).getConnectionPoints()[0].getY());
                drawSelector(g2, ((CircleEdge) selected).getConnectionPoints()[1].getX(),((CircleEdge) selected).getConnectionPoints()[1].getY());
            }
        }
        
        if (selecterBandStart != null){
            Color oldColour = g2.getColor();
            g2.setColor(selectorColour);
            g2.setStroke(new BasicStroke(1,BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
            g2.draw(new Line2D.Double(selecterBandStart, lastMousePoint));
            g2.setColor(oldColour);
        }
    }  
    
    @Override
    public void mouseClicked(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void mousePressed(MouseEvent me) {
        //System.out.println("Pressed");
        Point2D mousePoint = me.getPoint();
        Node n = PSTricksGraphEditor.graph.findNode(mousePoint);
        Edge e = PSTricksGraphEditor.graph.findEdge(mousePoint);
        PSTLabel label = PSTricksGraphEditor.graph.findLabel(mousePoint);
        Object tool = PSTricksGraphEditor.gtb.getSelectedTool();
        //System.out.println(tool.toString());
        if (tool == null){
            if (e != null){
                selected = e;
            }else if (n != null){
                selected = n;
                dragStartPoint = mousePoint;
                dragStartBounds = n.getBounds();
            }else if(label != null){
                selected = label;
                dragStartPoint = mousePoint;
                dragStartBounds = label.getBounds();
            }else{
                selected = null;
            }
        }else if (tool instanceof Node){
            Node prototype = (Node) tool;
            Node newNode = (Node) prototype.clone();
            boolean added = PSTricksGraphEditor.graph.addNode(newNode, mousePoint);
            if (added){
                selected = newNode;
                dragStartPoint = mousePoint;
                dragStartBounds = newNode.getBounds();
                newNode.setNodeID("node" + Graph.nodeCounter);
                newNode.setLabel(newNode.getNodeID());
                //After add a new node change the default graph selection to 
                PSTricksGraphEditor.gtb.selectItem.setSelected(true);
                //System.out.println("New Node has ID: " + newNode.getNodeID());
            }else if (n != null){
                selected = n;
                dragStartPoint = mousePoint;
                dragStartBounds = n.getBounds();
            }
        }else if (tool instanceof Edge){
            selected = null;
            if (n != null) selecterBandStart = mousePoint;
        }else if (tool instanceof PSTLabel){
            PSTLabel prototype = (PSTLabel) tool;
            PSTLabel newLabel = (PSTLabel) prototype.clone();
            boolean added = PSTricksGraphEditor.graph.addLabel(newLabel, mousePoint);
            if (added) {
                selected = newLabel;
                dragStartPoint = mousePoint;
                dragStartBounds = newLabel.getBounds();
                //After add a new label change the default graph selection to
                PSTricksGraphEditor.gtb.selectItem.setSelected(true);
            }else if(label != null){
                selected = label;
                dragStartPoint = mousePoint;
                dragStartBounds = label.getBounds();
            }
        }
        lastMousePoint = mousePoint;
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        //System.out.println("Released");
        Object tool = PSTricksGraphEditor.gtb.getSelectedTool();
        if (selecterBandStart != null){
            Point2D mousePoint = me.getPoint();
            Edge prototype = (Edge) tool;
            Edge newEdge = (Edge) prototype.clone();
            if (PSTricksGraphEditor.graph.addEdge(newEdge,selecterBandStart, mousePoint)){
                //if(newEdge instanceof LineEdge){
                    selected = newEdge;
                //}else{
                //    selected = newEdge;
                    // when new edge is arc edge
                    // or circle edge, prevent user to add duplicated edges
                    // arc edge
                    PSTricksGraphEditor.gtb.selectItem.setSelected(true);
                //}
            }
        }
        
        revalidate();
        repaint();
            
        lastMousePoint = null;
        dragStartBounds = null;
        selecterBandStart = null;
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        mouseIn = true;
    }

    @Override
    public void mouseExited(MouseEvent me) {
        mouseIn = false;
        PSTricksGraphEditor.ptb.micePositionLabel.setText(" ");
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        //System.out.println("Draged");
        PSTricksGraphEditor.ptb.micePositionLabel.setText(" " + me.getX() + ":" + me.getY());
        Point2D mousePoint = me.getPoint();
        if (dragStartBounds != null){
            if (selected instanceof Node){
                Node n = (Node) selected;
                Rectangle2D bounds = n.getBounds();
                n.position((float) (dragStartBounds.getX() - bounds.getX() 
                        + mousePoint.getX() - dragStartPoint.getX()),
                        (float) (dragStartBounds.getY() - bounds.getY() 
                        + mousePoint.getY() - dragStartPoint.getY()));
            }else if(selected instanceof PSTLabel){
                PSTLabel label = (PSTLabel) selected;
                Rectangle2D bounds = label.getBounds();
                label.position(dragStartBounds.getX() - bounds.getX() 
                        + mousePoint.getX() - dragStartPoint.getX(),
                        dragStartBounds.getY() - bounds.getY() 
                        + mousePoint.getY() - dragStartPoint.getY());
            }
        }
                    
        if(selected instanceof ArcEdge){
            ArcEdge e = (ArcEdge) selected;
            e.setOnDrag(true);
            //e.setMouseDragePoint(mousePoint);
            e.setCtrlPoint(mousePoint);
        }
        
        lastMousePoint = mousePoint;
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        if(mouseIn){
            PSTricksGraphEditor.ptb.micePositionLabel.setText(" " + me.getX() + ":" + me.getY());
        }
    }

    /**
     * Edit selected graph object
     */
    public void editSelected(){
        PSTricksGraphEditor.ptb.setSelected(selected);
    }

    /**
     * Delete selected graph object
     */
    public void removeSelected(){
        if (selected instanceof Node){
            PSTricksGraphEditor.graph.removeNode((Node) selected);
        }else if (selected instanceof Edge){
            PSTricksGraphEditor.graph.removeEdge((Edge) selected);
        }else if (selected instanceof PSTLabel)
            PSTricksGraphEditor.graph.removeLabel((PSTLabel) selected);
        selected = null;
        repaint();
    }
    
    /**
     * Selection
     * @param g2
     * @param x
     * @param y 
     */
    public static void drawSelector(Graphics2D g2, double x, double y){
        final int SIZE = 4;
        Color oldColour = g2.getColor();
        g2.setColor(selectorColour);
        g2.fill(new Rectangle2D.Double(x - SIZE / 2,
         y - SIZE / 2, SIZE, SIZE));
        g2.setColor(oldColour);
     }
    
    @Override
     public Dimension getPreferredSize(){
        Rectangle2D bounds = PSTricksGraphEditor.graph.getBounds((Graphics2D) getGraphics());
        return new Dimension((int) bounds.getMaxX(), (int) bounds.getMaxY());
    }

    /**
     * @return the graphSize
     */
    public double[] getGraphSize() {
        return graphSize;
    }
}
