package com.can.web;

import com.can.util.email.EmailUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 加载工具类
 * @author: LCN
 * @date: 2018-05-23 16:49
 */

// extends WebMvcConfigurationSupport

@Configuration
public class WebUtilConfigurer {

	@Bean
	public EmailUtil getEmailUtil() {
		return new EmailUtil();
	}
}

