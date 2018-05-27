package com.can.dao;

import com.can.entity.Role;
import com.can.entity.User;
import com.can.util.UserPage;

import java.util.List;
import java.util.Set;

public interface UserMapper {

    User selectUserByUserName(String userName);

    Set<Role> getRolesByUserId(Long userId);

    /** TODO: 2018/5/22 待完善 利用sql把用户和用户角色一次性查出来
        User selectAllUserInfo(Long userId);
    */

    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 测试分页用
     *
     * @param user 参数
     * @return
     */
    List<User> selectAllRole(UserPage user);

}