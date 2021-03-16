package commands;

import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;

public class TextListener implements CaretListener{
	
	private manager manage;
	
	public TextListener(manager man) {
		this.manage = man;
	}
	
	public void caretUpdate(CaretEvent ce)
	{									  
	    
	    JTextArea editArea = (JTextArea)ce.getSource();

        //start with some default value for the line
        int linenum = 1;
        int start = 0;
        int end = 0;

        try {
            // First we find the position of the caret. This is the number of where the caret is in relation to the start of the JTextArea
            // in the upper left corner. We use this position to find offset values (eg what line we are on for the given position as well as
            // what position that line starts on.
            int caretpos = editArea.getCaretPosition();
            linenum = editArea.getLineOfOffset(caretpos);
            start = editArea.getLineStartOffset(linenum);
			end = editArea.getLineEndOffset(linenum);

        }
        catch(Exception ex) { }
        
        boolean tempBool = true;
        try {
        	
			if(editArea.getText(start,end-start).isBlank())
				tempBool = false;
			
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        manage.manageLineButtons(tempBool, linenum);
        
	}

}
