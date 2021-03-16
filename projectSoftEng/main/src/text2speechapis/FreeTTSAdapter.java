package text2speechapis;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class FreeTTSAdapter implements TextToSpeechAPI {
	
	private VoiceManager vm;
	private Voice voice;
	private float voiceVolume = 1.0f; //default max values of audio
	private float voicePitch = 100.0f;
	private float voiceRate = 150.0f;
	
	public FreeTTSAdapter() {
		
		
	}
	
	public void play(String x) {
		
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		Voice[] voice2;
		vm = VoiceManager.getInstance();
		voice2 = vm.getVoices();
        for (Voice voice1 : voice2) { //to see the available voices
            voice = voice1;
        }
        voice.setVolume(voiceVolume);
        voice.setPitch(voicePitch);
        voice.setRate(voiceRate);
		if(voice!=null) {
			voice.allocate();
		}
		try {
			voice.speak(x);
			voice.deallocate();
		}
		catch(Exception e1){
			e1.printStackTrace(); 
		}
	}
	
	public void setVolume(float volume) {
		
		voiceVolume = volume;
		
	}
	
	public void setPitch(float pitch) {
		
		voicePitch = pitch;
		
	}
	
	public void setRate(float rate) {
		
		voiceRate = rate;
		
	}
	
	public String getplayText() {
		return null;
	}
	
	public float getVolume() {
		return voiceVolume;
		
	}
	
	public float getPitch() {
		return voicePitch;
	}
	
	public float getRate() {
		return voiceRate;
	}

}
