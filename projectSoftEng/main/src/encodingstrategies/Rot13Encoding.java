package encodingstrategies;

public class Rot13Encoding extends TemplateEncoding{
	
	public Rot13Encoding() {
		
		
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
	
	public char mapcharacter(char letter) {
		
		if(letter>='a' && letter<='z') {
			if(letter>'m') {
				letter -= 13;
			}
			else {
				letter += 13;
			}
		}else if(letter>='A' && letter<='Z') {
			if(letter>'M') {
				letter -= 13;
			}else {
				letter += 13;
			}
		}
		return letter;		
	}

}
