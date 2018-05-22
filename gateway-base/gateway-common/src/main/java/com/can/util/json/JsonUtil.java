package com.can.util.json;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @description:
 * @author: LCN
 * @date: 2018-05-22 14:38
 */

public class JsonUtil {

	private static JsonUtil mJosnUtil  = null;
	private static ObjectMapper mapper     = null;

	static {
		mJosnUtil = new JsonUtil();
		mapper = new ObjectMapper();
	}

	private JsonUtil(){
	}

/*	public static JsonUtil getInstance() {

		if(mJosnUtil == null){
			synchronized (JsonUtil.class) {
				if(mJosnUtil == null){
					mJosnUtil = new JsonUtil();
					mJosnUtil.mapper = new ObjectMapper();
				}
			}
		}
		return mJosnUtil;
	}*/

	public static String toJsonString(Object entity) {

		String str = "";
		try {
			str = mapper.disableDefaultTyping().writeValueAsString(entity);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	public static <T> T toJsonObject(String jsonStr, Class<T> T) {
		T obj = null;
		try {
			obj = mapper.readValue(jsonStr, T);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj;
	}

}
