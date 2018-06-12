package com.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;
import org.junit.Test;

/**
 * 邮件发送
 */
public class MailUtil implements  Runnable{
	 private String email;// 收件人邮箱
	    private String code;// 激活码
	 	private Properties mailProperties;//获取发送者邮箱和授权码
	    public MailUtil(String email, String code) {
	        this.email = email;
	        this.code = code;
			this.mailProperties = new Properties();
			InputStream in = MailUtil.class.getClassLoader().getResourceAsStream("mail.properties");
			try {
				mailProperties.load(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	 
	    public void run() {
	        // 1.创建连接对象javax.mail.Session
	        // 2.创建邮件对象 javax.mail.Message
	        // 3.发送一封激活邮件
	        String from = "799957684@qq.com";// 发件人电子邮箱
	        String host = "smtp.qq.com"; // 指定发送邮件的主机smtp.qq.com(QQ)|smtp.163.com(网易)



	        Properties properties = System.getProperties();// 获取系统属性
	 
	        properties.setProperty("mail.smtp.host", host);// 设置邮件服务器
	        properties.setProperty("mail.smtp.auth", "true");// 打开认证
	 
	        try {
	            //QQ邮箱需要下面这段代码，163邮箱不需要
	            MailSSLSocketFactory sf = new MailSSLSocketFactory();
	            sf.setTrustAllHosts(true);
	            properties.put("mail.smtp.ssl.enable", "true");
	            properties.put("mail.smtp.ssl.socketFactory", sf);
				// 1.获取默认session对象
	            Session session = Session.getDefaultInstance(properties, new Authenticator() {
	                public PasswordAuthentication getPasswordAuthentication() {
	                    return new PasswordAuthentication( mailProperties.getProperty("host"), mailProperties.getProperty("password")); // 发件人邮箱账号、授权码
	                }
	            });
	 
	            // 2.创建邮件对象
	            Message message = new MimeMessage(session);
	            // 2.1设置发件人
	            message.setFrom(new InternetAddress(from));
	            // 2.2设置接收人
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
	            // 2.3设置邮件主题
	            message.setSubject("账号激活");
	            // 2.4设置邮件内容
	            String content = "<html><head></head><body><h1>欢迎使用***考勤管理系统，本次验证码<u><i>"
	                    + code + "</u></i></h1></body></html>";
	            message.setContent(content, "text/html;charset=UTF-8");
	            // 3.发送邮件
	            Transport.send(message);
//	            return "1";
	        } catch (Exception e) {
//				return "0";
			}
	    }
//		public static void main(String args[]){
//			System.out.println(new MailUtil("1586559635@qq.com","123").sendMail());
//		}
}
