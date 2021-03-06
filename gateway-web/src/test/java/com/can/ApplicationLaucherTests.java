package com.can;


import com.can.entity.User;
import com.can.service.UserService;
import com.can.util.json.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationLaucherTests {

	@Resource
	private UserService userService;

	@Test
	public void contextLoads() {

		User user = userService.findUserByUserName("admin");

		System.out.println(JsonUtil.toJsonString(user));

	}

}
