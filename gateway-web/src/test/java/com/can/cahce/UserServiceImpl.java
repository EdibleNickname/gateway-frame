package com.can.cahce;

import com.can.entity.User;
import com.can.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @description:
 * @author: LCN
 * @date: 2018-05-22 12:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImpl {

	@Resource
	private UserService userService;

	@Test
	public void testCache() {

		System.out.println("開始");
		User user = userService.findUserByUserName("admin");
		System.out.println(user.toString());

		User user1 = userService.findUserByUserName("admin");
		System.out.println(user1.toString());
	}
}
