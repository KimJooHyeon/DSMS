package util;

import java.util.HashMap;
import java.util.Map;

public class LicenseNumberPool {
	private Map<String, String> license;
	
	private static LicenseNumberPool instance;
	private LicenseNumberPool() {
		license = new HashMap<>();
	}
	public static LicenseNumberPool getInstance() {
		if(instance == null) {
			instance = new LicenseNumberPool();
		}
		return instance;
	}
	
	public String getLicense(String key) {
		return license.get(key);
	}
	
	public void setLicense(String key, String value) {
		this.license.put(key, value);
	}
	
	
}
