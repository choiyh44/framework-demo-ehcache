package kr.co.ensmart.frameworkdemo.app.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import kr.co.ensmart.frameworkdemo.app.mapper.user.UserMapper;
import kr.co.ensmart.frameworkdemo.common.dto.User;

@Service
public class CustomUserDetailsService {
	@Autowired
	private UserMapper userMapper;
	
	@Cacheable(value="userService", key="#userName")
	public User findByUserName(String userName) {
		return userMapper.findByUserName(userName);
	}

}
