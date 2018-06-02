package com.can.service;


import com.can.entity.User;
import com.can.util.Page;
import com.can.util.UserRequest;

/**
 * @description:
 * @author: LCN
 * @date: 2018-05-20 22:44
 */

public interface UserService {

	@interface UserGrop{
	}

	/**
	 * 通过用户名查询整个用户信息
	 * @param userName 用户名
	 *
	 * @return
	 */
	User findUserByUserName(String userName);

	/**
	 * 分页查询
	 * @param requset
	 * @return
	 */
	Page<User> searchUserPage(UserRequest requset);
}
