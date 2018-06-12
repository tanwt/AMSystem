package com.service;

import com.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    /**
     * 用户注册
     * @return  int ：1 注册成功，0注册失败
     */
    public int userRegister(User user) throws Exception;

    /**
     * 用户登录
     * @param loginName
     * @param password
     * @return
     */
    public User userLogin(String loginName, String password) throws Exception;

    /**
     * 通过用户ID更新用户
     * @param user
     * @return
     */
    public User updateUserById(User user) throws Exception;

}
