package com.can.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: LCN
 * @date: 2018-05-22 18:07
 */

@Component
public class ApplicationInitialization implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		System.out.println("资源初始类");
	}

}
