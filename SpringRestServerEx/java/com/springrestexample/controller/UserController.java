package com.springrestexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springrestexample.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="getUserInfo/{userId}",
			method=RequestMethod.GET)
	@ResponseBody
public String getUserDetails
(@PathVariable("userId") Integer userId){
	 String jsonUser=userService.getUserDetails(userId);
	 return jsonUser;
}
}
