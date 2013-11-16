package org.pstricks.ui.dialogframes;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.pstricks.core.LaTeXDocument;

/**
 *
 * @author zhouhu
 */
public final class PreviewPDF extends JDialog {
    private JTextArea results;
    private JScrollPane scrollResults;
    private File dir;
    private String usrHomeDirStr;
    private File file;
    private PrintWriter fileWriter;
    private static String OS;
    private static String newLine;
    
    public PreviewPDF(JFrame window) {
        super(window, "Preview PDF", true);
        results = new JTextArea();
        results.setEditable(false);
        scrollResults = new JScrollPane(results);
        this.add(scrollResults);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(400, 300);
        this.setLocationRelativeTo(window);
        //this.setVisible(true);
        OS = System.getProperty("os.name");
        usrHomeDirStr = System.getProperty("user.home");
        newLine = System.getProperty("line.separator");
    }
    
   public void view(){
        writeTeXFile();
        results.append(exe("latex test.tex"));
        results.append(exe("dvips test.dvi"));
        results.append(exe("ps2pdf test.ps"));
        viewPDF();
    }
    void writeTeXFile(){
        dir = new File(usrHomeDirStr+"/PSTricksGraphEditorTest");
        file = new File(usrHomeDirStr+"/PSTricksGraphEditorTest/test.tex");
        try{
            dir.mkdir();
            file.createNewFile();
            fileWriter = new PrintWriter(file);
            fileWriter.print(new LaTeXDocument().getDocumentContents());
            results.append("Test File test.tex created in directory " + 
                    usrHomeDirStr + "/PSTricksGraphEditorTest" + newLine);
        } catch (IOException e){
            results.append("Error when creating test.tex\n" + e.toString());
            return;
        } finally {
            fileWriter.close();
        }
    }
    
    // execute command: cmd
    String exe(String cmd){
        
        Process proc;
        BufferedReader stdInput, stdError;
        // return string: message 
        String msg = "Command: \"" + cmd + "\" excuted results:" + newLine;
        // temp string: str
        String str;
        
        try{
            proc = Runtime.getRuntime().exec(cmd, null, dir);
            
            stdInput = new BufferedReader
                    (new InputStreamReader(proc.getInputStream()));
            stdError = new BufferedReader
                    (new InputStreamReader(proc.getErrorStream()));
            
            while((str = stdInput.readLine()) != null){
                msg += str + newLine;
            }
            
            if ((str = stdError.readLine()) != null){
                msg += str + newLine;
                while((str = stdError.readLine()) != null){
                    msg += str + newLine;
                }
                
                proc.destroy();
                
            }   
        } catch (Exception e){
            msg += e.toString() + newLine;
        }
         
        
        // command executing results
        return msg;
    }
    
    void viewPDF(){
        try{
            // different OS has different methods to open pdf file
            if(OS.startsWith("Windows")){
                Process p = Runtime.getRuntime()
                .exec("rundll32 url.dll,FileProtocolHandler " +usrHomeDirStr+ 
                        "/PSTricksGraphEditorTest/test.pdf");
                p.waitFor();
                results.append(newLine + "Done!");
            } else {
                Process p = Runtime.getRuntime().exec("open "+usrHomeDirStr+
                        "/PSTricksGraphEditorTest/test.pdf");
                results.append(newLine + "Done!");
            }
            
        } catch (Exception e){
            results.append(newLine + e.toString() + newLine);
        }
    }
}
