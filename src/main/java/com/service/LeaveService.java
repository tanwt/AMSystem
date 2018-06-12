package com.service;

import com.bean.ClockCard;
import com.bean.Leave;
import com.bean.LeaveReturn;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by tanwt
 * 2018/5/8 0008 19:22
 **/
@Service
public interface LeaveService {
    /**
     * 添加请假信息
     * @return
     */
    public int addLeave(Leave leave) throws Exception;

    /**
     * 获得所有请假信息
     * @return
     */
    public List<Leave> getAllLeave() throws Exception;

    /**
     * 更新请假信息
     * @param leave
     * @return
     */
    public int updateLeave(Leave leave) throws Exception;

    /**
     * 删除请假信息
     * @param leaveId
     * @return
     */
    public int deleteLeave(int leaveId) throws Exception;

    /**
     * 通过id得到请假记录
     * @return
     */
    public List<LeaveReturn> getAllLeaveById(Integer userId) throws Exception;

    /**
     * 管理员
     * 通过id得到部门其他人请假记录
     * @return
     */
    public List<LeaveReturn> getOtherLeaveById(Integer userId) throws Exception;
}
