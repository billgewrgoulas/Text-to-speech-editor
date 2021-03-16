package text2speechapis;

public class FakeTextToSpeechAPI implements TextToSpeechAPI{ //for the tests
	
	private String playText;
	private float volume;
	private float pitch;
	private float rate;
	
	public FakeTextToSpeechAPI() { 
		
		
	}
	
	public void play(String x) {
		
		this.playText = x;
		
	}
	
	public void setVolume(float x) {
		
		this.volume = x;
		
	}
	
	public void setPitch(float x) {
		
		this.pitch = x;
		
	}
	
	public void setRate(float x) {
		
		this.rate = x;
		
	}
	
	public String getplayText() {
		return playText;
	}
	
	public float getVolume() {
		return volume;
	}
	
	public float getPitch() {
		return pitch;
	}
	
	public float getRate() {
		return rate;
	}

}
