package com.can;


import com.can.entity.Role;
import com.can.entity.User;
import com.can.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Iterator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationLaucherTests {

	@Resource
	private UserService userService;

	@Test
	public void contextLoads() {

		User user = userService.findUserByUserName("admin");

		System.out.println(user.getUserName());

		Iterator<Role> iterator = user.getRoleSet().iterator();

		while (iterator.hasNext()){
			Role role = iterator.next();
			System.out.println(role.getRoleName());
		}

	}

}
