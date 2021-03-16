package commands;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import text2speechapis.TextToSpeechAPI;

public class TuneVoice implements ChangeListener{
	
	private manager manage;

	public TuneVoice(manager man) {
		
		this.manage = man;
	}
	
	public void stateChanged(ChangeEvent event) { //the voice in FREETTS is from range 0-1 and for this reason
												  //we convert the voice choice from range 0-100 to range 0-1
		
        int value = manage.manageVoiceSlider();
        TextToSpeechAPI audioManager = manage.manageTextToSpeechAPI();
        audioManager.setVolume((float)value/(float)100);
   }

}
