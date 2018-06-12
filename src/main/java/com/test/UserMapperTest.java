package com.test;

import com.bean.User;
import com.dao.UserDao;
import com.mapper.UserMapper;
import com.service.Impl.ClockCardServiceImpl;
import com.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class UserMapperTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Test
    public void userDaoTest() throws SQLException {
        User user = new User();
        //selectUserByNameOREmailORId
//        user.setUserId(2);
//        System.out.println(userDao.selectUserByNameOREmailORId(user));
        //updateUserById
//        user = userDao.selectUserByNameOREmailORId(user).get(0);
//        user.setUserPhone("13648404959");
//        System.out.println(userDao.updateUserById(user));
        //测试登录
//        System.out.println(userDao.userLogin("admins","admin"));
        //测试获取同部门人员信息
        for (int i = 0 ; i < 100;i++){
            System.out.println(new UserMapper().userLogin("admin","admin"));
            System.out.println(i);
        }
    }

    @Test
    public void userImplTest() {
        /**
        //测试注册
        User user = new User("text","text",'M',0,"13548404959","788857684@qq.com","行政部",0);
        try {
            System.out.println(userService.userRegister(user));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
         **/

        //测试登录
        /*try {
            System.out.println(userService.userLogin("admins","admin"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/

        //测试更新
        /*User user = new User();
        user.setUserId(2);
        user = userDao.selectUserByNameOREmailORId(user).get(0);
        user.setUserPhone("13648404959");
        try {
            System.out.println(userService.updateUserById(user));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/
    }
}
