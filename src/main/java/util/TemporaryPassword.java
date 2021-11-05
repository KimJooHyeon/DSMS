package util;

import java.util.Random;
import java.util.regex.Pattern;

public class TemporaryPassword {
	private static TemporaryPassword instance = new TemporaryPassword();
	public static TemporaryPassword getInstance() {
		return instance;
	}
	private TemporaryPassword() {}
	
	private final String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,20}";
	
	public String getTemporaryPassword() {
		Random rand = new Random();
		char pwCollection[] = new char[] {
				'1','2','3','4','5','6','7','8','9','0',
				'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
				'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
				'!','@','#','*','%','&','?','$'};
		String ranPw;
		do {
			ranPw = "";
			for(int i=0;i<10;i++) {
				int select =(int)rand.nextInt(pwCollection.length);
				ranPw += pwCollection[select];
			}
		}while(!Pattern.matches(pattern,ranPw));
		return ranPw;
	}
}
