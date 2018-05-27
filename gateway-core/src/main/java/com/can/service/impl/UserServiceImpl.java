package com.can.service.impl;

import com.can.dao.UserMapper;
import com.can.entity.User;
import com.can.service.UserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	@Cacheable(value = "role")
	@Override
	public User findUserByUserName(String userName) {
		System.out.println("執行方法");
		User user = userMapper.selectUserByUserName(userName);
		user.setRoleSet(userMapper.getRolesByUserId(user.getUserId()));
		return user;
	}

/*
	@Override
	public Page<User> searchPage(PageRequset requset) {

		// TODO: 2018/5/25 查询数据数据
		return null;
	}*/
	
}
