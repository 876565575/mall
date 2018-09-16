package com.wu.store.service.serviceImp;

import com.wu.store.bean.User;
import com.wu.store.bean.UserExample;
import com.wu.store.dao.UserMapper;
import com.wu.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImp implements UserService
{
	@Autowired
	private UserMapper mapper;
	@Override
	public int addUser(User user)
	{
		//实现注册功能
		return mapper.insertSelective(user);
	}

	@Override
	public User login(User user)
	{
		return mapper.Login(user);
	}

	@Override
	public int checkEmail(String email)
	{
		UserExample userExample = new UserExample();
		userExample.createCriteria().andEmailEqualTo(email);
		List<User> users = mapper.selectByExample(userExample);
		return users.size();
	}
}
