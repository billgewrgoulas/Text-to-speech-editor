package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JFileChooser;

import model.Document;

public class SaveDocument implements ActionListener {
	
	private manager manage;
	private ReplayManager replay;
	
	private Document document;
	private String newJlabelText;
	private String SaveDate;
	private String DateCreated;
	private String text;
	private String titleAuthor;
	private BufferedWriter w;

	public SaveDocument(manager man,ReplayManager rep) {
		this.manage = man;
		this.replay = rep;
	}
	
	public SaveDocument(BufferedWriter write,manager man,Document doc,String ta, String jText, String save,String creation,String txt) {
		this.manage = man;
		this.document = doc;
		this.newJlabelText = jText;
		this.SaveDate = save;
		this.DateCreated = creation;
		this.text = txt;
		this.titleAuthor = ta;
		this.w = write;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e==null) {
			replayAction();
		}else {
			JFileChooser j = new JFileChooser("C:");
			
			int r = j.showSaveDialog(null);
			if (r == JFileChooser.APPROVE_OPTION) { 
				  
				File fi = new File(j.getSelectedFile().getAbsolutePath()); 

				try { 
			    	
					// Create a file writer 
					FileWriter wr = new FileWriter(fi, false); 

					// Create buffered writer to write 
					w = new BufferedWriter(wr);
					
					titleAuthor = manage.manageFrameText() + "\n";
					// Write 
					w.write(titleAuthor);
					
					String previousJlabelText = manage.manageLabelText();
					String[] parts = previousJlabelText.split("-");
					LocalDate localDate = LocalDate.now();
		            SaveDate = DateTimeFormatter.ofPattern("dd/MM/yyy").format(localDate);
		            newJlabelText = parts[0] + " - " + "Last Day Saved: " + SaveDate;
		            manage.manageJLabel().setText(newJlabelText);
		            document = manage.getDocument();
		            document.setLastDateSaved(SaveDate);
		            SaveDate += "\n";
		            w.write(SaveDate);
		            
		            DateCreated = "";
		            DateCreated = document.getdateCreated() + "\n";
		            w.write(DateCreated);
		            
		            text = manage.manageTextArea().getText();
		            document.setLines(text);
					w.write(text); 

					w.flush(); 
					w.close();
					manage.manageButtonSave();
					if(!(e==null)) {
						SaveDocument copy = new SaveDocument(w,manage,document,titleAuthor,newJlabelText,SaveDate,DateCreated,text);
						replay.action(copy);
					}
				} 
				catch (Exception evt) {
					manage.cancelMenu(evt.getMessage());
					//JOptionPane.showMessageDialog(manage.getEditor().getFrame(), evt.getMessage()); 
				}
			
			} 
				// If the user cancelled the operation 
			else {
				manage.cancelMenu("the user cancelled the operation");
			}	
				//JOptionPane.showMessageDialog(manage.getEditor().getFrame(), "the user cancelled the operation");
		}
		
		
	}
	
	public void replayAction() {
		try { 
			w.write(titleAuthor);
			manage.manageJLabel().setText(newJlabelText);
			document.setLastDateSaved(SaveDate);
			w.write(SaveDate);
			w.write(DateCreated);
			w.write(text); 
			w.flush(); 
			w.close();
			manage.manageButtonSave();
			
		}catch(Exception evt) {
			
		}
		
	}

}
