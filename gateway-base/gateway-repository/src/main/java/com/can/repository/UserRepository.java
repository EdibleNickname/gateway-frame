package com.can.repository;

import com.can.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: LCN
 * @date: 2018-05-20 22:41
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String userName);

}
