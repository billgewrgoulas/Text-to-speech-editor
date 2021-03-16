package commands;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import text2speechapis.TextToSpeechAPI;

public class TunePitch implements ChangeListener{
	
	private manager manage;

	public TunePitch(manager man) {
		
		this.manage = man;
		
	}
	
	public void stateChanged(ChangeEvent event) {
		
        int value = manage.managePitchValue();
        TextToSpeechAPI audioManager = manage.manageTextToSpeechAPI();
        System.out.println((float)value);
        audioManager.setPitch((float)value);
        
   }


}
