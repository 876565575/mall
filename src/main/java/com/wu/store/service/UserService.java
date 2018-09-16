package com.wu.store.service;

import com.wu.store.bean.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService
{
	int addUser(User user);

	User login(User user);

	int checkEmail(String email);
}
