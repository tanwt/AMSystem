package com.service.Impl;

import com.dao.DepartmentDao;
import com.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * create by tanwt
 * 2018/5/8 0008 16:30
 **/
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentDao departmentDao;

    @Override
    public List<String> getAllDepartment() throws Exception {
        List list = null;
        try {
            list = departmentDao.getAllDepartment();
        }catch (Exception e){
            throw new Exception("系统错误");
        }
        return list;
    }
}
