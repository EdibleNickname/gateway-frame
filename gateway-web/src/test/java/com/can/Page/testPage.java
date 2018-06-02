package com.can.Page;

import com.can.entity.User;
import com.can.service.UserService;
import com.can.util.Page;
import com.can.util.UserRequest;
import com.can.util.json.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @description:
 * @author: LCN
 * @date: 2018-05-27 14:34
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class testPage {

	@Resource
	private UserService userService;

	/**
	 * 测试分页
	 */
	@Test
	public void testPage() {

		UserRequest request = new UserRequest();
		request.setPageNum(2);
		request.setPageSize(2);

		Page<User> page = userService.searchUserPage(request);

		System.out.println(JsonUtil.toJsonString(page));

		System.out.println("当前的页数"+page.getCurrentPage());

		System.out.println("总页数:"+page.getTotalPage());

	}

}
