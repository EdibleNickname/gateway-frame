package com.can.cahce;

import com.can.redis.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author: LCN
 * @date: 2018-05-22 15:35
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilTest {

	@Autowired
	private RedisUtil redisUtil;

	@Test
	public void testRedisUtil() {
		String testStr = "测试的字符串";

		redisUtil.set("str01", testStr);

		redisUtil.set("str02", testStr+"暂时存在的", 10L);

		System.out.println(redisUtil.get("str01"));

		System.out.println(redisUtil.get("str02"));


	}

}
