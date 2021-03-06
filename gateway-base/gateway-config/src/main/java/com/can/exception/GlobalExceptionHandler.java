package com.can.exception;

import com.can.exception.type.AuthenticationException;
import com.can.response.Response;
import com.can.response.enums.ResponseEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: SpringBoot的全局异常处理
 * @author: LCN
 * @date: 2018-05-18 10:09
 */

@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	private final static String MSG_KEY = "errorMsg";

	public GlobalExceptionHandler() {
	}

	@ExceptionHandler({Exception.class})
	public Response<Map<String, String>> exceptionHandler (Exception exception) {

		log.error(exception.getMessage(), exception);

		Response<Map<String, String>> response = new Response<>();
		Map<String, String> map = new HashMap<>(16);

		//认证失败异常
		if (exception instanceof AuthenticationException) {
			AuthenticationException ex = (AuthenticationException)exception;
			response.setCode(ResponseEnum.UNAUTHORIZED.getCode());
			response.setMessage(ResponseEnum.UNAUTHORIZED.getMessage());
			map.put(MSG_KEY, ex.getMessage());
			response.setResult(map);
			return response;
		}
		// 权限不够，直接返回url
		if (exception instanceof AccessDeniedException) {
			AccessDeniedException ex = (AccessDeniedException)exception;
			response.setCode(ResponseEnum.UNAUTHORIZED.getCode());
			response.setMessage(ResponseEnum.UNAUTHORIZED.getMessage());
			map.put(MSG_KEY, ex.getMessage());
			response.setResult(map);
			return response;
		}

		// 未知异常
		response.setCode(ResponseEnum.FAILURE.getCode());
		response.setMessage(ResponseEnum.FAILURE.getMessage());
		return response;
	}

}
