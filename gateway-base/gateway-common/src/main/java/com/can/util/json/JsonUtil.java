package com.can.util.json;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @description: Json工具
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

	/**
	 * bean转字符串
	 * @param entity
	 * @return
	 */
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

	/**
	 * 字符串转bean
	 * @param jsonStr
	 * @param T
	 * @param <T>
	 * @return
	 */
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
