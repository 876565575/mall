package com.wu.store.service;

import com.wu.store.bean.User;
import com.wu.store.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
	@Autowired
	private UserMapper userMapper;

	public void addUser(User user)
	{
		userMapper.insert(user);
	}
}
