package com.dao;

import com.bean.User;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserDao {

    /**
     * 用户注册
     * @Param   user    传入的用户对象
     * @return  int ：1 注册成功，0注册失败
     */
    public int userRegister(@Param("user") User user) throws SQLException;

    /**
     * 查询用户信息
     * 通过id name phone email
     * @param user      传入的用户对象
     * @return  int :   1 存在 0 不存在
     */
    public List<User> selectUserByNameOREmailORId(@Param("user")User user) throws SQLException;

    /**
     * 根据用户ID 更新用户信息
     * @param user
     * @return
     */
    public int updateUserById(@Param("user") User user) throws SQLException;

    /**
     * 用户登录
     * @param loginName     手机/邮箱/用户名
     * @param password
     * @return
     */
    public User userLogin(@Param("loginName")String loginName,
                         @Param("password")String password) throws SQLException;

    /**
     * 根据部门获取用户id
     * @param department
     * @return
     */
    public List<User> getUserIDByDepartment(@Param("department") String department) throws SQLException;
}
