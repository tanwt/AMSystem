package com.mapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;


public class DataBaseConnect {
	public static final String DB_URL = "jdbc:mysql://120.79.94.90:3306/amsystem?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false";
	public static final String DB_PASSWORD = "123456";
//	public static final String DB_URL = "jdbc:mysql://localhost:3306/amsystem?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false";
//	public static final String DB_PASSWORD = "wt2016210989";
	private Connection con;
	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_ROOT = "root";
	public DataBaseConnect() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName(DB_DRIVER);
			con = DriverManager.getConnection(DB_URL, DB_ROOT, DB_PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("数据库驱动异常，未添加数据库连接包");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("数据库连接异常");
			e.printStackTrace();
		}
		
	}
	
	public Connection getConnection(){
		return con;
	}
	
	@Test
	public void textConnection(){
		System.out.println(getConnection());
	}
}
