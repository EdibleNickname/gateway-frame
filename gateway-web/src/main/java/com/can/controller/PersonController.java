package com.can.controller;

import com.can.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: LCN
 * @date: 2018-05-17 15:04
 */

@RestController
public class PersonController {

	private static final List<Person> persons;

	static {
		persons = new ArrayList<>();
		persons.add(new Person("用户01", "123456@162.com"));
		persons.add(new Person("用户02", "123456@qq.com"));
	}

	@GetMapping("/persons")
	public static List<Person> getPersons() {
		return persons;
	}

	@GetMapping("/persons/{name}")
	public static Person getPerson(@PathVariable("name") String name) {
		return persons.stream()
				.filter( person -> name.equalsIgnoreCase(person.getName()))
				.findAny().orElse(null);
	}

}
