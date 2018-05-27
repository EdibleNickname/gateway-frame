package com.can.Page;

import com.can.dao.UserMapper;
import com.can.entity.User;
import com.can.util.UserPage;
import com.can.util.json.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: LCN
 * @date: 2018-05-27 14:34
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class testPage {

	@Resource
	private UserMapper userMapper;

	/**
	 * 测试分页
	 */
	@Test
	public void testPage() {
		UserPage page = new UserPage();
		page.setPage(1);
		page.setPageSize(2);
		List<User> list = userMapper.selectAllRole(page);

		System.out.println(JsonUtil.toJsonString(list));

		// 总条数
		System.out.println(page.getTotal());

		// 总页数
		System.out.println(page.getTotalPage());
	}

}
