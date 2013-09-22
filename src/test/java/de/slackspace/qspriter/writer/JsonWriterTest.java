package de.slackspace.qspriter.writer;

import org.json.simple.JSONObject;
import org.junit.Test;

public class JsonWriterTest {

	@Test
	public void testFu() {
		JSONObject json = new JSONObject();
		json.put("a", "x");

		JSONObject obj = new JSONObject();
		obj.put("name","foo");
		obj.put("num",new Integer(100));
		obj.put("balance",new Double(1000.21));
		obj.put("is_vip",new Boolean(true));
		obj.put("nickname",null);
		
		JSONObject mario = new JSONObject();
		mario.put("mario", obj);
		mario.put("luigi", obj);

		System.out.println(mario);
	}
}
