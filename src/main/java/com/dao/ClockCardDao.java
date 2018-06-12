package com.dao;

import com.bean.ClockCard;

import java.sql.SQLException;
import java.util.List;

public interface ClockCardDao {

    /**
     * 添加上班打卡信息
     * @return
     */
    public int startWork(int userId) throws SQLException;

    /**
     * 下班打卡信息
     * @return
     */
    public int endWork(int userId) throws SQLException;

    /**
     * 得到所有打卡信息
     * @return
     */
    public List<ClockCard> getAllClockInformation() throws SQLException;

}
