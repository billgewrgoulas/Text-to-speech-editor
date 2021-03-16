package encodingstrategies;

public class StrategiesFactory {
	
	private EncodingStrategy ret;
	
	public StrategiesFactory() {
		
		
	}
	
	public EncodingStrategy createStrategy(String name) {
		
		if(name.equalsIgnoreCase("atbash")) {
			
			ret = new AtBashEncoding();
			
			return ret;
			
		}else if(name.equalsIgnoreCase("rot13")) {
			
			ret = new Rot13Encoding();
			
			return ret;
		}
		
		return null;
		
	}

}
