package com.can.response;

import com.can.response.enums.ResponseEnum;

import java.io.Serializable;

/**
 * @description:
 * @author: LCN
 * @date: 2018-05-18 10:30
 */
public class Response<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/** http响应码 */
	private Integer code;

	/** 给予前端的提示信息 */
	private String message;

	/** 返回结果 */
	private T result;

	public Response() {
		this.code = ResponseEnum.SUCCESS.getCode();
		this.message = ResponseEnum.SUCCESS.getMessage();
	}

	public Response(Integer code) {
		this.code = code;
		this.message = ResponseEnum.getMessageByCode(code);
	}

	public Response(Integer code, String message) {
		this.code = code;
		this.message = ResponseEnum.getMessageByCode(code) + ":" + message;
	}

	public Response(Integer code, String message, T result) {
		this.code = code;
		this.message = ResponseEnum.getMessageByCode(code) + ":" + message;
		this.result = result;
	}


	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
}
