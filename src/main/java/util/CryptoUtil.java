package util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CryptoUtil {
	
	/**
	 * 매개변수의 문자열을 SHA-512방식으로 해시(암호화)한다. => 단방향 암호화
	 * (암호화 알고리즘 종류 : MD5, SHA-256, SHA3, SHA-512, ...)
	 * 
	 * @param str 암호화할 문자열
	 * @return 암호화된 문자열
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 */
	public static String sha512(String str) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		// length 88
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(str.getBytes("UTF-8"));
		
		return Base64.getEncoder().encodeToString(md.digest());
	};
	
	/**
	 * AES-256 암호화 방식으로 암호화 한다.
	 * 
	 * @param str 암호화할 문자열
	 * @param key 암호화에 사용할 암호키 문자열(16글자 이상)
	 * @return 암호화된 문자열
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */
	public static String encryptAES256(String str, String key) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		// length 64
		// 초기화 벡터(Initial Vector; IV) => 암호문이 패턴화 되지 않도록 하는데 사용하는 데이터를 말한다
		// CBC모드에서 사용하는데 암호화 때마다 다른 랜덤 비트열을 이용하는것이 보통이다.
		// CBC모드의 첫 블록을 암호화할 때 사용되는 값이다.
		String iv = key.substring(0, 16);
		byte[] keyBytes = new byte[16];
		System.arraycopy(key.getBytes("UTF-8"), 0, keyBytes, 0, keyBytes.length);		
		
		// 비밀키 생성
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
		
		// Cipher 생성 및 초기화 ==> 알고리즘/암호화모드/패딩
		// CBC : Cipher Block Chaining Mode ==> 이전 단계의 암호화한 결과가 현 단계의 암호화에 사용되는 방식을 말한다.
		//		 블록 암호화 운영 모드 중 보안성이 가장 높은 암호화 방법으로 가장 많이 사용하는 방식이다.
		//		 이것은 암호화가 병렬적으로 처리되는것이 아니라, 순차적으로 수행되어야한다는 단점이 있다.
		
		// 패딩 : Padding ==> 마지막 블록이 블록의 길이와 항상 딱 맞아 떨어지지 않기 때문에 부족한 길이만큼 '0'으로 채우거나 임의의
		//				     비트값들로 채워넣는 방식을 말한다.
		
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		byte[] ivByte = new byte[16];
		System.arraycopy(iv.getBytes("UTF-8"), 0, ivByte, 0, ivByte.length);
		
		// 암호를 옵션종류에 맞게 초기화한다.
		// 옵션 종류 : ENCRYPT_MODE(암호화 모드), DECRYPT_MODE(복호화 모드)
		c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(ivByte));
		
		// 암호문 생성
		byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
		
		String enStr = Base64.getEncoder().encodeToString(encrypted);
		return enStr;
	}
	
	/**
	 * 암호화된 문자열을 원래의 데이터로 복호화 하는 메서드
	 * 
	 * @param str 암호화된 문자열
	 * @param key 암호키 문자열
	 * @return 복호화된 문자열
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */
	public static String decryptAES256(String str, String key) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		String iv = key.substring(0, 16);
		
		byte[] keyBytes = new byte[16];
		System.arraycopy(key.getBytes("UTF-8"), 0, keyBytes, 0, keyBytes.length);		
		
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
		
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		byte[] ivByte = new byte[16];
		System.arraycopy(iv.getBytes("UTF-8"), 0, ivByte, 0, ivByte.length);
		
		c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(ivByte));
		
		byte[] byteStr = Base64.getDecoder().decode(str);
		
		String decrypStr = new String(c.doFinal(byteStr), "UTF-8");
		
		return decrypStr;
	}
}