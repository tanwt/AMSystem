package com.service;

import com.bean.ClockCard;
import com.bean.ClockReturn;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by tanwt
 * 2018/5/8 0008 18:34
 **/
@Service
public interface ClockCardService {

    /**
     * 添加上班打卡信息
     * @return
     */
    public int startWork(int userId) throws Exception;

    /**
     * 下班打卡信息
     * @return
     */
    public int endWork(int userId) throws Exception;

    /**
     * 得到所有打卡信息
     * @return
     */
    public List<ClockCard> getAllClockInformation() throws Exception;

    /**
     * 通过id得到打卡记录
     * @return
     */
    public List<ClockCard> getAllClockById(Integer userId) throws Exception;

    /**
     * 管理员
     * 通过id得到部门其他人打卡记录
     * @return
     */
    public List<ClockReturn> getOtherClockById(Integer userId) throws Exception;
}
