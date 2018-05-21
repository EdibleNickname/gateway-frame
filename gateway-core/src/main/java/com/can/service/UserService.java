package com.can.service;


import com.can.entity.User;

/**
 * @description:
 * @author: LCN
 * @date: 2018-05-20 22:44
 */

public interface UserService {

	/**
	 * 通过用户名查询整个用户信息
	 * @param userName 用户名
	 *
	 * @return
	 */
	User findUserByUserName(String userName);

}
