package com.can.controller;

import com.can.entity.User;
import com.can.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author: LCN
 * @date: 2018-05-20 22:47
 */

@RestController
public class UserController {

	@Resource
	private UserService userService;

	@GetMapping("/getUser/{userName}")
	public User getUserByUserName(@PathVariable String userName) {
		return userService.findUserByUserName(userName);
	}

}
