package com.wu.store.controller;

import com.wu.store.bean.EmailMessage;
import com.wu.store.bean.Msg;
import com.wu.store.bean.User;
import com.wu.store.bean.UserCustom;
import com.wu.store.service.UserService;
import com.wu.store.util.JavaMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController(value = "/user")
public class UserController
{
	@Autowired
	private UserService userService;

	/*
	* 用户注册
	* */
	@RequestMapping(value = "registerUser.do")
	public Msg UserRegister(@RequestBody UserCustom user)
	{
		user.setUid(UUID.randomUUID().toString());
		if(userService.addUser(user) <= 0)
		{
			return Msg.fail();
		}
		else {
//			设置注册成功后自动登录
//			httpSession.setAttribute("user", user);
			return Msg.succes();
		}
	}

	/*
	* 校验验证码
	* */
	@RequestMapping(value = "checkAuthCode.do")
	public Msg checkAuthCode(UserCustom user, HttpSession httpSession, HttpServletRequest req, HttpServletResponse resp) throws Exception
	{
		String authCode = (String) httpSession.getAttribute("authCode");
		if (user.getAuthCode().equals(authCode))
		{
			req.getRequestDispatcher("/register.do").forward(req, resp);
		}
		return Msg.fail();
	}

	/*
	* 通过邮箱发送验证码
	* */
	@RequestMapping(value = "sendAuthCode.do")
	public Msg sendAuthCode(User user, HttpSession httpSession)
	{
		String authCode = String.valueOf(Math.round(Math.random()*1000000));
		httpSession.setAttribute("email", user.getEmail());
		httpSession.setAttribute("authCode", authCode);
		EmailMessage emailMessage = new EmailMessage();
		emailMessage.receive= user.getEmail();
		emailMessage.subject="验证码";
		emailMessage.content= "您的验证码为：" + authCode;
		try {
			JavaMail.sendEmail(emailMessage);
			return Msg.succes();
		}catch (Exception e){
			return Msg.fail();
		}
	}

	/*
	* 用户登录
	* */
	@RequestMapping(value = "userLogin.do")
	public Msg userLogin(User user, HttpSession httpSession)
	{
		User user2 = userService.login(user);
		if(user2.getUsername()!=null && user2.getPassword()!=null)
		{
			httpSession.setAttribute("user", user2);
			user2.setPassword("");
			return Msg.succes().add("user", user2);
		}
		return Msg.fail();
	}

	/*
	* 检查用户是否登录
	* */
	@RequestMapping(value = "checkLogin.do")
	public Msg checkLogin(HttpSession httpSession)
	{
		User user = (User)httpSession.getAttribute("user");
		if(user.getUsername()==null || user.getEmail()==null)
		{
			return Msg.fail();
		}
		return Msg.succes().add("user", user);
	}

	/*
	* 检查邮箱是否被注册过
	* */
	@RequestMapping(value = "checkEmail.do")
	public Msg checkEmail(String email)
	{
		if (userService.checkEmail(email) == 0)
		{
			return Msg.succes();
		}
		return Msg.fail();
	}
}
