package com.wu.store.controller;

import com.wu.store.bean.Msg;
import com.wu.store.bean.User;
import com.wu.store.service.UserService;
import org.apache.ibatis.annotations.Lang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController(value = "/user")
public class UserController
{
	@Autowired
	private UserService userService;

	@RequestMapping(value = "register.do")
	public Msg UserRegister(@RequestBody User user)
	{
		user.setUid(UUID.randomUUID().toString());
		userService.addUser(user);
		return Msg.succes();
	}
}
