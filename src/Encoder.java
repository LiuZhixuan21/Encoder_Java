
public class Encoder {
	private final String referenceTable = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";
	private char encoderOffset;	

	// can choose any offset for the encoder
	public Encoder(char encoderOffset) {
		this.encoderOffset = encoderOffset;
	}
	
	public char getEncoderOffset() {
		return encoderOffset;
	}
	public void setEncoderOffset(char offset) {
		this.encoderOffset = offset;
	}
	
	public String encode(String plainText) {
		int shift = referenceTable.indexOf(encoderOffset);		
		StringBuilder encodedText = new StringBuilder();
		encodedText.append(encoderOffset);
		
		for (char c: plainText.toCharArray()) {			
			int index = referenceTable.indexOf(c);
			//not in referenceTable
			if (index == -1) {
				encodedText.append(c);
			}
			else {
				//in referenceTable: shit
				int newIndex = (index - shift + referenceTable.length()) % referenceTable.length();
				char newChar = referenceTable.charAt(newIndex);
				encodedText.append(newChar);
			}
		}
		return encodedText.toString();
	}
	public String decode(String encodedText) {
		char offset = encodedText.charAt(0);
		int shift = referenceTable.indexOf(offset);
		
		StringBuilder decodedText = new StringBuilder();
		
		//skip first char
		for (int i = 1; i < encodedText.length(); i++ ) {
			char c = encodedText.charAt(i);
			int index = referenceTable.indexOf(c);
			
			if (index == -1) {
				decodedText.append(c);
			}
			else {
				int decodedIndex = (index + shift) % referenceTable.length();
				decodedText.append(referenceTable.charAt(decodedIndex));
			}
		}
		return decodedText.toString();		
	}
	
	public static void main(String[] args) {
		Encoder encoder = new Encoder('B');
		String plainText = "HELLO WORLD";
		String encodedText = encoder.encode(plainText);
		
		System.out.println("Encoder Offest: " + encoder.encoderOffset);
		System.out.println("Plaintext: " + plainText);
        System.out.println("Encoded text: " + encodedText);
        System.out.println();
        		
        encoder.setEncoderOffset('F');
        plainText = "HELLO WORLD";
		encodedText = encoder.encode(plainText);
		
		System.out.println("Encoder Offest: " + encoder.encoderOffset);
		System.out.println("Plaintext: " + plainText);
        System.out.println("Encoded text: " + encodedText);
        System.out.println();
		
        
		encodedText = "BGDKKN VNQKC";
		String decodedText = encoder.decode(encodedText);		
        System.out.println("Encoded text: " + encodedText);
        System.out.println("Decoded text: " + decodedText);
        System.out.println();
        
        encodedText = "FC/GGJ RJMG.";
		decodedText = encoder.decode(encodedText);		
        System.out.println("Encoded text: " + encodedText);
        System.out.println("Decoded text: " + decodedText);
        System.out.println();
	}
}
