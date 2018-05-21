package com.can;

import com.can.entity.User;
import com.can.entity.UserRole;
import com.can.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationLaucherTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void contextLoads() {

		User user = userRepository.findByUserName("admin");

		System.out.println(user.getUserName());

		Iterator<UserRole> iterator = user.getUserRoles().iterator();
		while (iterator.hasNext()){
			UserRole userRole = iterator.next();
			System.out.println(userRole.getRole());
		}

	}

}
