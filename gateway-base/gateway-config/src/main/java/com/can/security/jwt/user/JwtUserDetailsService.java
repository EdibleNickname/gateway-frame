package com.can.security.jwt.user;

import com.can.entity.User;
import com.can.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @description: 通过用户名查询出对应的用户，并将其转为JwtUser
 *
 * @author: LCN
 * @date: 2018-05-21 10:56
 */

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		User user = userRepository.findByUserName(userName);

		if (user == null) {
			throw new UsernameNotFoundException(String.format("用户: %s 不存在！", userName));
		}
		return JwtUserFactory.create(user);
	}
}
