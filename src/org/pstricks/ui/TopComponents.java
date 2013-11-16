/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pstricks.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.pstricks.core.LaTeXDocument;
import org.pstricks.graphobjects.Graph;
import org.pstricks.ui.dialogframes.PSTricksCodeViewer;
import org.pstricks.ui.dialogframes.PreviewPDF;

/**
 * General Components: Main Menu Bar and Main Tool Bar
 * @author zhouhu
 */
public final class TopComponents {
    private final PSTricksGraphEditor window;
    
    // Menu font
    private final Font font = new Font(Font.SANS_SERIF, 0, 12);
    
    // Show zoom percent
    public JLabel zoomLabel;
    // Main menu bar
    private JMenuBar mainMenuBar = new JMenuBar();
    
    // Menu menu bar menus
    private JMenu fileMenu = new JMenu("File");
    private JMenu editMenu = new JMenu("Edit");
    private JMenu viewMenu = new JMenu("View");
    private JMenu helpMenu = new JMenu("Help");
    
    // File menu items
    private JMenuItem newFile = new JMenuItem("New");
    private JMenuItem open = new JMenuItem("Open");
    private JMenuItem save = new JMenuItem("Save");
    private JMenuItem saveAs = new JMenuItem("Save as .tex");
    private JMenuItem exit = new JMenuItem("Exit");
    
    // Edit menu items
    private JMenuItem undo = new JMenuItem("Undo");
    private JMenuItem redo = new JMenuItem("Redo");
    private JMenuItem copy = new JMenuItem("Copy");
    private JMenuItem past = new JMenuItem("Past");
    private JMenuItem cut = new JMenuItem("Cut");
    private JMenuItem delete = new JMenuItem("Delete");
    private JMenuItem selectAll = new JMenuItem("Select All");
        
    
    // View menu items
    private JCheckBoxMenuItem grid = new JCheckBoxMenuItem("Grid");
    private JMenuItem zoomIn = new JMenuItem("Zoom In");
    private JMenuItem zoomOut = new JMenuItem("Zoom Out");
    private JMenuItem unzoom = new JMenuItem("Un-Zoom");
    private JMenuItem preview = new JMenuItem("Preview");
    private JMenuItem viewCode = new JMenuItem("Code");
    
    // Help menu items
    private JMenuItem help = new JMenuItem("Help");
    private JMenuItem about = new JMenuItem("About");
    
    
    
    // Main tool bar
    private JToolBar mainToolBar;
    
    // Tool bar items
    private JButton _newFile;
    private JButton _open;
    private JButton _save;
    private JButton _undo;
    private JButton _redo;
    private JButton _cut;
    private JButton _copy;
    private JButton _past;
    private JButton _delete;
    private JToggleButton _grid;
    private JButton _zoomIn;
    private JButton _zoomOut;
    private JButton _unzoom;
    private JButton _preview;
    private JButton _viewCode;
    
    
    // Item Icon (grid icon will be changed, so here set grid icon only)
    ImageIcon ungrid24 = new ImageIcon(this.getClass().getResource("/icons/ungrid24.png"));
    ImageIcon grid24 = new ImageIcon(this.getClass().getResource("/icons/grid24.png"));
    

