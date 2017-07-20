package com.springrestexample.service;

import org.springframework.stereotype.Service;

import com.springrestexample.domain.User;
import com.springrestexample.util.JsonUtil;

@Service
public class UserServiceImpl 
implements UserService{
public String getUserDetails(Integer userId) {
	String jsonUser="{}";	
	if(userId!=null && userId.equals(101)){
		User user=new User();
		user.setUserId(101);
		user.setUserName("rama");
		user.setEmail("rama@gmail.com");
		user.setMobile("999999");
	jsonUser=JsonUtil.javaToJson(user);	
		}
		return jsonUser;
	}

}
