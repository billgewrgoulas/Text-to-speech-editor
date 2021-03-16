package commands;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DocumentChange implements DocumentListener{
	
	private manager manage;
	
	public DocumentChange(manager man) {
		
		this.manage = man;
	}
	
	public void changedUpdate(DocumentEvent e) {
	   
	}
	public void removeUpdate(DocumentEvent e) {
	
		manage.setButtons(); //if we remove a letter or a space or a enter(character) we assume that the document have changed 
							//and then we disable the speech voice buttons until the user edit the contents of the document
							//via edit button
		
	}
	public void insertUpdate(DocumentEvent e) {
		
		manage.setButtons();
		
	}

}
