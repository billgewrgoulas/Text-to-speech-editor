package text2speechapis;

public class TextToSpeechAPIFactory {
	
	private TextToSpeechAPI ttsAPI;
	
	public TextToSpeechAPIFactory() {
		
		
	}
	
	public TextToSpeechAPI createTTSAPI(String x) {
		
		if(x.equalsIgnoreCase("normal")) {
			
			ttsAPI = new FreeTTSAdapter();
			return ttsAPI;
			
		}else if(x.equalsIgnoreCase("fake")) {
			
			ttsAPI = new FakeTextToSpeechAPI();
			return ttsAPI;
			
		}
		return null;
		
	}

}
