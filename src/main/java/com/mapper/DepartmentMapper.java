package com.mapper;

import com.bean.Department;
import com.bean.User;
import com.dao.DepartmentDao;
import com.dao.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DepartmentMapper implements DepartmentDao{

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	public DepartmentMapper() {
		// TODO Auto-generated constructor stub
		con = new DataBaseConnect().getConnection();
	}

	@Override
	public List<String> getAllDepartment() throws SQLException {
		con = new DataBaseConnect().getConnection();
		sql = "select * from am_department";
		List<String> list = new ArrayList<String>();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			Department department;
			while (rs.next()){
				list.add(rs.getString(2));
			}
			if (!list.isEmpty())
				return list;

		} catch (SQLException e) {
			System.out.println("getAllDepartment连接数据库异常");
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
