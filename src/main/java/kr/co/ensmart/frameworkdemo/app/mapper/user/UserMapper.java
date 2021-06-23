package kr.co.ensmart.frameworkdemo.app.mapper.user;

import kr.co.ensmart.frameworkdemo.common.dto.User;

public interface UserMapper {
	User findByUserName(String username);
}
