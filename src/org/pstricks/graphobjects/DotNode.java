/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pstricks.graphobjects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author zhouhu
 */
public class DotNode extends AbstractNode{
    
    
    /**
     * \psdot[dotstyle=*] the default value
     */ 
    public static final String DEFAULT_DOT  = "*";
    
    /**
     * \psdot[dotstyle=oplus]
     */
    public static final String OPLUS_DOT    = "oplus";

    /**
     * \psdot[dotstyle=otimes]
     */
    public static final String OTIMES_DOT   = "otimes";
    
    /**
     * \psdot[dotstyle=+]
     */
    public static final String PLUS_DOT     = "+";

    /**
     * \psdot[dotstyle=asterisk]
     */
    public static final String ASTERISK_DOT = "asterisk";

    /**
     * \psdot[dotstyle=triangle]
     */
    public static final String TRIANGLE_DOT = "triangle";

    /**
     * \psdot[dotstyle=square]
     */
    public static final String SQUARE_DOT   = "square";

    /**
     * \psdot[dotstyle=diamond]
     */
    public static final String DIAMOND_DOT  = "diamond";

    /**
     * \psdot[dotstyle=pentagon]
     */
    public static final String PENTAGON_DOT = "pentagon";

    /**
     * \psdot[dotstyle=o]
     */
    public static final String O_DOT        = "o";

    /**
     * \psdot[dotstyle=x]
     */
    public static final String X_DOT        = "x";

    /**
     * \psdot[dotstyle=|]
     */
    public static final String BAR_DOT      = "|";

    /**
     * \psdot[dotstyle=triangle*]
     */
    public static final String TRIANGLE_F_DOT   = "triangle*";

    /**
     * \psdot[dotstyle=square*]
     */
    public static final String SQUARE_F_DOT = "square*";

    /**
     * \psdot[dotstyle=diamond*]
     */
    public static final String DIAMOND_F_DOT    = "diamond*";

    /**
     * \psdot[dotstyle=pentagon*]
     */
    public static final String PENTAGON_F_DOT   = "pentagon*";
    
    private String dotStyle;
    

    public DotNode() {
        dotStyle = DEFAULT_DOT;
    }
    

    @Override
    public void draw(Graphics2D g2) {
        Color oldColour = g2.getColor();
        g2.setColor(getNodeColour());
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                            RenderingHints.VALUE_ANTIALIAS_ON);
        
