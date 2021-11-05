package util;

import java.util.Random;

public class CreateLicenseNumber {
	private static CreateLicenseNumber instance;
	private int certCharLength = 8;
    private final char[] characterTable = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 
                                            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
                                            'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };

	private CreateLicenseNumber() {}
	public static CreateLicenseNumber getinstance() {
		if(instance == null) {
			instance = new CreateLicenseNumber();
		}
		return instance;
	}
	
	public String licenseNumber() {
		Random random = new Random(System.currentTimeMillis());
        int tablelength = characterTable.length;
        StringBuffer buf = new StringBuffer();
        
        for(int i = 0; i < certCharLength; i++) {
            buf.append(characterTable[random.nextInt(tablelength)]);
        }
        
        return buf.toString();
	}
}
