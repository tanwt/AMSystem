package com.service.Impl;

import com.bean.ClockCard;
import com.bean.Leave;
import com.bean.LeaveReturn;
import com.bean.User;
import com.dao.LeaveDao;
import com.dao.UserDao;
import com.mapper.LeaveMapper;
import com.mapper.UserMapper;
import com.service.LeaveService;
import com.utils.CheckNull;
import com.utils.DateConversion;
import com.utils.Verify;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * create by tanwt
 * 2018/5/8 0008 19:23
 **/
@Service
public class LeaveServiceImpl implements LeaveService{

    private LeaveDao leaveDao = new LeaveMapper();

    private UserDao userDao = new UserMapper();

    @Override
    public int addLeave(Leave leave) throws Exception {
        try {
            leave.setDate(DateConversion.getYMD(new Date()));
            System.out.println("更新的请假信息：" + leave);
            if (CheckNull.checkObject(leave,new String[]{"site","auditorId","rejection"}) != null){
                throw new Exception("传值错误");
            }
            int result = -1;
            result = leaveDao.addLeave(leave);
            return result;
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception("系统未知错误");
        }
    }

    @Override
    public List<Leave> getAllLeave() throws Exception {
        try {
            List list = null;
            list = leaveDao.getAllLeave();
            return list;
        }catch (Exception e){
            throw new Exception("系统错误");
        }
    }

    @Override
    public int updateLeave(Leave leave) throws Exception {
        try {
            if (CheckNull.checkObject(leave,new String[]{"site","auditorId","rejection"}) != null){
                throw new Exception("传值错误");
            }
            int result = -1;
            result = leaveDao.updateLeave(leave);
            return result;
        }catch (SQLException e){
            throw new Exception("系统数据异常");
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception("系统未知错误");
        }
    }

    @Override
    public int deleteLeave(int leaveId) throws Exception {
        try {
            return leaveDao.deleteLeave(leaveId);
        }catch (Exception e){
            throw new Exception("系统未知错误");
        }
    }

    @Override
    public List<LeaveReturn> getAllLeaveById(Integer userId) throws Exception {
        try {
            List<Leave> all = leaveDao.getAllLeave();
            List<Leave> my = new ArrayList();
            for (Leave temp:all) {
                if (temp.getUserId() == userId)
                    my.add(temp);
            }
            List<LeaveReturn> list = new ArrayList<LeaveReturn>();
            for(Leave temp:my){
                if (temp.getAuditorId() != 0){
                    User user = new User();
                    user.setUserId(temp.getAuditorId());
                    String name = userDao.selectUserByNameOREmailORId(user).get(0).getUserName();
                    LeaveReturn leaveReturn = new LeaveReturn(temp,null,name);
                    list.add(leaveReturn);
                }else {
                    LeaveReturn leaveReturn = new LeaveReturn(temp,null,null);
                    list.add(leaveReturn);
                }
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("数据异常");
        }
    }

    @Override
    public List<LeaveReturn> getOtherLeaveById(Integer userId) throws Exception {
        try {
            User user = new User();
            user.setUserId(userId);
            user = userDao.selectUserByNameOREmailORId(user).get(0);
            List<User> userList = userDao.getUserIDByDepartment(user.getUserDepartment());
            List<Leave> all = leaveDao.getAllLeave();
            List<Leave> my = new ArrayList();
            for (Leave temp:all) {
                for (User userTemp:userList){
                    if (temp.getUserId() == userTemp.getUserId())
                        my.add(temp);
                }
            }
            List<LeaveReturn> listReturn = new ArrayList<LeaveReturn>();
            for(Leave temp:my){
                User user1 = new User();
                user1.setUserId(temp.getUserId());
                User user2 = new User();
                LeaveReturn leaveReturn = null;
                if (temp.getAuditorId() > 0){
                    user2.setUserId(temp.getAuditorId());
                    leaveReturn = new LeaveReturn(temp,
                            userDao.selectUserByNameOREmailORId(user1).get(0).getUserName(),
                            userDao.selectUserByNameOREmailORId(user2).get(0).getUserName());
                }else {
                    leaveReturn = new LeaveReturn(temp,
                            userDao.selectUserByNameOREmailORId(user1).get(0).getUserName(),
                            null);
                }
                listReturn.add(leaveReturn);
            }
            return listReturn;
        }catch (Exception e){
            throw new Exception("数据异常");
        }
    }
}