        if(dotStyle.equals(DEFAULT_DOT)){
            g2.setStroke(new BasicStroke(1,
                BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));

            Ellipse2D d = new Ellipse2D.Double(
                    getPositionX(), getPositionY(), getSize(), getSize());
            g2.fill(d);
            g2.draw(d);
        } else if (dotStyle.equals(OPLUS_DOT)){
            // the number 16 is a guse number. no reason
            g2.setStroke(new BasicStroke(getSize()/16,
                BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
            Ellipse2D d = new Ellipse2D.Double(
                    getPositionX(), getPositionY(), getSize(), getSize());
            g2.draw(d);
            // the gap between + symbole to the circle edge
            float gap = (float) (getSize()/6.);
            Line2D hline = new Line2D.Double(getPositionX()+getSize()/2, getPositionY()+gap,
                    getPositionX()+getSize()/2, getPositionY()+getSize() - gap);
            Line2D vline = new Line2D.Double(getPositionX()+gap, getPositionY() + getSize()/2,
                    getPositionX()+getSize()- gap, getPositionY()+getSize()/2);
            g2.draw(hline);
            g2.draw(vline);
        } else if (dotStyle.equals(OTIMES_DOT)){
            // the number 16 is a guse number. no reason
            g2.setStroke(new BasicStroke(getSize()/16,
                BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
            Ellipse2D d = new Ellipse2D.Double(
                    getPositionX(), getPositionY(), getSize(), getSize());
            g2.draw(d);
            // the gap between x symbole to the circle edge
            float gap = (float) (getSize()/6.);
            Line2D line1 = new Line2D.Double(getPositionX() + gap, getPositionY() + gap,
                    getPositionX() + getSize() - gap, getPositionY() + getSize() - gap);
            Line2D line2 = new Line2D.Double(getPositionX() + getSize() - gap,
                    getPositionY() + gap, getPositionX() + gap, getPositionY() + getSize() - gap);
            g2.draw(line1);
            g2.draw(line2);
        } else if (dotStyle.equals(PLUS_DOT)){
            // the number 16 is a guse number. no reason
            g2.setStroke(new BasicStroke(getSize()/16,
                BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
            Line2D vline = new Line2D.Double(getPositionX()+getSize()/2, getPositionY(),
                    getPositionX()+getSize()/2, getPositionY()+getSize());
            Line2D hline = new Line2D.Double(getPositionX(), getPositionY() + getSize()/2,
                    getPositionX()+getSize(), getPositionY()+getSize()/2);
            g2.draw(hline);
            g2.draw(vline);
        } else if (dotStyle.equals(ASTERISK_DOT)){
            // the number 16 is a guse number. no reason
            g2.setStroke(new BasicStroke(getSize()/16,
                BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
            float gap = (float) (getSize()/6.);
            Line2D line1 = new Line2D.Double(getPositionX() + gap, getPositionY() + gap,
                    getPositionX() + getSize() - gap, getPositionY() + getSize() - gap);
            Line2D line2 = new Line2D.Double(getPositionX() + getSize() - gap,
                    getPositionY() + gap, getPositionX() + gap, getPositionY() + getSize() - gap);
            Line2D vline = new Line2D.Double(getPositionX()+getSize()/2, getPositionY(),
                    getPositionX()+getSize()/2, getPositionY()+getSize());
            g2.draw(line1);
            g2.draw(line2);
            g2.draw(vline);
        } else if (dotStyle.equals(TRIANGLE_DOT) || dotStyle.equals(TRIANGLE_F_DOT)){
            // the number 16 is a guse number. no reason
            g2.setStroke(new BasicStroke(getSize()/16,
                BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
            
            int xPoints[] = new int[3];
            int yPoints[] = new int[3];
            xPoints[0] = (int) (getPositionX() + getSize()/2);
            xPoints[1] = (int) (getPositionX());
            xPoints[2] = (int) (getPositionX() + getSize());
            yPoints[0] = (int) (getPositionY());
            yPoints[1] = (int) (getPositionY() + getSize());
            yPoints[2] = (int) (getPositionY() + getSize());
            Polygon d = new Polygon(xPoints,yPoints,3);
            
            if(dotStyle.equals(TRIANGLE_F_DOT)){
                g2.fill(d);
            }
            g2.draw(d);
        } else if (dotStyle.equals(SQUARE_DOT) || dotStyle.equals(SQUARE_F_DOT)){
            g2.setStroke(new BasicStroke(getSize()/16,
                BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
            Rectangle2D d = new Rectangle2D.Double(getPositionX(), getPositionY(), 
                    getSize(), getSize());
            if(dotStyle.equals(SQUARE_F_DOT)){
                g2.fill(d);
            }
            g2.draw(d);
        } else if (dotStyle.equals(DIAMOND_DOT)||dotStyle.equals(DIAMOND_F_DOT)){
            // the number 16 is a guse number. no reason
            g2.setStroke(new BasicStroke(getSize()/16,
                BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
            int xPoints[] = new int[4];
            int yPoints[] = new int[4];
            xPoints[0] = (int) (getPositionX() + getSize()/2);
            xPoints[1] = (int) (getPositionX() + getSize()/4);
            xPoints[2] = (int) (getPositionX() + getSize()/2);
            xPoints[3] = (int) (getPositionX() + getSize()*3/4);
            yPoints[0] = (int) getPositionY();
            yPoints[1] = (int) (getPositionY() + getSize()/2);
            yPoints[2] = (int) (getPositionY() + getSize());
            yPoints[3] = (int) (getPositionY() + getSize()/2);
            Polygon d = new Polygon(xPoints, yPoints, 4);
            if(dotStyle.equals(DIAMOND_F_DOT)){
                g2.fill(d);
            }
            g2.draw(d);
        } else if (dotStyle.equals(PENTAGON_DOT)||dotStyle.equals(PENTAGON_F_DOT)){
            // the number 16 is a guse number. no reason
            g2.setStroke(new BasicStroke(getSize()/16,
                BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
            int xPoints[] = new int[5];
            int yPoints[] = new int[5];
            xPoints[0] = (int) (getPositionX() + getSize()/2);
            xPoints[1] = (int) (getPositionX());
            xPoints[2] = (int) (getPositionX() + getSize()/5);
            xPoints[3] = (int) (getPositionX() + getSize()*4/5);
            xPoints[4] = (int) (getPositionX() + getSize());
            yPoints[0] = (int) (getPositionY());
            yPoints[1] = (int) (getPositionY() + getSize()/3);
            yPoints[2] = (int) (getPositionY() + getSize());
            yPoints[3] = (int) (getPositionY() + getSize());
            yPoints[4] = (int) (getPositionY() + getSize()/3);
            Polygon d = new Polygon(xPoints,yPoints,5);
            if(dotStyle.equals(PENTAGON_F_DOT)){
               g2.fill(d);
            }
            g2.draw(d);
        } else if (dotStyle.equals(O_DOT)){
            // the number 16 is a guse number. no reason
            g2.setStroke(new BasicStroke(getSize()/16,
                BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
            Ellipse2D d = new Ellipse2D.Double(
                    getPositionX(), getPositionY(), getSize(), getSize());
            g2.draw(d);
        } else if (dotStyle.equals(X_DOT)){
            // the number 16 is a guse number. no reason
            g2.setStroke(new BasicStroke(getSize()/16,
                BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
            Line2D line1 = new Line2D.Double(getPositionX(), getPositionY(),
                    getPositionX() + getSize(), getPositionY() + getSize());
            Line2D line2 = new Line2D.Double(getPositionX() + getSize(),
                    getPositionY(), getPositionX(), getPositionY() + getSize());
            g2.draw(line1);
            g2.draw(line2);
        } else if (dotStyle.equals(BAR_DOT)){
            g2.setStroke(new BasicStroke(1,
                BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
            Rectangle2D d = new Rectangle2D.Double(getPositionX()+getSize()*2/5, getPositionY(),
                            getSize()/5, getSize());
            g2.fill(d);
            g2.draw(d);
        }
        
        // draw label
        if(!getLabel().isEmpty()){
            g2.setColor(Color.BLACK);
            FontMetrics fontMetrics = g2.getFontMetrics();
            float labelWidth = fontMetrics.stringWidth(getLabel());
            if (getLabelPosition().equals(LABEL_POSITION_UP)){
                float x = getPositionX() + getSize()/2 - labelWidth/2;
                float y = getPositionY() - 1;
                g2.drawString(getLabel(), x, y);
            } else if (getLabelPosition().equals(LABEL_POSITION_DOWN)){
                float x = getPositionX() + getSize()/2 - labelWidth/2;
                float y = getPositionY() + getSize() + 10;
                g2.drawString(getLabel(), x, y);
            } else if (getLabelPosition().equals(LABEL_POSITION_RIGHT)){
                float x = getPositionX() + getSize() + 1;
                float y = getPositionY() + getSize()/2 + fontMetrics.getHeight()/2;
                g2.drawString(getLabel(), x, y);
            } else if (getLabelPosition().equals(LABEL_POSITION_LEFT)){
                float x = getPositionX() - labelWidth - 1;                
                float y = getPositionY() + getSize()/2 + fontMetrics.getHeight()/2;
                g2.drawString(getLabel(), x, y);
            } else if (getLabelPosition().equals(LABEL_POSITION_UP_RIGHT)){
                float x = getPositionX() + getSize() + 1;
                float y = getPositionY() - 5;
                g2.drawString(getLabel(), x, y);
            } else if (getLabelPosition().equals(LABEL_POSITION_UP_LEFT)){
                float x = getPositionX() - labelWidth - 1;
                float y = getPositionY() - 5;
                g2.drawString(getLabel(), x, y);
            } else if (getLabelPosition().equals(LABEL_POSITION_DOWN_RIGHT)){
                float x = getPositionX() + getSize() + 1;
                float y = getPositionY() + getSize() + 10;
                g2.drawString(getLabel(), x, y);
            } else if (getLabelPosition().equals(LABEL_POSITION_DOWN_LEFT)){
                float x = getPositionX() - labelWidth - 1;
                float y = getPositionY() + getSize() + 10;
                g2.drawString(getLabel(), x, y);
            }
            
        }
        
        g2.setColor(oldColour);
    }

    /**
     * @return the dotStyle
     */
    public String getDotStyle() {
        return dotStyle;
    }

    /**
     * @param dotStyle the dotStyle to set
     */
    public void setDotStyle(String dotStyle) {
        this.dotStyle = dotStyle;
        //System.out.println(getDotStyle());
    }
    
    @Override
    public String getCode() {
        double size = getSize()/50;
        
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
        
        
        String labelStr = "";
        if(!getLabel().isEmpty()){
            labelStr = newLine + "\\uput["+getLabelPosition()+"]("+x+","+y+
        "){"+getLabel()+"}";
        }
        
        if(dotStyle.equals(DEFAULT_DOT)){
            if(!comments.isEmpty())
                return comments + newLine + "\\dotnode[dotsize="+size+", linecolor="+
                    getNodeColourString()+"]("+x+","+y+"){"+getNodeID()+"}" + labelStr;
            return "\\dotnode[dotsize="+size+", linecolor="+
                    getNodeColourString()+"]("+x+","+y+"){"+getNodeID()+"}" + labelStr;
        }else{
            if(!comments.isEmpty())
                return comments + newLine + "\\dotnode[dotsize="+size+", linecolor="+
                    getNodeColourString()+",dotstyle="+dotStyle+"]("+x+","+y+
                    "){"+getNodeID()+"}" + labelStr;
            return "\\dotnode[dotsize="+size+", linecolor="+
                    getNodeColourString()+",dotstyle="+dotStyle+"]("+x+","+y+
                    "){"+getNodeID()+"}" + labelStr;
        }
    }
}
