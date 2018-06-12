package com.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by tanwt
 * 2018/5/8 0008 16:29
 **/
@Service
public interface DepartmentService {
    /**
     * 获得所有的部门信息
     * @return
     */
    public List<String> getAllDepartment() throws Exception;
}