    public TopComponents(PSTricksGraphEditor window) {
        this.window = window;
        
        zoomLabel = new JLabel(" 100% ");
        zoomLabel.setPreferredSize(new Dimension(150,0));
        zoomLabel.setForeground(new Color(0,128,0));
        zoomLabel.setFont(new Font(Font.SANS_SERIF, 10, 24));
        // Initialise/implements all menu items
        fileMenu.setFont(font);
        fileMenu.setMnemonic('f');
        editMenu.setFont(font);
        editMenu.setMnemonic('e');
        viewMenu.setFont(font);
        viewMenu.setMnemonic('v');
        helpMenu.setFont(font);
        helpMenu.setMnemonic('h');
                
        // File menu items implements
        FileMenuListener fileListener = new FileMenuListener();
                
        // New File
        newFile.setIcon(new ImageIcon(this.getClass().getResource("/icons/newfile16.png")));
        newFile.setFont(font);
        // Keybord shortcuts: Ctrl + N
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        newFile.setActionCommand("NEW_FILE");
        newFile.addActionListener(fileListener);
        
        // Open
        open.setIcon(new ImageIcon(this.getClass().getResource("/icons/open16.png")));
        open.setFont(font);
        // Keybord shortcuts: Ctrl + O
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        open.setActionCommand("OPEN");
        open.addActionListener(fileListener);        
        
        // Save
        save.setIcon(new ImageIcon(this.getClass().getResource("/icons/save16.png")));
        save.setFont(font);
        // Keybord shortcuts: Ctrl + S
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        save.setActionCommand("SAVE");
        save.addActionListener(fileListener);
        
        // Save As...
        saveAs.setIcon(new ImageIcon(this.getClass().getResource("/icons/saveas16.png")));
        saveAs.setFont(font);
        // Keybord shortcuts: Shift + S
        saveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.SHIFT_MASK | Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        saveAs.setActionCommand("SAVE_AS");
        saveAs.addActionListener(fileListener);
        
        // Exit
        exit.setIcon(new ImageIcon(this.getClass().getResource("/icons/exit16.png")));
        exit.setFont(font);
        exit.setActionCommand("EXIT");
        exit.addActionListener(fileListener);
        
        
        // Edit menu items implements
        EditMenuListener editListener = new EditMenuListener();
        
        // Undo
        undo.setIcon(new ImageIcon(this.getClass().getResource("/icons/undo16.png")));
        undo.setFont(font);
        // Keybord shortcuts: Ctrl + Z
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        undo.setActionCommand("UNDO");
        undo.addActionListener(editListener);
        
        // Redo
        redo.setIcon(new ImageIcon(this.getClass().getResource("/icons/redo16.png")));
        redo.setFont(font);
        // Keybord shortcuts: Ctrl + Y
        redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        redo.setActionCommand("REDO");
        redo.addActionListener(editListener);
        
        // Copy
        copy.setIcon(new ImageIcon(this.getClass().getResource("/icons/copy16.png")));
        copy.setFont(font);
        // Keybord shortcuts: Ctrl + C
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        copy.setActionCommand("COPY");
        copy.addActionListener(editListener);
        
        // Past
        past.setIcon(new ImageIcon(this.getClass().getResource("/icons/past16.png")));
        past.setFont(font);
        // Keybord shortcuts: Ctrl + V
        past.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        past.setActionCommand("PAST");
        past.addActionListener(editListener);
        
        // Cut
        cut.setIcon(new ImageIcon(this.getClass().getResource("/icons/cut16.png")));
        cut.setFont(font);
        // Keybord shortcuts: Ctrl + X
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        cut.setActionCommand("CUT");
        cut.addActionListener(editListener);
        
        // Delete
        delete.setIcon(new ImageIcon(this.getClass().getResource("/icons/delete16.png")));
        delete.setFont(font);
        // Keybord shortcuts: BACK_SPACE
        delete.setAccelerator(KeyStroke.getKeyStroke(Event.DELETE, 0));
        delete.setActionCommand("DELETE");
        delete.addActionListener(editListener);
        
        // Select All
        selectAll.setFont(font);
        // Keybord shortcuts: Ctrl + A
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        selectAll.setActionCommand("SELECT_ALL");
        selectAll.addActionListener(editListener);
        
        
        // View menu items implements
        ViewMenuListener viewListener = new ViewMenuListener();
        
        // Grid
        grid.setIcon(new ImageIcon(this.getClass().getResource("/icons/grid16.png")));
        grid.setFont(font);
        // Keybord shortcuts: Ctrl + G
        grid.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        grid.setSelected(true);
        grid.setActionCommand("GRID");
        grid.addActionListener(viewListener);
        
        // Zoom In
        zoomIn.setIcon(new ImageIcon(this.getClass().getResource("/icons/zoomin16.png")));
        zoomIn.setFont(font);
        // Keybord shortcuts: + (symbol)
        zoomIn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, 0));
        zoomIn.setActionCommand("ZOOM_IN");
        zoomIn.addActionListener(viewListener);
        
        // Zoom Out
        zoomOut.setIcon(new ImageIcon(this.getClass().getResource("/icons/zoomout16.png")));
        zoomOut.setFont(font);
        // Keybord shortcuts: - (symbol)
        zoomOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, 0));
        zoomOut.setActionCommand("ZOOM_OUT");
        zoomOut.addActionListener(viewListener);
        
        // Un-Zoom
        unzoom.setIcon(new ImageIcon(this.getClass().getResource("/icons/unzoom16.png")));
        unzoom.setFont(font);
        // Keybord shortcuts: 0 (number)
        unzoom.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, 0));
        unzoom.setActionCommand("UNZOOM");
        unzoom.addActionListener(viewListener);
        
        // Preview
        preview.setIcon(new ImageIcon(this.getClass().getResource("/icons/preview16.png")));
        preview.setFont(font);
        preview.setActionCommand("PREVIEW");
        preview.addActionListener(viewListener);
        
        // View Code
        viewCode.setIcon(new ImageIcon(this.getClass().getResource("/icons/viewcode16.png")));
        viewCode.setFont(font);
        viewCode.setActionCommand("CODE");
        viewCode.addActionListener(viewListener);
        
        // Help menu items implements
        HelpMenuListener helpListener = new HelpMenuListener();
        
        // About
        about.setIcon(new ImageIcon(this.getClass().getResource("/icons/about16.png")));
        about.setFont(font);
        about.setActionCommand("ABOUT");
        about.addActionListener(helpListener);
        
        // Help
        help.setIcon(new ImageIcon(this.getClass().getResource("/icons/help16.png")));
        help.setFont(font);
        // Keybord shortcuts: F1
        help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        help.setActionCommand("HELP");
        help.addActionListener(helpListener);
        
        
        // Layout menu items
        
        // File menu
        fileMenu.add(newFile);
        //open.setEnabled(false);
        fileMenu.add(open);
        //save.setEnabled(false);
        fileMenu.add(save);
        fileMenu.add(saveAs);
        fileMenu.addSeparator();
        fileMenu.add(exit);
        
        // Edit menu
        // Undo Redo will not be implemented at first version
        editMenu.add(undo);
        undo.setEnabled(false);
        editMenu.add(redo);
        redo.setEnabled(false);
        editMenu.addSeparator();
        editMenu.add(cut);
        cut.setEnabled(false);
        editMenu.add(copy);
        copy.setEnabled(false);
        editMenu.add(past);
        past.setEnabled(false);
        editMenu.add(delete);
        editMenu.addSeparator();
        editMenu.add(selectAll);
        selectAll.setEnabled(false);
        
        // View menu
        viewMenu.add(grid);
        viewMenu.addSeparator();
        //Zoom funcion will not be implemented at first version
        viewMenu.add(zoomIn);
        zoomIn.setEnabled(false);
        viewMenu.add(zoomOut);
        zoomOut.setEnabled(false);
        viewMenu.add(unzoom);
        unzoom.setEnabled(false);
        viewMenu.addSeparator();
        preview.setEnabled(false);
        viewMenu.add(preview);
        viewMenu.add(viewCode); 
        
        // Help menu
        helpMenu.add(about);
        helpMenu.add(help);
        
        // Layout menus
        mainMenuBar.add(fileMenu);
        mainMenuBar.add(editMenu);
        mainMenuBar.add(viewMenu);
        mainMenuBar.add(helpMenu);
        
        
        
        
        
        mainToolBar = new JToolBar();
        mainToolBar.setOrientation(JToolBar.HORIZONTAL);
        mainToolBar.setFloatable(false);
        
        // Initialise toolbar items
        
        // New File
        _newFile = new JButton(new ImageIcon(this.getClass().getResource("/icons/newfile24.png")));
        _newFile.setToolTipText("New File");
        //_newFile.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        _newFile.setActionCommand("NEW_FILE");
        _newFile.addActionListener(fileListener);
        
        // Open
        _open = new JButton(new ImageIcon(this.getClass().getResource("/icons/open24.png")));
        _open.setToolTipText("Open");
        //_open.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        _open.setActionCommand("OPEN");
        _open.addActionListener(fileListener);
        
        // Save
        _save = new JButton(new ImageIcon(this.getClass().getResource("/icons/save24.png")));
        _save.setToolTipText("Save");
        //_save.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        _save.setActionCommand("SAVE");
        _save.addActionListener(fileListener);
        
        // Undo
        _undo = new JButton(new ImageIcon(this.getClass().getResource("/icons/undo24.png")));
        _undo.setToolTipText("Undo");
        //_undo.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        _undo.setActionCommand("UNDO");
        _undo.addActionListener(editListener);
        
        // Redo
        _redo = new JButton(new ImageIcon(this.getClass().getResource("/icons/redo24.png")));
        _redo.setToolTipText("Redo");
        //_redo.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        _redo.setActionCommand("REDO");
        _redo.addActionListener(editListener);
        
        
        // Cut
        _cut = new JButton(new ImageIcon(this.getClass().getResource("/icons/cut24.png")));
        _cut.setToolTipText("Cut");
        _cut.setActionCommand("CUT");
        _cut.addActionListener(editListener);
        
        // Copy
        _copy = new JButton(new ImageIcon(this.getClass().getResource("/icons/copy24.png")));
        _copy.setToolTipText("Copy");
        _copy.setActionCommand("COPY");
        _copy.addActionListener(editListener);
        
        // Past
        _past = new JButton(new ImageIcon(this.getClass().getResource("/icons/past24.png")));
        _past.setToolTipText("Past");
        _past.setActionCommand("PAST");
        _past.addActionListener(editListener);
        
        // Delete
        _delete = new JButton(new ImageIcon(this.getClass().getResource("/icons/delete24.png")));
        _delete.setToolTipText("Delete Selected Graph Object");
        _delete.setActionCommand("DELETE");
        _delete.addActionListener(editListener);
        
        // Grid
        _grid = new JToggleButton(grid24);
        _grid.setToolTipText("Grid");
        _grid.setSelected(true);
        //_grid.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        _grid.setActionCommand("GRID");
        _grid.addActionListener(viewListener);
        
        // Zoom In
        _zoomIn = new JButton(new ImageIcon(this.getClass().getResource("/icons/zoomin24.png")));
        _zoomIn.setToolTipText("Zoom In");
        //_zoomIn.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        _zoomIn.setActionCommand("ZOOM_IN");
        _zoomIn.addActionListener(viewListener);
        
        // Zoom Out
        _zoomOut = new JButton(new ImageIcon(this.getClass().getResource("/icons/zoomout24.png")));
        _zoomOut.setToolTipText("Zoom Out");
        //_zoomOut.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        _zoomOut.setActionCommand("ZOOM_OUT");
        _zoomOut.addActionListener(viewListener);
        
        // Un-Zoom
        _unzoom = new JButton(new ImageIcon(this.getClass().getResource("/icons/unzoom24.png")));
        _unzoom.setToolTipText("Un-Zoom");
        //_unzoom.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        _unzoom.setActionCommand("UNZOOM");
        _unzoom.addActionListener(viewListener);
        
        // Preview
        _preview = new JButton(new ImageIcon(this.getClass().getResource("/icons/preview24.png")));
        _preview.setToolTipText("Preview in PDF");
        //_preview.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        _preview.setActionCommand("PREVIEW");
        _preview.addActionListener(viewListener);
        
        // View Code
        _viewCode = new JButton(new ImageIcon(this.getClass().getResource("/icons/viewcode24.png")));
        _viewCode.setToolTipText("View PSTricks Code");
        //_viewCode.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        _viewCode.setActionCommand("CODE");
        _viewCode.addActionListener(viewListener);
        
        
        // Layout too bar items
        mainToolBar.add(_newFile);
        //_open.setEnabled(false);
        mainToolBar.add(_open);
        //_save.setEnabled(false);
        mainToolBar.add(_save);
        mainToolBar.addSeparator();
        // Undo Redo will not be implemented at first version
        mainToolBar.add(_undo);
        _undo.setEnabled(false);
        mainToolBar.add(_redo);
        _redo.setEnabled(false);
        mainToolBar.addSeparator();
        mainToolBar.add(_cut);
        _cut.setEnabled(false);
        mainToolBar.add(_copy);
        _copy.setEnabled(false);
        mainToolBar.add(_past);
        _past.setEnabled(false);
        mainToolBar.add(_delete);
        mainToolBar.addSeparator();
        mainToolBar.add(_grid);
        mainToolBar.addSeparator();
        // Zoom function will not be implmented at first version
        mainToolBar.add(_zoomIn);
        _zoomIn.setEnabled(false);
        mainToolBar.add(_zoomOut);
        _zoomOut.setEnabled(false);
        mainToolBar.add(_unzoom);
        _unzoom.setEnabled(false);
        mainToolBar.add(zoomLabel);
        mainToolBar.addSeparator();
        mainToolBar.add(_preview);
        _preview.setEnabled(false);
        mainToolBar.add(_viewCode);
    }
    
    /**
     * Get main menu
     * @return main menu bar 
     */
    public JMenuBar getMenuBar(){
        return mainMenuBar;
    }
    
    /**
     * Get main tool bar
     * @return main tool bar
     */
    public JToolBar getToolBar(){
        return mainToolBar;
    }
   
    /**
     * Open File Dialog
     */
    private void openFile(){
        JFileChooser fileChooser = new JFileChooser();
        int i = fileChooser.showOpenDialog(window);
        if (i == JFileChooser.APPROVE_OPTION) {
            try{
                File file = fileChooser.getSelectedFile();
                ObjectInputStream in = new ObjectInputStream(
                                    new FileInputStream(file));
                Graph graph = (Graph) in.readObject();
                PSTricksGraphEditor.graph = graph;
                in.close();
                PSTricksGraphEditor.canvas.validate();
                PSTricksGraphEditor.canvas.repaint();
                } catch (IOException exception){
                    JOptionPane.showMessageDialog(null, exception);
                } catch (ClassNotFoundException exception){
                    JOptionPane.showMessageDialog(null,exception);
                }
        }
    }
    
    /**
     * Save File / Save As Dialog
     */
    
    private void saveFile(){
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(window) == JFileChooser.APPROVE_OPTION){
            try{
                File file = fileChooser.getSelectedFile();
                ObjectOutputStream out = new ObjectOutputStream(
                                         new FileOutputStream(file+".pst"));
                out.writeObject(PSTricksGraphEditor.graph);
                out.close();
            }catch (IOException exception){
                    JOptionPane.showMessageDialog(null,exception);
            }
        }
    }
    
    private void saveFileAs(){
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TeX files/.tex", ".tex");
        fileChooser.setFileFilter(filter);
        String content = new LaTeXDocument().getDocumentContents();
        byte buffer[] = content.getBytes();
        if (fileChooser.showSaveDialog(window) == JFileChooser.APPROVE_OPTION){
            try{
                File file = fileChooser.getSelectedFile();
                OutputStream out = new FileOutputStream(file+".tex");
                out.write(buffer);
                out.close();
            }catch (IOException exception){
                    JOptionPane.showMessageDialog(null,exception);
            }
        }
    }
    
    /**
     * New File Dialog
     */
    private void newFile(){
        
        if(PSTricksGraphEditor.graph.getNodes().isEmpty()){
            return;
        }
        
        int reponse = getResponse();
        
        if(reponse == JOptionPane.YES_OPTION){
            saveFile();
            PSTricksGraphEditor.graph = new Graph();
            Graph.nodeCounter = 0;
            PSTricksGraphEditor.canvas.validate();
            PSTricksGraphEditor.canvas.selected = null;
            PSTricksGraphEditor.canvas.repaint();
        } else if (reponse == JOptionPane.CANCEL_OPTION){
            return;
        } else {
            PSTricksGraphEditor.graph = new Graph();
            Graph.nodeCounter = 0;
            PSTricksGraphEditor.canvas.validate();
            PSTricksGraphEditor.canvas.selected = null;
            PSTricksGraphEditor.canvas.repaint();
        }
    }
    
    public void quit(){
        if(PSTricksGraphEditor.graph.getNodes().isEmpty()){
            System.exit(0);
        } else {
            
            int reponse = getResponse();
            
            if(reponse == JOptionPane.YES_OPTION){
                saveFile();
                System.exit(0);
            } else if (reponse == JOptionPane.NO_OPTION){
                System.exit(0);
            } else {
                return;
            }
        }
    }
    
    /**
     * When click new file or exit, ask user to save current work
     * @return 
     */
    private int getResponse(){
       return JOptionPane.showConfirmDialog(null,
                "Do you want to save current graph?", "Save?", 
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
    }
    
    ///////////////// Items actions implements
    
    /**
     * FileMenuListener Class
     * Implements file menu actions
     */
    private class FileMenuListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            // Get Action Source
            Object src = ae.getSource();
            if(src == open || src == _open){
                openFile();
                return;
            }
            
            if (src == save || src == _save){
                saveFile();
                return;
            }
            
            if (src == saveAs){
                saveFileAs();
                return;
            }
            
            if (src == newFile || src == _newFile){
                newFile();
                return;
            }
            
            if (src == exit){
                quit();
            }
        }
        
    }
    
    /**
     * EditMenuListener Class
     * Implements edit menu actions
     */
    private class EditMenuListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            Object src = ae.getSource();
            if(src == delete || src == _delete){
                PSTricksGraphEditor.canvas.removeSelected();
            }
        }
        
    }
    
    /**
     * ViewMenuListener Class
     * Implements view menu actions
     */
    private class ViewMenuListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            Object src = ae.getSource();
            if(src == grid){
                if(grid.isSelected()){
                    _grid.setSelected(true);
                    _grid.setIcon(grid24);
                    PSTricksGraphEditor.canvas.grid.gridOn = true;
                    PSTricksGraphEditor.canvas.repaint();
                } else{
                    _grid.setSelected(false);
                    _grid.setIcon(ungrid24);
                    PSTricksGraphEditor.canvas.grid.gridOn = false;
                    PSTricksGraphEditor.canvas.repaint();
                }
            }
            
            if(src == _grid){
                if(_grid.isSelected()){
                    grid.setSelected(true);
                    _grid.setIcon(grid24);
                    PSTricksGraphEditor.canvas.grid.gridOn = true;
                    PSTricksGraphEditor.canvas.repaint();
                } else {
                    grid.setSelected(false);
                    _grid.setIcon(ungrid24);
                    PSTricksGraphEditor.canvas.grid.gridOn = false;
                    PSTricksGraphEditor.canvas.repaint();
                }
            }
            
            if(src == viewCode || src == _viewCode){
               PSTricksCodeViewer cv = new PSTricksCodeViewer(window);
               cv.setVisible(true);
            }
            
            if(src == preview || src == _preview){
                PreviewPDF pdf = new PreviewPDF(window);
                pdf.view();
                pdf.setVisible(true);
            }
        }
        
    }
    
    /**
     * HelpMenuListener Class
     * Implements help menu actions
     */
    private class HelpMenuListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            Object src = ae.getSource();
            if(src == help){
                String message = "Online: >> http://grapheditor.pstricks.org/help";
                JOptionPane pane = new JOptionPane(message);
                JDialog dialog = pane.createDialog(window, "Help");
                dialog.setVisible(true);
            }
            
            if(src == about){
                String message = "PSTricks Graph Editor\n"
                        + "Graphical User Interface for Drawing PSTricks Graphs\n"
                        + "A student project from Computer Science Department\n"
                        + "     @ University of Liverpool: >> http://csc.liv.ac.uk\n"
                        + "Project Homepage: >> http://grapheditor.pstricks.org";
                JOptionPane pane = new JOptionPane(message);
                JDialog dialog = pane.createDialog(window, "About");
                dialog.setVisible(true);
            }
        }  
    }    
}
