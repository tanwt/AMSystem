package com.service.Impl;

import com.bean.ClockCard;
import com.bean.ClockReturn;
import com.bean.Leave;
import com.bean.User;
import com.dao.ClockCardDao;
import com.dao.UserDao;
import com.mapper.ClockCardMapper;
import com.mapper.UserMapper;
import com.service.ClockCardService;
import com.utils.CheckNull;
import com.utils.CommonUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * create by tanwt
 * 2018/5/8 0008 18:34
 **/
@Service
public class ClockCardServiceImpl implements ClockCardService{

    private ClockCardDao clockCardDao = new ClockCardMapper();

    private UserDao userDao = new UserMapper();

    @Override
    public int startWork(int userId) throws Exception {

        try {
            if (CheckNull.checkString(String.valueOf(userId)))
                throw new Exception("未传入值");
            int result = -1;
            result = clockCardDao.startWork(userId);
            return result;
        }catch (Exception e){
            throw new Exception("系统异常");
        }
    }

    @Override
    public int endWork(int userId) throws Exception {
        try {
            if (CheckNull.checkString(String.valueOf(userId)))
                throw new Exception("未传入值");
            int result = -1;
            result = clockCardDao.endWork(userId);
            return result;
        }catch (Exception e){
            throw new Exception("系统异常");
        }
    }

    @Override
    public List<ClockCard> getAllClockInformation() throws Exception {
        try {
            List list = null;
            list = clockCardDao.getAllClockInformation();
            return list;
        }catch (Exception e){
            throw new Exception("系统错误");
        }
    }

    @Override
    public List<ClockCard> getAllClockById(Integer userId) throws Exception {
        try {
            List<ClockCard> all = getAllClockInformation();
            List<ClockCard> my = new ArrayList();
            for (ClockCard temp:all) {
                if (temp.getUserId() == userId)
                    my.add(temp);
            }
            return my;
        }catch (Exception e){
            throw new Exception("数据异常");
        }
    }

    @Override
    public List<ClockReturn> getOtherClockById(Integer userId) throws Exception {
        try {
            User user = new User();
            user.setUserId(userId);
            user = userDao.selectUserByNameOREmailORId(user).get(0);
            List<User> userList = userDao.getUserIDByDepartment(user.getUserDepartment());
            List<ClockCard> cardList = getAllClockInformation();
            List<ClockCard> my = new ArrayList();
            for (ClockCard temp:cardList) {
                for (User userTemp:userList){
                    if (temp.getUserId() == userTemp.getUserId())
                        my.add(temp);
                }
            }
            List<ClockReturn> returnList = new ArrayList();
            for (ClockCard temp:my) {
                User user1 = new User();
                user1.setUserId(temp.getUserId());
                ClockReturn clockReturn = new ClockReturn(temp,new UserMapper().selectUserByNameOREmailORId(user1).get(0).getUserName());
                returnList.add(clockReturn) ;
            }
            return returnList;
        }catch (Exception e){
            throw new Exception("数据异常");
        }
    }
}
