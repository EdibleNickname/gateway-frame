package com.can.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: LCN
 * @date: 2018-05-17 14:24
 */

@RestController
@RequestMapping("/protected")
public class ProtectedController {

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/msg")
	public ResponseEntity<?> getProtectedGreeting() {
		return ResponseEntity.ok("这是一个受保护的方法，只有admin才能访问,恭喜你返回成功，admin用户");
	}

}
