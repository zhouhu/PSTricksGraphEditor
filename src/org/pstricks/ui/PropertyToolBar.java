package org.pstricks.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import org.pstricks.graphobjects.AbstractEdge;
import org.pstricks.graphobjects.AbstractNode;
import org.pstricks.graphobjects.ArcEdge;
import org.pstricks.graphobjects.ArrowHead;
import org.pstricks.graphobjects.CircleEdge;
import org.pstricks.graphobjects.CircleNode;
import org.pstricks.graphobjects.DotNode;
import org.pstricks.graphobjects.Edge;
import org.pstricks.graphobjects.Graph;
import org.pstricks.graphobjects.Node;
import org.pstricks.graphobjects.PSTLabel;

/**
 *
 * @author zhouhu
 */
public final class PropertyToolBar{
    private JToolBar propertiesToolBar;
    
    // Show mouth position X, Y
    public JLabel micePositionLabel;
    
    private JButton property;
    
    private JButton layerUp;
    private JButton layerDown;
    private JButton layerTop;
    private JButton layerBottom;

    private JPanel colourPanel;
    private JButton blackBtn;
    private JButton darkgrayBtn;
    private JButton grayBtn;
    private JButton lightgrayBtn;
    private JButton whiteBtn;
    private JButton redBtn;
    private JButton greenBtn;
    private JButton blueBtn;
    private JButton cyanBtn;
    private JButton magentaBtn;
    private JButton yellowBtn;
    private JButton colourPicked;
    
    private String[] sizeValue;
    public JComboBox sizeValueChoice;
    public JComboBox dotStyleChoice;
    public JComboBox lineStyleChoice;
    public JComboBox arrowStyleStartChoice;
    public JComboBox arrowStyleEndChoice;
    private ComboBoxRender comboBoxRender;
    
    private Object selected;
    

