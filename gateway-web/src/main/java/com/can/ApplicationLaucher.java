package com.can;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @description: 程序启动类
 *
 * @author: LCN
 * @date: 2018-05-17 21:10
 */

//异步处理
@EnableAsync
@SpringBootApplication
@MapperScan("com.can.dao")
public class ApplicationLaucher {

	public static void main(String[] args) {


		SpringApplication.run(ApplicationLaucher.class, args);
	}

}
