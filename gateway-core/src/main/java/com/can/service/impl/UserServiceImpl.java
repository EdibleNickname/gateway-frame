package com.can.service.impl;

import com.can.dao.UserMapper;
import com.can.entity.User;
import com.can.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: 
 *
 * @author: LCN
 * @date: 2018-05-20 22:45
 */

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;

	@Override
	public User findUserByUserName(String userName) {
		User user = userMapper.selectUserByUserName(userName);
		user.setRoleSet(userMapper.getRolesByUserId(user.getUserId()));
		return user;
	}

}
