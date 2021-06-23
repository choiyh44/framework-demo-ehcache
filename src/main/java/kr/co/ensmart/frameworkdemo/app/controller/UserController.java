package kr.co.ensmart.frameworkdemo.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ensmart.frameworkdemo.app.service.user.CustomUserDetailsService;
import kr.co.ensmart.frameworkdemo.common.dto.User;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private CustomUserDetailsService userService;
	
	@GetMapping("")
	public User findByUserName(@RequestParam String userName) {
		return userService.findByUserName(userName);
	}
}
