package com.can.security.config;

import com.can.security.jwt.filter.JwtAuthorizationTokenFilter;
import com.can.security.jwt.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @description:
 * @author: LCN
 * @date: 2018-05-21 11:31
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	@Qualifier("jwtUserDetailsService")
	private UserDetailsService userDetailsService;

	@Value("${jwt.header}")
	private String header;

	/**
	 * 验证的路径
	 */
	@Value("${jwt.route.authentication.path}")
	private String authenticationPath;


	/**
	 * 密码加密器
	 *
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoderBean() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * 验证管理器
	 *
	 * @return
	 * @throws Exception
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * url过滤器
	 *
	 * @return
	 * @throws Exception
	 */
	@Bean
	public JwtAuthorizationTokenFilter authenticationTokenFilterBean() throws Exception {
		return new JwtAuthorizationTokenFilter();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoderBean());
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

			// 由于使用的是JWT，我们这里不需要csrf
		httpSecurity.csrf().disable()
			// 认证失败的处理器
			.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
			// 基于token，所以不需要session
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests()
			// 以/auth/开头的请求可以直接访问
			.antMatchers(authenticationPath).permitAll()
			// 下面的请求也可以直接通过
			.antMatchers(
				HttpMethod.GET,
				"/",
				"/*.html",
				"/**/*.css",
				"/**/*.js",
				"/favicon.ico"
			).permitAll()
			// 其他的请求都需要验证
			.anyRequest().authenticated();

		// 在返回直接先进行过滤
		httpSecurity
			.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

		// 禁用缓存
		httpSecurity.headers().cacheControl();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// AuthenticationTokenFilter 将会忽略下面的路径 可以直接返回
		web.ignoring()
			.antMatchers(
				HttpMethod.POST,
				authenticationPath
			)
			.and()
			.ignoring()
			.antMatchers(
				HttpMethod.GET,
				"/",
				"/*.html",
				"/**/*.css",
				"/**/*.js",
				"/favicon.ico"
			);
	}

}
