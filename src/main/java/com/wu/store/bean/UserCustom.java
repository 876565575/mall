package com.wu.store.bean;

public class UserCustom extends User
{
	private String authCode;

	public String getAuthCode()
	{
		return authCode;
	}

	public void setAuthCode(String authCode)
	{
		this.authCode = authCode;
	}
}
