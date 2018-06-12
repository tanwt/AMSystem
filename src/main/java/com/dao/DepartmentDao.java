package com.dao;

import com.bean.Department;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentDao {

    /**
     * 获得所有的部门信息
     * @return
     */
    public List<String> getAllDepartment() throws SQLException;
}
