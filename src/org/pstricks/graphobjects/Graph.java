package org.pstricks.graphobjects;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.pstricks.ui.PSTricksGraphEditor;

/**
 * Graph contains all nodes and edges
 * @author zhouhu
 */
public final class Graph implements Serializable{
    // change to linked list in next version
    private ArrayList nodes;
    private ArrayList edges;
    private ArrayList labels;
    public static final int NODE_MOVE_UP = 1;
    public static final int NODE_MOVE_DOWN = 2;
    public static final int NODE_MOVE_TOP = 3;
    public static final int NODE_MOVE_BOTTOM = 3;
    

    // node counter, for display default c node name
    public static int nodeCounter = 0;

    public Graph() {
        nodes   = new ArrayList();
        edges   = new ArrayList();
        labels  = new ArrayList();
    }
    
    /**
     * Add an Edge
     * @param e
     * @param p1
     * @param p2
     * @return 
     */
    public boolean addEdge(Edge e, Point2D p1, Point2D p2){
      
      Node node1 = findNode(p1);
      Node node2 = findNode(p2);
      
      if(node1 != null && node2 != null){
          if(e instanceof CircleEdge){
              // when edge type is  circle edge, linked 2 nodes must same
              if(node1.equals(node2)){
                  e.connect(node1, node2);
                  edges.add(e);
                  return true;
              }
          } else {
              // when edge type is not circle edge, linked 2 nodes must different
              if(!node1.equals(node2)){
                  e.connect(node1, node2);
                  edges.add(e);
                  return true;
              }
          }
      }

      return false;
    }
    
    /**
     * Add a node
     * @param n
     * @param p
     * @return 
     */
    public boolean addNode(Node n, Point2D p){
        Rectangle2D bounds = n.getBounds();
        n.position((float) p.getX() - (float) bounds.getX(),(float) p.getY() - (float) bounds.getY()); 
        nodes.add(n);
        return true;
    }
    
    /**
     * Move node up / down, top / bottom in the node array list
     * @param n
     * @param move 
     */
    public void changeNodeLayer(Node n, int move){
        return;
        /*
        int i = nodes.indexOf(n);
        //System.out.println("am here "+ move);
        if(move == NODE_MOVE_UP){
            if(i > 0){
                Object temp = nodes.get(i-1);
                nodes.add(i-1, n);
                nodes.add(i,temp);
                System.out.println("move up");
            }
        } else if (move == NODE_MOVE_DOWN){
            if(i < nodes.size()-1){
                Object temp = nodes.get(i+1);
                nodes.add(i+1, n);
                nodes.add(i,temp);
                System.out.println("move down");
            }
        }
         */
    }
    
    /**
     * Add a label
     * @param label
     * @param p
     * @return 
     */
    public boolean addLabel(PSTLabel label, Point2D p){
        Rectangle2D bounds = label.getBounds();
        label.position(p.getX() - bounds.getX(), p.getY() - bounds.getY());
        labels.add(label);
        return true;
    }
    
    /**
     * Find a node at given point
     * @param p
     * @return 
     */
    public Node findNode(Point2D p){
        for (int i = nodes.size() - 1; i >= 0; i--){
            Node n = (Node) nodes.get(i);
            if (n.contains(p)) return n;
        }
        return null;
    }
    
    /**
     * Find an edge at given point
     * @param p
     * @return 
     */
    public Edge findEdge(Point2D p){
        for (int i = edges.size() - 1; i >= 0; i--){
            Edge e = (Edge) edges.get(i);
            if (e.contains(p)) return e;
        }
        return null;
    }
    
    /**
     * Find a label at given point
     * @param p
     * @return 
     */
    public PSTLabel findLabel(Point2D p){
        for (int i = labels.size() - 1; i >= 0; i--){
            PSTLabel label = (PSTLabel) labels.get(i);
            if (label.contains(p)) return label;
        }
        return null;
    }
    
    /**
     * Draw the graph
     * @param g2 
     */
    public void draw(Graphics2D g2){
        for (int i = 0; i < nodes.size(); i++){
            Node n = (Node) nodes.get(i);
            n.draw(g2);
        }
        
        for (int i = 0; i < edges.size(); i++){
            Edge e = (Edge) edges.get(i);
            e.draw(g2);
        }
        
        for (int i = 0; i < labels.size(); i++){
            PSTLabel label = (PSTLabel) labels.get(i);
            label.draw(g2);
        }
    }
    
    /**
     * Remove a node, and also remove the node connected edges
     * @param n 
     */
    public void removeNode(Node n){
        for (int i = edges.size() - 1; i >= 0; i--){
            Edge e = (Edge) edges.get(i);
            if (e.getStart() == n || e.getEnd() == n){
                edges.remove(e);
            }
        }
        
        nodes.remove(n);
    }
    
    /**
     * Remove an edge
     * @param e 
     */
    public void removeEdge(Edge e){
        edges.remove(e);
    }
    
