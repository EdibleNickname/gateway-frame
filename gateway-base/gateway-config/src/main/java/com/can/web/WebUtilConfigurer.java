package com.can.web;

import com.can.util.email.EmailUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @description: 加载工具类
 * @author: LCN
 * @date: 2018-05-23 16:49
 */

@Configuration
public class WebUtilConfigurer extends WebMvcConfigurationSupport {

	@Bean
	public EmailUtil getEmailUtil() {
		return new EmailUtil();
	}
}

