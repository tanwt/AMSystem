package com.service.Impl;

import com.bean.User;
import com.dao.UserDao;
import com.service.UserService;
import com.utils.CheckNull;
import com.utils.Verify;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserDao userDao;

    /**
     * 注册
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public int userRegister(User user) throws Exception {
        if (!userDao.selectUserByNameOREmailORId(user).isEmpty())
            throw new Exception("重复注册");
        if (CheckNull.checkObject(user,null) != null){
            throw new Exception("错误的注册信息");
        }
        String verify = Verify.vaildUser(user);
        if (verify != null)
            throw new Exception(verify);
        int temp = userDao.userRegister(user);
        if (temp == 0)
            throw new Exception("注册失败");
        return temp;
    }

    /**
     * 登录
     * @param loginName
     * @param password
     * @return
     */
    @Override
    public User userLogin(String loginName, String password) throws Exception {
        User user = null;
        user = userDao.userLogin(loginName,password);
        if (user == null)
            throw new Exception("登录失败");
        return user;
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    @Override
    public User updateUserById(User user) throws Exception {
        if (CheckNull.checkObject(user,null) != null)
            throw new Exception("错误的更新信息");
        String verify = Verify.vaildUser(user);
        if (verify != null)
            throw new Exception(verify);
        if (userDao.selectUserByNameOREmailORId(user).size() > 1)
            throw new Exception("更新信息已存在");
        int result = userDao.updateUserById(user);
        if (result == 0)
            throw new Exception("更新失败");
        user = userDao.selectUserByNameOREmailORId(user).get(0);
        return user;
    }


}
