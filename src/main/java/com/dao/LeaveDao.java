package com.dao;

import com.bean.Leave;

import java.sql.SQLException;
import java.util.List;

public interface LeaveDao {
    /**
     * 添加请假信息
     * @return
     */
    public int addLeave(Leave leave) throws SQLException;

    /**
     * 获得所有请假信息
     * @return
     */
    public List<Leave> getAllLeave() throws SQLException;

    /**
     * 更新请假信息
     * @param leave
     * @return
     */
    public int updateLeave(Leave leave) throws SQLException;

    /**
     * 删除请假信息
     * @param leaveId
     * @return
     */
    public int deleteLeave(int leaveId) throws SQLException;
}