    /**
     * Remove a label
     * @param label 
     */
    public void removeLabel(PSTLabel label){
        labels.remove(label);
    }
    
    /**
     * Get the bounds of the graph,(including all nodes and edges bounds)
     * @param g2
     * @return 
     */
    public Rectangle2D getBounds(Graphics2D g2){
        Rectangle2D r = null;
        for (int i = 0; i < nodes.size(); i++){
            Node n = (Node) nodes.get(i);
            Rectangle2D b = n.getBounds();
            if (r == null) r = b;
            else r.add(b);
        }
        for (int i = 0; i < edges.size(); i++){
            Edge e = (Edge) edges.get(i);
            r.add(e.getBounds(g2));
        }
        for (int i = 0; i < labels.size(); i++){
            PSTLabel label = (PSTLabel) labels.get(i);
            Rectangle2D b = label.getBounds();
            if(r == null) r = b;
            else r.add(b);
        }
        return r == null ? new Rectangle2D.Double() : r;
    }
    
    /**
     * Get the node type, (dot, circle, frame ...)
     * @return 
     */
    public Node[] getNodePrototypes(){
        Node[] nodeTypes = {
           new DotNode(),
           new CircleNode()
         };
        return nodeTypes;
    }
    
    /**
     * Get the edge type, (line, arc, circle ...)
     * @return 
     */
    public Edge[] getEdgePrototypes(){
        Edge[] edgeTypes = {
           new LineEdge(),
           new ArcEdge(),
           new CircleEdge()
        };
        return edgeTypes;
    }
    
    /**
     * Get the label type, (uput label)
     * @return 
     */
    public PSTLabel[] getLabelPrototypes(){
        PSTLabel[] labelTypes = {
            new UPutLabel()
        };
        return labelTypes;
    }
    
    /**
     * Get all nodes of this graph
     * @return unmodifiable nodes list
     */
    public List getNodes(){
        return Collections.unmodifiableList(nodes);
    }
    
    /**
     * Get all edges of this graph
     * @return unmodifiable edge list
     */
    public List getEdges(){
        return Collections.unmodifiableList(edges);
    }
    
    /**
     * Get all labels of this graph
     * @return unmodifiable label list
     */
    public List getLabels(){
        return Collections.unmodifiableList(labels);
    }
    
     /**
     * Return PSTricks code
     * @return 
     */
    public String getCode() {
        String newLine = System.getProperty("line.separator");
        String code = "%" + newLine;
        code += "%PSTricks Graph Editor" + newLine;        
        code += "%\\usepackage{pstricks-add} % PSTricks package" + newLine;
        code += "%\\usepackage{graphicx} % Graphicx package, used for rescale the graph" + newLine;
        code += "%" + newLine;
        double maxX = 0;
        double maxY = 0;
        double minX = 0;
        double minY = 0;
        double[] graphSize = PSTricksGraphEditor.canvas.getGraphSize();
        if(graphSize != null){
            maxX = graphSize[0]/50;
            maxY = graphSize[1]/50;
            minX = graphSize[2]/50;
            minY = graphSize[3]/50;
        }
        //String gridStr = "\\psgrid(0,0)(-1,"+(int)(0-1-(maxY-minY)/2)+")("+
        //        (int)(maxX-minX+1)+","+(int)(((maxY-minY)/2)+1)+")" + newLine;
        String gridStr = "\\psgrid(0,0)(-1,-1)("+
                (int)(Math.floor(maxX-minX+1))+","+(int)(Math.floor(maxY-minY+1))+")" + newLine;
        
        if(!PSTricksGraphEditor.canvas.grid.gridOn){
            gridStr = "%" + gridStr;
        }
        code += "\\psset{}" + newLine;
        code += "\\scalebox{1} % Rescale the Graph." + newLine;
        code += "{" + newLine;
        //String codeStart = "\\begin{pspicture}("+ 0 +"," + ((maxY-minY)/2) + ")(" +
        //        (maxX-minX) + "," + (0-(maxY-minY)/2) + ")";
        String codeStart = "\\begin{pspicture}(-1,-1)(" +
                (int)(Math.floor(maxX-minX+1)) + "," + (int)(Math.floor(maxY-minY+1)) + ")";
        
        code += codeStart + newLine;
        code += gridStr;
        
        if(graphSize != null){
            for(int i = 0; i < getNodes().size(); i++){
                code += ((Node)getNodes().get(i)).getCode() + newLine;
            }

            for(int i = 0; i < getEdges().size(); i++){
                code += ((Edge) getEdges().get(i)).getCode() + newLine;
            }
            
            for(int i = 0; i < getLabels().size(); i++){
                code += ((PSTLabel) getLabels().get(i)).getCode() +newLine;
            }
            
        }
        
        String codeEnd = "\\end{pspicture}" + newLine;
        code += codeEnd;
        code += "}";
        
        return code;
    }
}