    public PropertyToolBar(PSTricksGraphEditor window) {
        selected = null;
        propertiesToolBar = new JToolBar();
        propertiesToolBar.setOrientation(JToolBar.HORIZONTAL);
        propertiesToolBar.setFloatable(false);
        
        // Properties tool bar items
        
        micePositionLabel = new JLabel();
        micePositionLabel.setIcon(new ImageIcon(this.getClass().getResource("/icons/miceposition.png")));
        micePositionLabel.setPreferredSize(new Dimension(80,0));
        
        property = new JButton(new ImageIcon(this.getClass().getResource("/icons/property16.png")));
        property.setToolTipText("Edit Selected Graph Component Property");
        property.addActionListener(new PropertyBtnListener());
        
        LayerOperationListener layerListener = new LayerOperationListener();
        
        layerUp = new JButton(new ImageIcon(this.getClass().getResource("/icons/layersup.png")));
        layerUp.setToolTipText("Bring Selected Graph Component Layer Up");
        layerUp.addActionListener(layerListener);
        
        layerDown = new JButton(new ImageIcon(this.getClass().getResource("/icons/layersdown.png")));
        layerDown.setToolTipText("Put Seletcted Graph Component Layer Down");
        layerDown.addActionListener(layerListener);
        
        layerTop = new JButton(new ImageIcon(this.getClass().getResource("/icons/layerstop.png")));
        layerTop.setToolTipText("Bring Selected Graph Component to Top Layer");
        layerTop.addActionListener(layerListener);
        
        layerBottom = new JButton(new ImageIcon(this.getClass().getResource("/icons/layersbottom.png")));
        layerBottom.setToolTipText("Put Selected Graph Component to Bottom Layer");
        layerBottom.addActionListener(layerListener);
        
        // --- Color Picker --- //
        colourPanel = new JPanel();
        colourPanel.setLayout(new GridLayout(2,6,2,2));
        colourPanel.setBorder(new EmptyBorder(0,5,0,0));

        ColourBtnListener colourBtnListener = new ColourBtnListener();
        
        blackBtn = new JButton(new ImageIcon(this.getClass().getResource("/icons/colourbtn.png")));
        blackBtn.setOpaque(true);
        blackBtn.setBackground(Color.BLACK);
        blackBtn.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
        blackBtn.addActionListener(colourBtnListener);
        
        darkgrayBtn = new JButton(new ImageIcon(this.getClass().getResource("/icons/colourbtn.png")));
        darkgrayBtn.setOpaque(true);
        darkgrayBtn.setBackground(Color.DARK_GRAY);
        darkgrayBtn.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
        darkgrayBtn.addActionListener(colourBtnListener);
        
        grayBtn = new JButton(new ImageIcon(this.getClass().getResource("/icons/colourbtn.png")));
        grayBtn.setOpaque(true);
        grayBtn.setBackground(Color.GRAY);
        grayBtn.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
        grayBtn.addActionListener(colourBtnListener);
        
        lightgrayBtn = new JButton(new ImageIcon(this.getClass().getResource("/icons/colourbtn.png")));
        lightgrayBtn.setOpaque(true);
        lightgrayBtn.setBackground(Color.LIGHT_GRAY);
        lightgrayBtn.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
        lightgrayBtn.addActionListener(colourBtnListener);
        
        whiteBtn = new JButton(new ImageIcon(this.getClass().getResource("/icons/colourbtn.png")));
        whiteBtn.setOpaque(true);
        whiteBtn.setBackground(Color.WHITE);
        whiteBtn.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
        whiteBtn.addActionListener(colourBtnListener);
        
        redBtn = new JButton(new ImageIcon(this.getClass().getResource("/icons/colourbtn.png")));
        redBtn.setOpaque(true);
        redBtn.setBackground(Color.RED);
        redBtn.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
        redBtn.addActionListener(colourBtnListener);
        
        greenBtn = new JButton(new ImageIcon(this.getClass().getResource("/icons/colourbtn.png")));
        greenBtn.setOpaque(true);
        greenBtn.setBackground(Color.GREEN);
        greenBtn.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
        greenBtn.addActionListener(colourBtnListener);
        
        blueBtn = new JButton(new ImageIcon(this.getClass().getResource("/icons/colourbtn.png")));
        blueBtn.setOpaque(true);
        blueBtn.setBackground(Color.BLUE);
        blueBtn.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
        blueBtn.addActionListener(colourBtnListener);
        
        cyanBtn = new JButton(new ImageIcon(this.getClass().getResource("/icons/colourbtn.png")));
        cyanBtn.setOpaque(true);
        cyanBtn.setBackground(Color.CYAN);
        cyanBtn.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
        cyanBtn.addActionListener(colourBtnListener);
        
        magentaBtn = new JButton(new ImageIcon(this.getClass().getResource("/icons/colourbtn.png")));
        magentaBtn.setOpaque(true);
        magentaBtn.setBackground(Color.MAGENTA);
        magentaBtn.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
        magentaBtn.addActionListener(colourBtnListener);
        
        yellowBtn = new JButton(new ImageIcon(this.getClass().getResource("/icons/colourbtn.png")));
        yellowBtn.setOpaque(true);
        yellowBtn.setBackground(Color.YELLOW);
        yellowBtn.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
        yellowBtn.addActionListener(colourBtnListener);
        
        // This button's background displays ColourPicked
        colourPicked = new JButton(new ImageIcon(this.getClass().getResource("/icons/colourbtn.png")));
        colourPicked.setOpaque(true);
        colourPicked.setBackground(Color.BLACK);
        colourPicked.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
        colourPicked.addActionListener(colourBtnListener);
        
        
        colourPanel.add(blackBtn);
        colourPanel.add(darkgrayBtn);
        colourPanel.add(grayBtn);
        colourPanel.add(lightgrayBtn);
        colourPanel.add(whiteBtn);
        colourPanel.add(colourPicked);
        colourPanel.add(redBtn);
        colourPanel.add(greenBtn);
        colourPanel.add(blueBtn);
        colourPanel.add(cyanBtn);
        colourPanel.add(magentaBtn);
        colourPanel.add(yellowBtn);
        
        
        
        //--------------COMBO BOXES------------------//
        
        sizeValue = new String[10];
        for(int i = 0; i < sizeValue.length; i++){
            sizeValue[i] = "  " + (i+1);
        }        
        sizeValueChoice = new JComboBox(sizeValue);
        sizeValueChoice.setSelectedIndex(0);
        sizeValueChoice.setEditable(true);
        sizeValueChoice.addActionListener(new LinewidthEditeListner());
        
        
        
        
        
        
        comboBoxRender = new ComboBoxRender();
        
        // Dot style combo box
        dotStyleChoice = new JComboBox();
        dotStyleChoice.setRenderer(comboBoxRender);
        
        // None
        JLabel noneDotStyle = new JLabel(DotNode.DEFAULT_DOT);
        noneDotStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/dotstyle/none.png")));
        dotStyleChoice.addItem(noneDotStyle);
        
        // Asterisk
        JLabel asteriskDotStyle = new JLabel(DotNode.ASTERISK_DOT);
        asteriskDotStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/dotstyle/asterisk.png")));
        dotStyleChoice.addItem(asteriskDotStyle);
        
        // Bar
        JLabel barDotStyle = new JLabel(DotNode.BAR_DOT);
        barDotStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/dotstyle/bar.png")));
        dotStyleChoice.addItem(barDotStyle);
        
        // Cross
        JLabel timesDotStyle = new JLabel(DotNode.X_DOT);
        timesDotStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/dotstyle/cross.png")));
        dotStyleChoice.addItem(timesDotStyle);
        
        // Diamond
        JLabel diamondDotStyle = new JLabel(DotNode.DIAMOND_DOT);
        diamondDotStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/dotstyle/diamond.png")));
        dotStyleChoice.addItem(diamondDotStyle);
        
        // DiamondF
        JLabel diamondFDotStyle = new JLabel(DotNode.DIAMOND_F_DOT);
        diamondFDotStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/dotstyle/diamondF.png")));
        dotStyleChoice.addItem(diamondFDotStyle);
        
        // O
        JLabel oDotStyle = new JLabel(DotNode.O_DOT);
        oDotStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/dotstyle/o.png")));
        dotStyleChoice.addItem(oDotStyle);
        
        // O Cross
        JLabel otimesDotStyle = new JLabel(DotNode.OTIMES_DOT);
        otimesDotStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/dotstyle/ocross.png")));
        dotStyleChoice.addItem(otimesDotStyle);
        
        // O Plus
        JLabel oplusDotStyle = new JLabel(DotNode.OPLUS_DOT);
        oplusDotStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/dotstyle/oplus.png")));
        dotStyleChoice.addItem(oplusDotStyle);
        
        // Pentagon
        JLabel pentagonDotStyle = new JLabel(DotNode.PENTAGON_DOT);
        pentagonDotStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/dotstyle/pentagon.png")));
        dotStyleChoice.addItem(pentagonDotStyle);
        
        // Pentagon F
        JLabel pentagonfDotStyle = new JLabel(DotNode.PENTAGON_F_DOT);
        pentagonfDotStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/dotstyle/pentagonF.png")));
        dotStyleChoice.addItem(pentagonfDotStyle);
        
        // Plus
        JLabel plusDotStyle = new JLabel(DotNode.PLUS_DOT);
        plusDotStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/dotstyle/plus.png")));
        dotStyleChoice.addItem(plusDotStyle);
        
        // Square
        JLabel squareDotStyle = new JLabel(DotNode.SQUARE_DOT);
        squareDotStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/dotstyle/square.png")));
        dotStyleChoice.addItem(squareDotStyle);
        
        // Square F
        JLabel squarefDotStyle = new JLabel(DotNode.SQUARE_F_DOT);
        squarefDotStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/dotstyle/squareF.png")));
        dotStyleChoice.addItem(squarefDotStyle);
        
        // Triangle
        JLabel triangleDotStyle = new JLabel(DotNode.TRIANGLE_DOT);
        triangleDotStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/dotstyle/triangle.png")));
        dotStyleChoice.addItem(triangleDotStyle);
        
        // Triangle F
        JLabel trianglefDotStyle = new JLabel(DotNode.TRIANGLE_F_DOT);
        trianglefDotStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/dotstyle/triangleF.png")));
        dotStyleChoice.addItem(trianglefDotStyle);
        
        // Set default selection
        dotStyleChoice.setSelectedItem(noneDotStyle);
        dotStyleChoice.addActionListener(new DotStyleChoiceListener());

        
        // Line style combo box
        lineStyleChoice = new JComboBox();
        lineStyleChoice.setRenderer(comboBoxRender);
        
        // Solid
        JLabel solidLineStyle = new JLabel(AbstractEdge.SOLID_STYLE);
        solidLineStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/linestyle/solid.png")));
        lineStyleChoice.addItem(solidLineStyle);
        // Dashed
        JLabel dashedLineStyle = new JLabel(AbstractEdge.DASHED_STYLE);
        dashedLineStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/linestyle/dashed.png")));
        lineStyleChoice.addItem(dashedLineStyle);
        // Dotted
        JLabel dottedLineStyle = new JLabel(AbstractEdge.DOTTED_STYLE);
        dottedLineStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/linestyle/dotted.png")));
        lineStyleChoice.addItem(dottedLineStyle);
        // Line style choice default value
        lineStyleChoice.setSelectedItem(solidLineStyle);
        lineStyleChoice.addActionListener(new LineStyleListner());
        
        ArrowStyleChoiceListener arrowStyleListner = new ArrowStyleChoiceListener();
        
        // Left arrow style combo box
        arrowStyleStartChoice = new JComboBox();
        arrowStyleStartChoice.setRenderer(comboBoxRender);
        
        
        // None
        JLabel noneLeftArrowStyle = new JLabel(ArrowHead.DEFAULT_ARROW);
        noneLeftArrowStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/arrowstyle/none_left.png")));
        arrowStyleStartChoice.addItem(noneLeftArrowStyle);
        
        // Arc
        JLabel arcLeftArrowStyle = new JLabel(ArrowHead.LEFT_ROUND_BRACKET);
        arcLeftArrowStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/arrowstyle/arc_left.png")));
        arrowStyleStartChoice.addItem(arcLeftArrowStyle);
        
        // arrow
        JLabel arrowLeftArrowStyle = new JLabel(ArrowHead.LEFT_ARROW);
        arrowLeftArrowStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/arrowstyle/arrow_left.png")));
        arrowStyleStartChoice.addItem(arrowLeftArrowStyle);
        
        // Bar end
        JLabel barendLeftArrowStyle = new JLabel(ArrowHead.BAR_END_ARROW);
        barendLeftArrowStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/arrowstyle/bar_end_left.png")));
        arrowStyleStartChoice.addItem(barendLeftArrowStyle);
        
        // Bar In
        JLabel barinLeftArrowStyle = new JLabel(ArrowHead.BAR_IN_ARROW);
        barinLeftArrowStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/arrowstyle/bar_in_left.png")));
        arrowStyleStartChoice.addItem(noneLeftArrowStyle);
        
        // Bracket
        JLabel bracketLeftArrowStyle = new JLabel(ArrowHead.LEFT_SQUARE_BRACKET);
        bracketLeftArrowStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/arrowstyle/bracket_left.png")));
        arrowStyleStartChoice.addItem(bracketLeftArrowStyle);
        
        // Circle end
        JLabel circleendLeftArrowStyle = new JLabel(ArrowHead.CIRCLE_END_ARROW);
        circleendLeftArrowStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/arrowstyle/circle_end_left.png")));
        arrowStyleStartChoice.addItem(circleendLeftArrowStyle);
        
        // Circle in
        JLabel circleinLeftArrowStyle = new JLabel(ArrowHead.CIRCLE_IN_ARROW);
        circleinLeftArrowStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/arrowstyle/circle_in_left.png")));
        arrowStyleStartChoice.addItem(circleinLeftArrowStyle);
        
        // Double arrow
        JLabel dblearrowLeftArrowStyle = new JLabel(ArrowHead.DOUBLE_LEFT_ARROW);
        dblearrowLeftArrowStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/arrowstyle/dbleArrow_left.png")));
        arrowStyleStartChoice.addItem(dblearrowLeftArrowStyle);
        
        // Disk end
        JLabel diskendLeftArrowStyle = new JLabel(ArrowHead.DISK_END_ARROW);
        diskendLeftArrowStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/arrowstyle/disk_end_left.png")));
        arrowStyleStartChoice.addItem(diskendLeftArrowStyle);
        
        // Disk in
        JLabel diskinLeftArrowStyle = new JLabel(ArrowHead.DISK_IN_ARROW);
        diskinLeftArrowStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/arrowstyle/disk_in_left.png")));
        arrowStyleStartChoice.addItem(diskinLeftArrowStyle);
        
        // Reverse arrow
        JLabel rarrowLeftArrowStyle = new JLabel(ArrowHead.RIGHT_ARROW);
        rarrowLeftArrowStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/arrowstyle/rarrow_left.png")));
        arrowStyleStartChoice.addItem(rarrowLeftArrowStyle);
        
        // Reverse double arrow
        JLabel rdblearrowLeftArrowStyle = new JLabel(ArrowHead.DOUBLE_RIGHT_ARROW);
        rdblearrowLeftArrowStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/arrowstyle/rdbleArrow_left.png")));
        arrowStyleStartChoice.addItem(rdblearrowLeftArrowStyle);
        
        // Round in
        JLabel roundinLeftArrowStyle = new JLabel(ArrowHead.ROUND_IN_ARROW);
        roundinLeftArrowStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/arrowstyle/round_in_left.png")));
        arrowStyleStartChoice.addItem(roundinLeftArrowStyle);
        
        arrowStyleStartChoice.addActionListener(arrowStyleListner);
        
        
        
        
        // Right arrow style combo box
        arrowStyleEndChoice = new JComboBox();
        arrowStyleEndChoice.setRenderer(comboBoxRender);
        
        
        // None
        JLabel noneRightArrowStyle = new JLabel(ArrowHead.DEFAULT_ARROW);
        noneRightArrowStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/arrowstyle/none_right.png")));
        arrowStyleEndChoice.addItem(noneRightArrowStyle);
        
        // Arc
        JLabel arcRightArrowStyle = new JLabel(ArrowHead.RIGHT_ROUND_BRACKET);
        arcRightArrowStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/arrowstyle/arc_right.png")));
        arrowStyleEndChoice.addItem(arcRightArrowStyle);
        
        // arrow
        JLabel arrowRightArrowStyle = new JLabel(ArrowHead.RIGHT_ARROW);
        arrowRightArrowStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/arrowstyle/arrow_right.png")));
        arrowStyleEndChoice.addItem(arrowRightArrowStyle);
        
        // Bar end
        JLabel barendRightArrowStyle = new JLabel(ArrowHead.BAR_END_ARROW);
        barendRightArrowStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/arrowstyle/bar_end_right.png")));
        arrowStyleEndChoice.addItem(barendRightArrowStyle);
        
        // Bar In
        JLabel barinRightArrowStyle = new JLabel(ArrowHead.BAR_IN_ARROW);
        barinRightArrowStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/arrowstyle/bar_in_right.png")));
        arrowStyleEndChoice.addItem(barinRightArrowStyle);
        
        // Bracket
        JLabel bracketRightArrowStyle = new JLabel(ArrowHead.RIGHT_SQUARE_BRACKET);
        bracketRightArrowStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/arrowstyle/bracket_right.png")));
        arrowStyleEndChoice.addItem(bracketRightArrowStyle);
        
        // Circle end
        JLabel circleendRightArrowStyle = new JLabel(ArrowHead.CIRCLE_END_ARROW);
        circleendRightArrowStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/arrowstyle/circle_end_right.png")));
        arrowStyleEndChoice.addItem(circleendRightArrowStyle);
        
        // Circle in
        JLabel circleinRightArrowStyle = new JLabel(ArrowHead.CIRCLE_IN_ARROW);
        circleinRightArrowStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/arrowstyle/circle_in_right.png")));
        arrowStyleEndChoice.addItem(circleinRightArrowStyle);
        
        // Double arrow
        JLabel dblearrowRightArrowStyle = new JLabel(ArrowHead.DOUBLE_RIGHT_ARROW);
        dblearrowRightArrowStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/arrowstyle/dbleArrow_right.png")));
        arrowStyleEndChoice.addItem(dblearrowRightArrowStyle);
        
        // Disk end
        JLabel diskendRightArrowStyle = new JLabel(ArrowHead.DISK_END_ARROW);
        diskendRightArrowStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/arrowstyle/disk_end_right.png")));
        arrowStyleEndChoice.addItem(diskendRightArrowStyle);
        
        // Disk in
        JLabel diskinRightArrowStyle = new JLabel(ArrowHead.DISK_IN_ARROW);
        diskinRightArrowStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/arrowstyle/disk_in_right.png")));
        arrowStyleEndChoice.addItem(diskinRightArrowStyle);
        
        // Reverse arrow
        JLabel rarrowRightArrowStyle = new JLabel(ArrowHead.LEFT_ARROW);
        rarrowRightArrowStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/arrowstyle/rarrow_right.png")));
        arrowStyleEndChoice.addItem(rarrowRightArrowStyle);
        
        // Reverse double arrow
        JLabel rdblearrowRightArrowStyle = new JLabel(ArrowHead.DOUBLE_LEFT_ARROW);
        rdblearrowRightArrowStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/arrowstyle/rdbleArrow_right.png")));
        arrowStyleEndChoice.addItem(rdblearrowRightArrowStyle);
        
        // Round in
        JLabel roundinRightArrowStyle = new JLabel(ArrowHead.ROUND_IN_ARROW);
        roundinRightArrowStyle.setIcon(new ImageIcon(this.getClass().getResource("/icons/arrowstyle/round_in_right.png")));
        arrowStyleEndChoice.addItem(roundinRightArrowStyle);
        
        arrowStyleEndChoice.addActionListener(arrowStyleListner);
        
        // Layout the properties tool bar items
        propertiesToolBar.add(colourPanel);
        propertiesToolBar.addSeparator();
        propertiesToolBar.add(sizeValueChoice);
        propertiesToolBar.add(dotStyleChoice);
        propertiesToolBar.add(lineStyleChoice);
        propertiesToolBar.add(arrowStyleStartChoice);
        propertiesToolBar.add(arrowStyleEndChoice);
        propertiesToolBar.addSeparator();
        layerUp.setEnabled(false);
        propertiesToolBar.add(layerUp);
        layerDown.setEnabled(false);
        propertiesToolBar.add(layerDown);
        layerTop.setEnabled(false);
        propertiesToolBar.add(layerTop);
        layerBottom.setEnabled(false);
        propertiesToolBar.add(layerBottom);
        propertiesToolBar.addSeparator();
        propertiesToolBar.add(property);
        propertiesToolBar.addSeparator();
        propertiesToolBar.add(micePositionLabel);
    }
    
    
    public JToolBar getToolBar(){
        return propertiesToolBar;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(Object selected) {
        this.selected = selected;
    }
    
    class PropertyBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            PSTricksGraphEditor.canvas.editSelected();
            if(selected != null){
                if(selected instanceof Node){
                    JTextField comments = new JTextField();
                    comments.setText(((Node) selected).getComments());
                    comments.selectAll();
                    String dialogTitle = "Edit Node Label and Comments";
                    JTextField label = new JTextField();
                    label.setText(((Node) selected).getLabel());
                    label.selectAll();
                    
                    JComboBox position = new JComboBox();
                    String right = "Right";
                    String left = "Left";
                    String up = "Up";
                    String down = "Down";
                    String upLeft = "Up Left";
                    String upRight = "Up Right";
                    String downLeft = "Down Left";
                    String downRight = "Down Right";
                    String currentPosition = upLeft;
                    position.addItem(right);
                    position.addItem(left);
                    position.addItem(up);
                    position.addItem(down);
                    position.addItem(upLeft);
                    position.addItem(upRight);
                    position.addItem(downLeft);
                    position.addItem(downRight);
                    String current = ((Node) selected).getLabelPosition();
                    if(current.equals(AbstractNode.LABEL_POSITION_RIGHT)){
                        currentPosition = right;
                    } else if (current.equals(AbstractNode.LABEL_POSITION_DOWN)){
                        currentPosition = down;
                    } else if (current.equals(AbstractNode.LABEL_POSITION_UP)){
                        currentPosition = up;
                    } else if (current.equals(AbstractNode.LABEL_POSITION_LEFT)){
                        currentPosition = left;
                    } else if (current.equals(AbstractNode.LABEL_POSITION_UP_LEFT)){
                        currentPosition = upLeft;
                    } else if (current.equals(AbstractNode.LABEL_POSITION_UP_RIGHT)){
                        currentPosition = upRight;
                    } else if (current.equals(AbstractNode.LABEL_POSITION_DOWN_LEFT)){
                        currentPosition = downLeft;
                    } else if (current.equals(AbstractNode.LABEL_POSITION_DOWN_RIGHT)){
                        currentPosition = downRight;
                    }
                    position.setSelectedItem(currentPosition);   
                    final JComponent[] inputs;
                    if(selected instanceof DotNode){
                        dialogTitle = "Edit Dot Node Label, Label Position and Comments";
                        inputs = new JComponent[] {
                            new JLabel("Change Node Label:"),
                            label,
                            new JLabel("Change Label Position:"),
                            position,
                            new JLabel("Add Comments"),
                            comments
                        };
                    } else {
                        inputs = new JComponent[] {
                            new JLabel("Change Node Label"),
                            label,
                            new JLabel("Add Comments"),
                            comments,
                        };
                    }
                    
                    JOptionPane.showMessageDialog(null, inputs, dialogTitle, JOptionPane.PLAIN_MESSAGE);
                    ((Node) selected).setLabel(label.getText());
                    
                    if(selected instanceof DotNode){
                        if(position.getSelectedItem().equals(up)){
                            ((DotNode) selected).setLabelPosition(AbstractNode.LABEL_POSITION_UP);
                        } else if (position.getSelectedItem().equals(down)){
                            ((DotNode) selected).setLabelPosition(AbstractNode.LABEL_POSITION_DOWN);
                        } else if (position.getSelectedItem().equals(left)){
                            ((DotNode) selected).setLabelPosition(AbstractNode.LABEL_POSITION_LEFT);
                        } else if (position.getSelectedItem().equals(right)){
                            ((DotNode) selected).setLabelPosition(AbstractNode.LABEL_POSITION_RIGHT);
                        } else if (position.getSelectedItem().equals(downLeft)){
                            ((DotNode) selected).setLabelPosition(AbstractNode.LABEL_POSITION_DOWN_LEFT);
                        } else if (position.getSelectedItem().equals(downRight)){
                            ((DotNode) selected).setLabelPosition(AbstractNode.LABEL_POSITION_DOWN_RIGHT);
                        } else if (position.getSelectedItem().equals(upRight)){
                            ((DotNode) selected).setLabelPosition(AbstractNode.LABEL_POSITION_UP_RIGHT);
                        } else if (position.getSelectedItem().equals(upLeft)){
                            ((DotNode) selected).setLabelPosition(AbstractNode.LABEL_POSITION_UP_LEFT);
                        }
                    }
                    
                    if(!comments.getText().isEmpty()){
                        if(comments.getText().charAt(0) != '%')
                            ((Node) selected).setComments("% " + comments.getText());
                        else
                            ((Node) selected).setComments(comments.getText());
                    }
                      
                    PSTricksGraphEditor.canvas.repaint();
                        
                }else if (selected instanceof Edge){
                    
                    JTextField label = new JTextField();
                    label.setText(((Edge) selected).getLabel());
                    label.selectAll();
                    JTextField comments = new JTextField();
                    comments.setText(((Edge) selected).getComments());
                    comments.selectAll();
                    JComboBox position = new JComboBox();
                    String above = "Above The Edge";
                    String on = "On The Edge";
                    String below = "Below The Edge";
                    String currentPosition = above;
                    position.addItem(above);
                    position.addItem(on);
                    position.addItem(below);
                    String current = ((Edge) selected).getLabelPosition();
                    if(current.equals(AbstractEdge.LABEL_POSITION_ABOVE))
                        currentPosition = above;
                    else if(current.equals(AbstractEdge.LABEL_POSITION_ON))
                        currentPosition = on;
                    else
                        currentPosition = below;
                    position.setSelectedItem(currentPosition);
                    
                    final JComponent[] inputs;
                    String dialogTitle = "Edit Edge Label";
                    JTextField arcAngle = new JTextField();
                    JTextField circleR = new JTextField();
                    if(selected instanceof ArcEdge){
                        arcAngle.setText(((ArcEdge) selected).getArcAngle()+"");
                        arcAngle.selectAll();
                        dialogTitle = "Edit Arc Edge Label and Angle";
                        inputs = new JComponent[] {
                            new JLabel("Change Label Text:"),
                            label,
                            new JLabel("Change Label Position"),
                            position,
                            new JLabel("Change Arc Angle"),
                            //new JLabel("Value range: -30 to 30"),
                            arcAngle,
                            new JLabel("Add comments"),
                            comments
                        };
                    } else if (selected instanceof CircleEdge){
                        circleR.setText(((CircleEdge) selected).getCodeR()+"");
                        circleR.selectAll();
                        dialogTitle = "Edit Circle Edge Label and Radius";
                        inputs = new JComponent[] {
                            new JLabel("Change Label Text:"),
                            label,
                            new JLabel("Change Label Position"),
                            position,
                            new JLabel("Change Circle Radius"),
                            //new JLabel("Value range: -30 to 30"),
                            circleR,
                            new JLabel("Add comments"),
                            comments
                        };
                    } else {
                        inputs = new JComponent[] {
                            new JLabel("Change Label Text:"),
                            label,
                            new JLabel("Change Label Position"),
                            position,
                            new JLabel("Add Comments"),
                            comments
                        };
                    }
                    
                    JOptionPane.showMessageDialog(null, inputs, dialogTitle, JOptionPane.PLAIN_MESSAGE);
                    ((Edge) selected).setLabel(label.getText());
                    
                    if(position.getSelectedItem().equals(on)){
                        ((Edge) selected).setLabelPosition(AbstractEdge.LABEL_POSITION_ON);
                    } else if(position.getSelectedItem().equals(above)){
                        ((Edge) selected).setLabelPosition(AbstractEdge.LABEL_POSITION_ABOVE);
                    } else {
                        ((Edge) selected).setLabelPosition(AbstractEdge.LABEL_POSITION_BELOW);
                    }
                    
                    if(selected instanceof ArcEdge){
                        double angle = ((ArcEdge) selected).getArcAngle();
                        try{
                            angle = Double.parseDouble(arcAngle.getText());
                        } catch (NumberFormatException e){
                            
                        }
                        
                        ((ArcEdge) selected).setArcAngle(angle);
                        
                        /*
                        if(angle>30.0){
                            ((ArcEdge) selected).setArcAngle(30);
                        } else if (angle<-30.0){
                            ((ArcEdge) selected).setArcAngle(-30);
                        } else {
                            ((ArcEdge) selected).setArcAngle(angle);
                        }
                        */
                    } else if (selected instanceof CircleEdge){
                        double r = ((CircleEdge) selected).getCodeR();
                        try{
                            r = Double.parseDouble(circleR.getText());
                        } catch (NumberFormatException e){
                            
                        }
                        ((CircleEdge) selected).setCodeR(r);
                    }
                    if(!comments.getText().isEmpty()){
                        if(comments.getText().charAt(0) != '%')
                            ((Edge) selected).setComments("% " + comments.getText());
                        else
                            ((Edge) selected).setComments(comments.getText());
                    }
                    
                    PSTricksGraphEditor.canvas.repaint();
                } else if (selected instanceof PSTLabel){
                    JTextField label = new JTextField();
                    label.setText(((PSTLabel) selected).getText());
                    label.selectAll();
                    JTextField comments = new JTextField();
                    comments.setText(((PSTLabel) selected).getComments());
                    comments.selectAll();
                    final JComponent[] inputs = new JComponent[] {
                        new JLabel("Change Label Text:"),
                        label,
                        new JLabel("Add Comments"),
                        comments
                    };
                    JOptionPane.showMessageDialog(null, inputs, "Edit Label Text", JOptionPane.PLAIN_MESSAGE);
                    
                    if(!comments.getText().isEmpty()){
                        if(comments.getText().charAt(0) != '%')
                            ((PSTLabel) selected).setComments("% " + comments.getText());
                        else
                            ((PSTLabel) selected).setComments(comments.getText());
                    }
                    
                    // label must have text 
                    if(!label.getText().isEmpty()){
                        ((PSTLabel) selected).setText(label.getText());
                        PSTricksGraphEditor.canvas.repaint();
                    }
                }
            }
        }
        
    }

    class LayerOperationListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            PSTricksGraphEditor.canvas.editSelected();
            if(selected != null){
                if(selected instanceof Node){
                    Object src = ae.getSource();
                    Node n = (Node) selected;
                    if(src == layerUp){
                        PSTricksGraphEditor.graph.changeNodeLayer(n, Graph.NODE_MOVE_UP);
                    } else if (src == layerDown){
                        PSTricksGraphEditor.graph.changeNodeLayer(n, Graph.NODE_MOVE_DOWN);
                    } else if (src == layerTop){
                        PSTricksGraphEditor.graph.changeNodeLayer(n, Graph.NODE_MOVE_TOP);
                    } else if (src == layerBottom){
                        PSTricksGraphEditor.graph.changeNodeLayer(n, Graph.NODE_MOVE_BOTTOM);
                    }
                    PSTricksGraphEditor.canvas.repaint();
                }
            }
        }
        
    }
    
    class DotStyleChoiceListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            PSTricksGraphEditor.canvas.editSelected();
            if(selected != null){
                if (selected instanceof DotNode){
                    String dotStyle = ((JLabel) dotStyleChoice.getSelectedItem()).getText();
                    ((DotNode) selected).setDotStyle(dotStyle);
                    PSTricksGraphEditor.canvas.repaint();
                } 
            }
        }
        
    }
    class ArrowStyleChoiceListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            PSTricksGraphEditor.canvas.editSelected();
            if(selected != null){
                if(selected instanceof Edge){
                    String arrowStyle = "-";
                    Edge edge = (Edge) selected;
                    ArrowHead arrow = new ArrowHead();
                    if(ae.getSource() == arrowStyleStartChoice){
                        arrowStyle = ((JLabel) arrowStyleStartChoice.getSelectedItem()).getText();
                        arrow.setArrowStyle(arrowStyle);
                        edge.setStartArrow(arrow);
                    }
                    
                    if(ae.getSource() == arrowStyleEndChoice){
                        arrowStyle = ((JLabel) arrowStyleEndChoice.getSelectedItem()).getText();
                        arrow.setArrowStyle(arrowStyle);
                        edge.setEndArrow(arrow);
                    }
                    PSTricksGraphEditor.canvas.repaint();
                }
            }
        }
        
    }
    
    class LinewidthEditeListner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            PSTricksGraphEditor.canvas.editSelected();
            //sizeValueChoice.
            // Selected index
            int i = sizeValueChoice.getSelectedIndex();
            
            if(selected != null){
                float value = 1;
                
                if(i>=0){
                    value = i+1;
                }else{
                    try{
                        value = Float.parseFloat
                                ((String)(sizeValueChoice.getSelectedItem()));
                    }catch(NumberFormatException e){
                        
                    }
                }
                
                if (value <= 0){
                    value = 1;
                }
                
                //System.out.println("Selected");
                if(selected instanceof Edge){
                    ((Edge) selected).setLinewidth(value);
                    //System.out.println("Edge");
                } else if (selected instanceof CircleNode){
                    ((CircleNode) selected).setLinewidth(value);
                    //System.out.println("Node");
                } else if (selected instanceof Node){
                    ((Node) selected).setSize(value);
                }
                PSTricksGraphEditor.canvas.repaint();
            }//else{
                //System.out.println("NOT Selected");
            //}
        }

    }
    
    class ColourBtnListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
            PSTricksGraphEditor.canvas.editSelected();
            if(ae.getSource() == blackBtn){
                colourPicked.setBackground(Color.BLACK);
            }else if(ae.getSource() == darkgrayBtn){
                colourPicked.setBackground(Color.DARK_GRAY);
            }else if(ae.getSource() == grayBtn){
                colourPicked.setBackground(Color.GRAY);
            }else if(ae.getSource() == lightgrayBtn){
                colourPicked.setBackground(Color.LIGHT_GRAY);
            }else if(ae.getSource() == whiteBtn){
                colourPicked.setBackground(Color.WHITE);
            }else if(ae.getSource() == redBtn){
                colourPicked.setBackground(Color.RED);
            }else if(ae.getSource() == greenBtn){
                colourPicked.setBackground(Color.GREEN);
            }else if(ae.getSource() == blueBtn){
                colourPicked.setBackground(Color.BLUE);
            }else if(ae.getSource() == cyanBtn){
                colourPicked.setBackground(Color.CYAN);
            }else if(ae.getSource() == magentaBtn){
                colourPicked.setBackground(Color.MAGENTA);
            }else if(ae.getSource() == yellowBtn){
                colourPicked.setBackground(Color.YELLOW);
            }else{
                colourPicked.setBackground(colourPicked.getBackground());
            }

            if(selected != null){
                //System.out.println("Selected");
                if(selected instanceof Edge){
                    ((Edge) selected).setLineColour(colourPicked.getBackground());
                    //System.out.println("Edge");
                } else if (selected instanceof Node){
                    ((Node) selected).setNodeColour(colourPicked.getBackground());
                    //System.out.println("Node");
                }
                PSTricksGraphEditor.canvas.repaint();
            }//else{
                //System.out.println("NOT Selected");
            //}
        }

    }

    class LineStyleListner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            PSTricksGraphEditor.canvas.editSelected();

            if(selected != null){
                //System.out.println("Selected");
                if(selected instanceof Edge){
                    ((Edge) selected).setLineStyle(((JLabel) lineStyleChoice.getSelectedItem()).getText());
                }else if(selected instanceof CircleNode){
                    ((CircleNode) selected).setLineStyle(((JLabel) lineStyleChoice.getSelectedItem()).getText());
                }
                PSTricksGraphEditor.canvas.repaint();
            }//else{
                //System.out.println("NOT Selected");
            //}
        }
    }
    
    /**
     * Customize combo box to show image icons
     */
    private class ComboBoxRender extends JLabel implements ListCellRenderer{

        public ComboBoxRender() {
            setOpaque(true);
            setHorizontalAlignment(CENTER);
            setVerticalAlignment(CENTER);
        }
        
        /**
         * Find image icons corresponding to the selected value
         * and returns the label, display the image
         * @param jlist
         * @param o
         * @param i
         * @param isSelected
         * @param cellHasFocus
         * @return 
         */

        @Override
        public Component getListCellRendererComponent(JList jlist, 
                                                        Object o, 
                                                        int i, 
                                                        boolean isSelected, 
                                                        boolean cellHasFocus) {

            //int selectedIndex = ((Integer)o).intValue();
            
            if(isSelected){
                setBackground(jlist.getSelectionBackground());
                setForeground(jlist.getSelectionForeground());
            } else {
                setBackground(jlist.getBackground());
                setForeground(jlist.getForeground());
            }
            
            // TEST //System.out.println(o.getClass().toString());
            // Set the image icon, must use JLabel to set as the list item
            if(o instanceof JLabel){
                setIcon((ImageIcon)((JLabel)o).getIcon());
            }
                       
            return this;
        }
        
    }
}
