package com.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.bean.User;
import com.dao.UserDao;


public class UserMapper implements UserDao{

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	public UserMapper() {
		// TODO Auto-generated constructor stub
		con = new DataBaseConnect().getConnection();
	}

	@Override
	public int userRegister(User user) throws SQLException {
		con = new DataBaseConnect().getConnection();
		sql = "INSERT INTO am_user(am_user_name,am_user_password,am_user_sex,am_user_marriage,am_user_phone,am_user_mail,am_user_department)\n" +
				"      VALUE (?,?,?,?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,user.getUserName());
			ps.setString(2,user.getUserPassWord());
			ps.setString(3, String.valueOf(user.getUserSex()));
			ps.setInt(4,user.getUserMarriage());
			ps.setString(5,user.getUserPhone());
			ps.setString(6,user.getUserMail());
			ps.setString(7,user.getUserDepartment());
			int reslut = ps.executeUpdate();
			return reslut;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("注册数据库异常");
		}finally {
			if (con != null)
				con.close();
			if (ps != null)
				ps.close();
			if (rs != null)
				rs.close();
		}
		return 0;
	}

	@Override
	public List<User> selectUserByNameOREmailORId(User user) throws SQLException {
		con = new DataBaseConnect().getConnection();
		sql = " SELECT * FROM am_user WHERE am_user_name=? OR am_user_mail=? OR am_user_phone=? OR am_user_id=? ";
		List<User> userList = new ArrayList<User>();
		User reslut ;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,user.getUserName());
			ps.setString(2,user.getUserMail());
			ps.setString(3,user.getUserPhone());
			ps.setInt(4,user.getUserId());
			rs = ps.executeQuery();
			while (rs.next()){
				reslut = new User();
				reslut.setUserId(rs.getInt(1));
				reslut.setUserName(rs.getString(2));
				reslut.setUserPassWord(rs.getString(3));
				reslut.setUserSex(rs.getString(4).charAt(0));
				reslut.setUserMarriage(rs.getInt(5));
				reslut.setUserPhone(rs.getString(6));
				reslut.setUserMail(rs.getString(7));
				reslut.setUserDepartment(rs.getString(8));
				reslut.setUserState(rs.getInt(9));
				userList.add(reslut);
			}
			return userList;
		} catch (SQLException e) {
			System.out.println("获取user信息失败");
			e.printStackTrace();
		}finally {
			if (con != null)
				con.close();
			if (ps != null)
				ps.close();
			if (rs != null)
				rs.close();
		}
		return null;
	}

	@Override
	public int updateUserById(User user) throws SQLException {
		con = new DataBaseConnect().getConnection();
		sql = "update am_user set am_user_name=?,am_user_password=?,am_user_sex=?,am_user_marriage=?,am_user_phone=?,am_user_mail=?,am_user_department=?" +
				" where am_user_id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,user.getUserName());
			ps.setString(2,user.getUserPassWord());
			ps.setString(3,String.valueOf(user.getUserSex()));
			ps.setInt(4,user.getUserMarriage());
			ps.setString(5,user.getUserPhone());
			ps.setString(6,user.getUserMail());
			ps.setString(7,user.getUserDepartment());
			ps.setInt(8,user.getUserId());
			int result = ps.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (con != null)
				con.close();
			if (ps != null)
				ps.close();
			if (rs != null)
				rs.close();
		}
		return 0;
	}

	@Override
	public User userLogin(String loginName, String password) throws SQLException {
		con = new DataBaseConnect().getConnection();
		sql = "SELECT * FROM am_user WHERE (am_user_name=? OR am_user_mail=? OR am_user_phone=?) AND am_user_password=?";
		User reslut = new User();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,loginName);
			ps.setString(2,loginName);
			ps.setString(3,loginName);
			ps.setString(4,password);
			rs = ps.executeQuery();
			if (rs.next()){
				reslut.setUserId(rs.getInt(1));
				reslut.setUserName(rs.getString(2));
				reslut.setUserPassWord(rs.getString(3));
				reslut.setUserSex(rs.getString(4).charAt(0));
				reslut.setUserMarriage(rs.getInt(5));
				reslut.setUserPhone(rs.getString(6));
				reslut.setUserMail(rs.getString(7));
				reslut.setUserDepartment(rs.getString(8));
				reslut.setUserState(rs.getInt(9));
				return reslut;
			}
		} catch (SQLException e) {
			System.out.println("获取user信息失败");
			e.printStackTrace();
		}finally {
			if (con != null)
				con.close();
			if (ps != null)
				ps.close();
			if (rs != null)
				rs.close();
		}
		return null;
	}

	@Override
	public List<User> getUserIDByDepartment(String department) throws SQLException {
		con = new DataBaseConnect().getConnection();
		sql = "SELECT * FROM am_user WHERE am_user_department=?";
		List<User> users = new LinkedList<User>();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,department);
			rs = ps.executeQuery();
			while (rs.next()){
				User reslut = new User();
				reslut.setUserId(rs.getInt(1));
				reslut.setUserName(rs.getString(2));
				reslut.setUserPassWord(rs.getString(3));
				reslut.setUserSex(rs.getString(4).charAt(0));
				reslut.setUserMarriage(rs.getInt(5));
				reslut.setUserPhone(rs.getString(6));
				reslut.setUserMail(rs.getString(7));
				reslut.setUserDepartment(rs.getString(8));
				reslut.setUserState(rs.getInt(9));
				users.add(reslut);
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (con != null)
				con.close();
			if (ps != null)
				ps.close();
			if (rs != null)
				rs.close();
		}
		return null;
	}


}
