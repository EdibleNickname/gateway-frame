package com.can.email;

import com.can.util.email.EmailUtil;
import com.can.util.email.model.Email;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author: LCN
 * @date: 2018-05-23 11:03
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmailTest {

	@Autowired
	private EmailUtil emailUtil;

	@Test
	public void test() {

		Email<String> email = new Email<String>();
		email.setReceiverEmail("1455476411@qq.com");
		email.setSubject("工具类整合");
		email.setContent("1234");
		email.setTempleName("test.ftl");
		email.setKey("testString");

		emailUtil.sendHtmlEmail(email);

		System.out.println("主线程");

		//测试时，需要暂停一会，否则程序会执行完，就结束了
		try {
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
