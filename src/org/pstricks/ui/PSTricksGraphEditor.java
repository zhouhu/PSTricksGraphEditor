package org.pstricks.ui;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import org.pstricks.graphobjects.Graph;

/**
 * The main window (Entry) of this program 
 * @author zhouhu
 */
public final class PSTricksGraphEditor extends JFrame {
    
    // The main window
    public static final PSTricksGraphEditor window = new PSTricksGraphEditor();
    // Graph Container
    public static Graph graph = new Graph();
    // Graph Canvas, user draw graphics on it
    public static final GraphCanvas canvas = new GraphCanvas(window);
    // Top Components: main menu bar and main tool bar
    public static final TopComponents tc = new TopComponents(window);
    // Property Tool Bar, for modifing selected graph parameters
    public static final PropertyToolBar ptb = new PropertyToolBar(window);
    // Graph Tool Bar (Selector, Edges, and Nodes)
    public static final GraphToolBar gtb = new GraphToolBar(window);
    // For Scrolling canvas
    public static final JScrollPane canvasScroll = new JScrollPane(canvas);
    
    public PSTricksGraphEditor() {
        setTitle("PSTricks Graph Editor");
        setLayout(new BorderLayout());
        setSize(800,600);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        //setIconImage(Toolkit.getDefaultToolkit().getImage("../icons/appIcon.png"));
        // Centre the window location
        setLocationRelativeTo(null);
    }
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Lay out the user interface componnents
        
        // Panel on the top (north), contains main menu bar and main tool bar
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(tc.getMenuBar(), BorderLayout.NORTH);
        topPanel.add(tc.getToolBar(), BorderLayout.SOUTH);
        // Top panel at north
        window.add(topPanel, BorderLayout.NORTH);
        // Graph tool bar on the left (west)
        window.add(gtb.getToolBar(), BorderLayout.WEST);
        // Properties too bar on the bottom (south)
        window.add(ptb.getToolBar(), BorderLayout.SOUTH);
        // GraphCanvas at centre
        window.add(canvasScroll);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                window.setVisible(true);
            }
        });
        
        window.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent winEvt) {
                tc.quit();
            }
        });
    }
}
