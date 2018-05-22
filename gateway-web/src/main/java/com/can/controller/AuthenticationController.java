package com.can.controller;

import com.can.exception.type.AuthenticationException;
import com.can.request.JwtAuthenticationRequest;
import com.can.response.JwtAuthenticationResponse;
import com.can.security.jwt.user.JwtUser;
import com.can.security.jwt.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @description:
 * @author: LCN
 * @date: 2018-05-17 14:39
 */

@RestController
public class AuthenticationController {

	@Value("${jwt.header}")
	private String header;

	@Value("${jwt.expiration}")
	private Long expiration;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	/**
	 * 生成token
	 * @param authenticationRequest
	 * @return
	 * @throws AuthenticationException
	 */
	@PostMapping("${jwt.route.authentication.path}")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails, expiration);
		return ResponseEntity.ok(new JwtAuthenticationResponse(token));
	}

	/**
	 * 刷新token
	 * @param request
	 * @return
	 */
	@GetMapping("${jwt.route.authentication.refresh}")
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {

		String authToken = request.getHeader(header);
		final String token = authToken.substring(header.length());
		String username = jwtTokenUtil.getUsernameFromToken(token);
		JwtUser user = (JwtUser) jwtUserDetailsService.loadUserByUsername(username);

		if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
			String refreshedToken = jwtTokenUtil.refreshToken(token, expiration );
			return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	private void authenticate(String username, String password) {

		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new AuthenticationException("用户不可以用---->{}", e);
		} catch (BadCredentialsException e) {
			throw new AuthenticationException("创建Token失败", e);
		}

	}

}
