package com.wu.store.bean;

import java.util.HashMap;
import java.util.Map;

public class Msg
{
	//提示码：100-成功  200-失败
	private int code;
	//提示信息
	private String msg;
	//返还给浏览器的数据
	private Map<String, Object> extend = new HashMap<>();

	public Msg add(String key, Object value)
	{
		this.getExtend().put(key, value);
		return this;
	}

	public static Msg succes()
	{
		Msg result = new Msg();
		result.setCode(100);
		result.setMsg("成功");
		return result;
	}

	public static Msg fail()
	{
		Msg result = new Msg();
		result.setCode(200);
		result.setMsg("失败");
		return result;
	}

	public int getCode()
	{
		return code;
	}

	public void setCode(int code)
	{
		this.code = code;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public Map<String, Object> getExtend()
	{
		return extend;
	}

	public void setExtend(Map<String, Object> extend)
	{
		this.extend = extend;
	}
}
