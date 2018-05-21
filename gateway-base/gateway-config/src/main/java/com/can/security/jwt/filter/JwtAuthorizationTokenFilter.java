package com.can.security.jwt.filter;

import com.can.security.jwt.utils.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 请求过滤器
 * @author: LCN
 * @date: 2018-05-21 11:20
 */

public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier("jwtUserDetailsService")
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Value("${jwt.header}")
	private String header;

	@Value("${jwt.tokenHeader}")
	private String tokenHeader;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

		logger.debug("{}正在认证处理", httpServletRequest.getRequestURL());

		final String requestHeader = httpServletRequest.getHeader(this.header);

		String username = null;
		String authToken = null;

		if (requestHeader != null && requestHeader.startsWith(tokenHeader)) {

			authToken = requestHeader.substring(tokenHeader.length());

			try {
				username = jwtTokenUtil.getUsernameFromToken(authToken);
			} catch (IllegalArgumentException e) {
				logger.error("从token解析用户名是报错--->{}", e);
			} catch (ExpiredJwtException e) {
				logger.warn("token过期或者无效的--->{}", e);
			}
		}else {
			logger.warn("没有从头部获取到需要的tokenHeader,忽略头部继续处理");
		}

		logger.debug("正在验证的用户----->{}", username);

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			logger.debug("SecurityContext为null,开始验证用户");

			// 通过用户名从数据源里面查询这个用户
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

			// 验证token
			if (jwtTokenUtil.validateToken(authToken, userDetails)) {

				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
				logger.info("用户--{}--验证通过,为其设置SecurityContext", username);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

}
