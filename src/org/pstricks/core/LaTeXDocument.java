package org.pstricks.core;

import org.pstricks.ui.PSTricksGraphEditor;

/**
 * Generates the LaTeX document content
 * @author zhouhu
 */
public final class LaTeXDocument {

    public LaTeXDocument() {
    }
    
    public final String getDocumentContents(){
        String newLine = System.getProperty("line.separator");
        String content = "\\documentclass[10pt]{article}" + newLine;
               content += "\\usepackage{pstricks-add}" + newLine;
               content += "\\usepackage{graphicx}" + newLine;
               content += "\\pagestyle{empty}" + newLine;
               content += "\\begin{document}" + newLine;
               content += PSTricksGraphEditor.graph.getCode() + newLine;
               content += "\\end{document}" + newLine;
               
        return content;
    }
}
