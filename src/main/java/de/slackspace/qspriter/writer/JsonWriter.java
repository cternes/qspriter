package de.slackspace.qspriter.writer;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;

public class JsonWriter implements Writer<JSONObject> {
	
	private Map<String, JSONObject> jsonMap = new HashMap<String, JSONObject>();
	
	@SuppressWarnings("unchecked")
	public void appendJsonObjectForRow(String key, int rowNum, int cols, int imageWidth, int rowHeight, int rowLen) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sx", 0);
		jsonObject.put("sy", rowNum);
		jsonObject.put("cols", cols);
		jsonObject.put("tilew", imageWidth);
		jsonObject.put("tileh", rowHeight);
		jsonObject.put("frames", rowLen);

		jsonMap.put(key, jsonObject);
	}
	
	public JSONObject getCombinedJsonObject() {
		JSONObject json = new JSONObject();
		
		Set<String> keys = jsonMap.keySet();
		for (String key : keys) {
			JSONObject rowJson = jsonMap.get(key);
			json.put(key, rowJson);
		}
		
		return json;
	}

	@Override
	public void write(OutputStream stream, JSONObject data) throws IOException {
		if(stream == null) {
			throw new IllegalArgumentException("Parameter stream must not be null");
		}
		
		stream.write(data.toJSONString().getBytes());
	}
}
