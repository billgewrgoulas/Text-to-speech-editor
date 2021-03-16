package text2speechapis;

public interface TextToSpeechAPI {
	
	public void play(String x);
	public void setVolume(float x);
	public void setPitch(float x);
	public void setRate(float x);
	public String getplayText(); //for the tests
	public float getVolume(); //for the tests
	public float getPitch(); //for the tests
	public float getRate(); //for the tests
	
}
