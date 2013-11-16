package org.pstricks.ui.dialogframes;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.pstricks.ui.PSTricksGraphEditor;

/**
 *
 * @author zhouhu
 */
public final class PSTricksCodeViewer extends JDialog implements ClipboardOwner{
    private JTextArea code;
    private JScrollPane codeScroll;
    private JButton copyBtn;

    public PSTricksCodeViewer(Frame window) {
        super(window);
        code = new JTextArea();
        code.setEditable(false);
        codeScroll = new JScrollPane(code);
        code.setText(PSTricksGraphEditor.graph.getCode());
        code.selectAll();
        copyBtn = new JButton("Copy all code to clipboard");
        copyBtn.setIcon(new ImageIcon(
                            this.getClass().getResource("/icons/copy24.png")));
        copyBtn.addActionListener(new CopyBtnListener());
        this.setLayout(new BorderLayout());
        this.add(copyBtn, BorderLayout.NORTH);
        this.add(codeScroll, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(400, 300);
        this.setLocationRelativeTo(window);
    }

    void copy(){
        StringSelection str = new StringSelection(code.getText());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(str, this);
    }
    
    @Override
    public void lostOwnership(Clipboard clpbrd, Transferable t) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    
    class CopyBtnListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
            code.selectAll();
            copy();
        }
    }
}

