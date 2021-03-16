package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;

import model.Document;

public class OpenDocument implements ActionListener {
	
	private manager manage;
	private ReplayManager replay;
	private String title;
	private String author;
    private String dateCreated;
    private String dateLastSaved;
    private String contents;
    private String titleAuthor;
    private Document document;

	public OpenDocument(manager man,ReplayManager rep) {
		this.manage= man;
		this.replay = rep;
	}
	
	public OpenDocument(manager man,Document doc,String t,String a,String lastSaved,String creation,String text,String ta) {
		this.manage = man;
		this.document = doc;
		this.title = t;
		this.author = a;
		this.dateLastSaved = lastSaved;
		this.dateCreated = creation;
		this.contents = text;
		this.titleAuthor = ta;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e==null) {
			replayAction();
		}else {

			JFileChooser j = new JFileChooser("C:"); 
			  
	        // Invoke the showsOpenDialog function to show the save dialog 
	        int r = j.showOpenDialog(null); 

	        // If the user selects a file 
	        if (r == JFileChooser.APPROVE_OPTION) {
	        	
	            // Set the label to the path of the selected directory 
	            File fi = new File(j.getSelectedFile().getAbsolutePath());
	            
	            String s1 = "", s2 = "", s3 = "", s4 = ""; 
	            contents = "";
	            titleAuthor = "";
	            dateCreated = "";
	            dateLastSaved = "";
	            try { 
	                // File reader 
	                FileReader fr = new FileReader(fi); 

	                // Buffered reader 
	                BufferedReader br = new BufferedReader(fr);
	                
	                s1 = br.readLine();
	                titleAuthor = s1;
	                
	                s2 = br.readLine();
	                dateLastSaved = s2;
	                
	                s3 = br.readLine();
	                dateCreated = s3;
	                
	                // Take the input from the file 
	                while ((s4 = br.readLine()) != null) { 
	                    contents += s4 + "\n"; 
	                }
	                
	                fr.close();
	                
	            } 
	            catch (Exception evt) { 
	            	manage.cancelMenu(evt.getMessage());
	            }
	            
	          
	            document = new Document();
	            manage.setDocument(document);
	            manage.manageOpenDocument(titleAuthor, dateCreated, dateLastSaved,contents);
	            String[] parts = titleAuthor.split("-");
	            title = "";
	            author = "";
	            for(int i=0;i<parts.length;i++) {
	            	String[] value = parts[i].split(":");
	            	if(i==0) {
	            		title = value[1];
	            	}else {
	            		author = value[1];
	            	}
	            }
	            document.setTitleAuthorDate(title, author, dateCreated);
	            document.setLastDateSaved(dateLastSaved);
	            document.setLines(contents);
	            if(!(e==null)) {
	            	OpenDocument copy = new OpenDocument(manage,document,title,author,dateLastSaved,dateCreated,contents,titleAuthor);
	            	replay.action(copy);
	            }
	            
	        } 
	        // If the user cancelled the operation 
	        else
	        	manage.cancelMenu("the user cancelled the operation");
		}
	}
	
	public void replayAction() {
		manage.setDocument(document);
		manage.manageOpenDocument(titleAuthor, dateCreated, dateLastSaved,contents);
		document.setTitleAuthorDate(title, author, dateCreated);
        document.setLastDateSaved(dateLastSaved);
        document.setLines(contents);
	}

}
