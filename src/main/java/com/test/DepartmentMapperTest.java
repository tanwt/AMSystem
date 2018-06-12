package com.test;

import com.bean.User;
import com.dao.DepartmentDao;
import com.dao.UserDao;
import com.service.DepartmentService;
import com.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class DepartmentMapperTest {

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private DepartmentService departmentService;

    @Test
    public void DepartmentDaoTest() throws SQLException {
        System.out.println(departmentDao.getAllDepartment());
    }

    @Test
    public void DepartmentImplTest() throws Exception {
        System.out.println(departmentService.getAllDepartment());
    }
}
