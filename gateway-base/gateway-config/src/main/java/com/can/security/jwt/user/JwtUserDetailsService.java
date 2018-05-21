package com.can.security.jwt.user;

import com.can.dao.UserMapper;
import com.can.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: 通过用户名查询出对应的用户，并将其转为JwtUser
 *
 * @author: LCN
 * @date: 2018-05-21 10:56
 */

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Resource
	private UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		User user = userMapper.selectUserByUserName(userName);
		user.setRoleSet(userMapper.getRolesByUserId(user.getUserId()));

		if (user == null) {
			throw new UsernameNotFoundException(String.format("用户: %s 不存在！", userName));
		}
		return JwtUserFactory.create(user);
	}
}
