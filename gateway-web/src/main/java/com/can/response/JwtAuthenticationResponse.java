package com.can.response;

import java.io.Serializable;

/**
 * @description:
 * @author: LCN
 * @date: 2018-05-17 14:21
 */
public class JwtAuthenticationResponse implements Serializable {

	private static final long serialVersionUID = 1250166508152483573L;

	private final String token;

	public JwtAuthenticationResponse(String token) {
		this.token = token;
	}

	public String getToken() {
		return this.token;
	}
}
