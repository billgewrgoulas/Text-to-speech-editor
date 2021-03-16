package encodingstrategies;

public class AtBashEncoding extends TemplateEncoding{
	
	private String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private String alphabet2 = "zyxwvutsrqponmlkjihgfedcba";
    private String alphabet_upper = alphabet.toUpperCase();
    private String alphabet2_upper = alphabet2.toUpperCase();
	
	public AtBashEncoding() {
		
		
	}
	
	public String encode(String txt) {
		
		char[] val = txt.toCharArray();
		for(int i=0;i<val.length;i++) {
			char letter = val[i];
			char encodeLetter = mapcharacter(letter);
			val[i] = encodeLetter;
		}
		return new String(val);	
		
	}
	
	public char mapcharacter(char character) {
		
		if(character>='a' && character <='z') {
			
			int pos = alphabet.indexOf(character);
            character = alphabet2.charAt(pos);
			
		}else if(character>='A' && character <='Z') {
			
			int pos = alphabet_upper.indexOf(character);
            character = alphabet2_upper.charAt(pos);
			
		}
		
		return character;
		
	}

}
