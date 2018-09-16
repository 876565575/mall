package com.wu.store.util;

import com.wu.store.bean.EmailMessage;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class JavaMail
{
	public static void main(String[] args) throws Exception
	{
		EmailMessage emailMessage = new EmailMessage();
		String yzm = String.valueOf(Math.round(Math.random()*1000000));
		emailMessage.receive="876565575@qq.com";
		emailMessage.subject="你的验证码";
		emailMessage.content="验证码：" + yzm;
		sendEmail(emailMessage);
		System.out.println(yzm);
	}
	
	//发送邮件
	public static void sendEmail(EmailMessage emailMessage) throws Exception
	{
		if(emailMessage.receive==null || emailMessage.subject == null || emailMessage.content == null)
		{
			throw new Exception("错误");
		}
		Session session = getMailSession();
//		session.setDebug(true);
		MimeMessage message = creatMimeMessage(session, "876565575@qq.com", emailMessage);
		Transport transport = session.getTransport();//建立连接对象
		transport.connect("876565575@qq.com", "tyzpgiqfbvwlbdbd");//建立连接、其中密码以授权码的形式体现
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}
	//MimeMessage:邮件
	public static MimeMessage creatMimeMessage(Session session, String send, EmailMessage emailMessage) throws Exception
	{
		MimeMessage message = new MimeMessage(session);
		//邮件：标题、正文、收件人 {图片，附件}
		Address address = new InternetAddress(send,"发件人的名字");
		message.setFrom(address);
		message.setSubject(emailMessage.subject);
		message.setContent(emailMessage.content, "text/html; charset=utf-8");
		message.setSentDate(new Date());
		//收件人类型：普通收件人、抄送、密送
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(emailMessage.receive));
		message.saveChanges();
		return message;		
	}
	//获得mailSession
	public static Session getMailSession()
	{
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");//使用协议：smtp
		props.setProperty("mail.smtp.host", "smtp.qq.com");//协议地址
		props.setProperty("mail.smtp.port", "465");//协议端口
		props.setProperty("mail.smtp.auth", "true");//需要授权
		//qq：ssl安全认证
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		return Session.getInstance(props);
	}
}
