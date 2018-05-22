package com.can.security.jwt.util;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:  认证不成功处理
 * @author: LCN
 * @date: 2018-05-21 11:41
 */

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	/**
	 * 当用户尝试去访问没有权限的url,非法的请求
	 * 返回401(一般来说该错误消息表明您首先需要登录)回去
	 *
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @param e
	 * @throws IOException
	 */
	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
		AuthenticationException e) throws IOException, ServletException {

		System.out.println("666");
		// 此处返回了401回去了
		httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "权限不够");
	}


}
