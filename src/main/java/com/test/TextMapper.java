package com.test;

import com.alibaba.fastjson.JSONObject;
import com.bean.Leave;
import com.bean.User;
import com.dao.UserDao;
import com.mapper.ClockCardMapper;
import com.mapper.DepartmentMapper;
import com.mapper.LeaveMapper;
import com.mapper.UserMapper;
import com.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.SQLException;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TextMapper {


    @Test
    public void text() throws SQLException {
//        User user = new User("tanwt","1234567",'M',0,"17783139574","13648404959@163.com","行政部",0);
//        System.out.println(new UserMapper().userRegister(user));
//        User user = new User();
//        user.setUserMail("1586559635@qq.com");
//        user.setUserId(4);
//        System.out.println(new UserMapper().updateUserById(user));
//        System.out.println(new UserMapper().userLogin("799957684@qq.com","admin"));
//        System.out.println(new UserMapper().getUserIDByDepartment("行政部"));
        System.out.println(new UserMapper().userLogin("text1","text2"));
    }


    @Test
    public void textDepartment() throws SQLException {
        System.out.println(new DepartmentMapper().getAllDepartment());

    }
    @Test
    public void Clock() throws SQLException {
//        System.out.println(new ClockCardMapper().startWork(4));
//        System.out.println(new ClockCardMapper().endWork(4));
        System.out.println(new ClockCardMapper().getAllClockInformation().get(0));

    }

    @Test
    public void leave() throws SQLException {
        Leave leave = new Leave();
        leave.setUserId(4);
        leave.setDate("2018-4-16");
        leave.setStart("2018-5-01");
        leave.setEnd("2018-6-01");
        leave.setReason("到上海出差");
        leave.setSite("上海");
        leave.setType(2);
        leave.setIsExigency(1);
//        System.out.println(new LeaveMapper().addLeave(leave));
        System.out.println(new LeaveMapper().deleteLeave(15));
//        System.out.println(new LeaveMapper().getAllLeave());
    }
}
