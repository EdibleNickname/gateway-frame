package com.can.service.impl;

import com.can.entity.User;
import com.can.repository.UserRepository;
import com.can.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 
 *
 * @author: LCN
 * @date: 2018-05-20 22:45
 */

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findUserByUserName(String userName) {

		return userRepository.findByUserName(userName);

	}
}
