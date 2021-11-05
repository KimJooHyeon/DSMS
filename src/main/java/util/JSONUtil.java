package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONUtil {
	
	public static List<JSONObject> getJsonObjectList(String json) {
		JSONParser parser = new JSONParser();
		List<JSONObject> obj = new ArrayList<>();
		try {
			JSONArray array = (JSONArray)parser.parse(json);
			for(int i = 0; i < array.size(); i++) {
				obj.add((JSONObject) array.get(i));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	public static List<Map<String, String>> getJsonMap(String json) {
		System.out.println(json);
		List<JSONObject> list = getJsonObjectList(json);
		List<Map<String, String>> result = new ArrayList<>();
		
		for(JSONObject item : list) {
			Set keySet = item.keySet();
			Map<String, String> map = new HashMap<>();
			for(Object key : keySet) {
				map.put(key + "", (String) item.get(key));
			}
			result.add(map);
		}
		
		return result;
	}
}
