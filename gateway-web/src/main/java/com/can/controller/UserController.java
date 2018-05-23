package com.can.controller;

import com.can.entity.User;
import com.can.model.UserDto;
import com.can.security.jwt.user.JwtUser;
import com.can.security.jwt.util.JwtTokenUtil;
import com.can.service.UserService;
import com.can.util.json.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: LCN
 * @date: 2018-05-20 22:47
 */

@RestController
public class UserController {

	@Resource
	private UserService userService;

	@Value("${jwt.header}")
	private String header;

	@Value("${jwt.tokenHeader}")
	private String tokenHeader;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@GetMapping("/getUser/{userName}")
	public User getUserByUserName(@PathVariable String userName) {
		return userService.findUserByUserName(userName);
	}

	@GetMapping("/user")
	public JwtUser getAuthenticatedUser(HttpServletRequest request) {
		String token = request.getHeader(header).substring(tokenHeader.length());

		System.out.println(token);

		String username = jwtTokenUtil.getUsernameFromToken(token);

		JwtUser user = (JwtUser) jwtUserDetailsService.loadUserByUsername(username);
		return user;
	}

	@PostMapping("/userdto")
	public UserDto getUserDto(@Validated @RequestBody UserDto userDto) {
		System.out.println(JsonUtil.toJsonString(userDto));
		userDto.setName("新的名字");
		return userDto;
	}

}
