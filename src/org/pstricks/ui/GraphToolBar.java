package org.pstricks.ui;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import org.pstricks.graphobjects.Edge;
import org.pstricks.graphobjects.PSTLabel;
import org.pstricks.graphobjects.Node;
//import javax.swing.border.SoftBevelBorder;

/**
 *
 * @author zhouhu
 */
public final class GraphToolBar {
    private JToolBar graphToolBar;
    
    private ButtonGroup btnGroup;
    private ArrayList tools;
    private ArrayList btns;
    JToggleButton selectItem;
    private JToggleButton circleNodeItem;
    private JToggleButton dotnodeItem;
    private JToggleButton nclineItem;
    private JToggleButton ncarcItem;
    private JToggleButton nccircleItem;
    private JToggleButton uputItem;
    

    public GraphToolBar(PSTricksGraphEditor window) {
        graphToolBar = new JToolBar();
        graphToolBar.setOrientation(JToolBar.VERTICAL);
        graphToolBar.setBorderPainted(true);
        graphToolBar.setFloatable(true);
        
        btnGroup = new ButtonGroup();
        tools = new ArrayList();
        
        // Items        
        // Select Item
        selectItem = new JToggleButton(
                new ImageIcon(
                this.getClass().getResource(
                "/icons/graphelements/selection.png")));
        selectItem.setToolTipText("Selection");
        //selectItem.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        
        
        // Node Items //
        
        
        // Dot Node Item
        dotnodeItem = new JToggleButton(
                new ImageIcon(
                this.getClass().getResource(
                "/icons/graphelements/dotnode.png")));
        dotnodeItem.setToolTipText("Add a Dot Node");
        
        // Circle Node Item
        circleNodeItem = new JToggleButton(
                new ImageIcon(
                this.getClass().getResource(
                "/icons/graphelements/circlenode.png")));
        circleNodeItem.setToolTipText("Add a Circle Node");
        
        
        // Node Connection Items //
        
        
        // Node Connection Line Item
        nclineItem = new JToggleButton(
                new ImageIcon(
                this.getClass().getResource(
                "/icons/graphelements/ncline-ends-with-arrow.png")));
        nclineItem.setToolTipText("Connect Nodes with Straight Line");
        
        
        // Node Connection Arc Item
        ncarcItem = new JToggleButton(
                new ImageIcon(
                this.getClass().getResource(
                "/icons/graphelements/ncarc.png")));
        ncarcItem.setToolTipText("Connect Nodes with Arc");
        
        // Node Connection Circle Item
        nccircleItem = new JToggleButton(
                new ImageIcon(
                this.getClass().getResource(
                "/icons/graphelements/nccircle.png")));
        nccircleItem.setToolTipText("Connect Nodes with Circle");
        
        // Label uput Item
        uputItem = new JToggleButton(
                new ImageIcon(
                this.getClass().getResource(
                "/icons/graphelements/label-text.png")));
        uputItem.setToolTipText("Add Text Label");
        
        // Keep this order
        btnGroup.add(selectItem);
        btnGroup.add(dotnodeItem);
        btnGroup.add(circleNodeItem);
        btnGroup.add(nclineItem);
        btnGroup.add(ncarcItem);
        btnGroup.add(nccircleItem);
        btnGroup.add(uputItem);
        
        selectItem.setSelected(true);
        tools.add(null);
        
        Node[] nodeTypes = PSTricksGraphEditor.graph.getNodePrototypes();
        for (int i = 0; i < nodeTypes.length; i++){
            addTools(nodeTypes[i]);
        }
        
        Edge[] edgeTypes = PSTricksGraphEditor.graph.getEdgePrototypes();
        for(int i = 0; i < edgeTypes.length; i++){
            addTools(edgeTypes[i]);
        }
        
        PSTLabel[] labelTypes = PSTricksGraphEditor.graph.getLabelPrototypes();
        for(int i = 0; i < labelTypes.length; i++){
            addTools(labelTypes[i]);
        }
        
        
        // Layout items
        graphToolBar.add(selectItem);
        graphToolBar.addSeparator();
        //graphToolBar.add(new JLabel("Nodes"));
        graphToolBar.add(dotnodeItem);
        graphToolBar.add(circleNodeItem);
        graphToolBar.addSeparator();
        //graphToolBar.add(new JLabel("Edges"));
        graphToolBar.add(nclineItem);
        graphToolBar.add(ncarcItem);
        graphToolBar.add(nccircleItem);
        graphToolBar.addSeparator();
        graphToolBar.add(uputItem);
    }

    void addTools(Node n){
        tools.add(n);
    }
    
    void addTools(Edge e){
        tools.add(e);
    }
    
    void addTools(PSTLabel lt){
        tools.add(lt);
    }
    /**
     * Get the selected graph tool
     * @return 
     */
    public Object getSelectedTool(){
        btns = new ArrayList();
        btns = Collections.list(btnGroup.getElements());
        
        for (int i = 0; i < tools.size(); i++){
            JToggleButton button = (JToggleButton)btns.get(i);
            if (button.isSelected()) return tools.get(i);
        }
        return null;
   }
    
    public JToolBar getToolBar(){
        return graphToolBar;
    }
}
