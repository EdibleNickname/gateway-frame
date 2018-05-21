package com.can.dao;

import com.can.entity.Role;
import com.can.entity.User;

import java.util.Set;

public interface UserMapper {

    User selectUserByUserName(String userName);

    Set<Role> getRolesByUserId(Long userId);

    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}