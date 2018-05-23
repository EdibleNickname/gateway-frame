package com.can.response.enums;

/**
 * @description: 请求响应信息枚举
 * @author: LCN
 * @date: 2018-05-18 10:37
 */
public enum ResponseEnum {

	/** 请求成功响应 */
	SUCCESS(200, "请求成功"),

	/** 请求参数错误 */
	PARAMETER_ERROR(400, "参数错误"),

	/** 权限相关的响应 */
	UNAUTHORIZED(401, "权限不够"),

	/** 未知的错误响应 */
	FAILURE(-999, "未知错误");

	private Integer code;
	private String message;

	private ResponseEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * 通过枚举的code获取对应的msg
	 *
	 * @param code 枚举的code
	 *
	 * @return
	 */
	public static String getMessageByCode(int code) {

		String noCodeMsg = "该code没有对应的消息提示";

		for ( ResponseEnum item : ResponseEnum.values() ) {
			if (item.getCode() == code) {
				return item.getMessage();
			}
		}

		return noCodeMsg;
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
}
