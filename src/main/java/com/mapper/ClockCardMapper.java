package com.mapper;

import com.bean.ClockCard;
import com.dao.ClockCardDao;
import com.utils.CheckNull;
import com.utils.DateConversion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClockCardMapper implements ClockCardDao {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;
    public ClockCardMapper() {
        // TODO Auto-generated constructor stub
        con = new DataBaseConnect().getConnection();
    }
    @Override
    public int startWork(int userId) throws SQLException {
        con = new DataBaseConnect().getConnection();
//        if (CheckNull.checkObject(clockCard,new String[]{"start_time","end_time"}) != null)
//            return 0;
        Date nowDate = new Date();
        String date = DateConversion.getYMD(nowDate);
        int status = 0;
        if (DateConversion.StrToDate(DateConversion.getYMD(new Date())+" 9:00:00").before(new Date()))
            status = 1;
        sql = "select * from am_clock_card where date=? AND user_id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,date);
            ps.setInt(2,userId);
            if (ps.executeQuery().next())
                return 0;
            sql = "insert into am_clock_card(user_id,date,start_time,status) values(?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1,userId);
            ps.setString(2,date);
            ps.setString(3,DateConversion.getHMS(nowDate));
            ps.setInt(4,status);
            int result = ps.executeUpdate();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (con != null)
                con.close();
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
        }
        return 0;
    }

    @Override
    public int endWork(int userId) throws SQLException {
        con = new DataBaseConnect().getConnection();
        Date nowDate = new Date();
        String date = DateConversion.getYMD(nowDate);
        sql = "select * from am_clock_card where date=? AND user_id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,date);
            ps.setInt(2,userId);
            rs = ps.executeQuery();
            if (rs.next()){
                String end_time = rs.getString(5);
                int status = rs.getInt(6);
                if (!CheckNull.checkString(end_time)) {
                    return 0;
                }
                else {

                    if (DateConversion.StrToDate(DateConversion.getYMD(new Date())+" 21:00:00").after(new Date())){
                        if (status == 0)
                            status =2;
                        if (status == 1)
                            status = 3;
                    }
                    sql = "UPDATE am_clock_card SET end_time=?,status=? WHERE user_id=? AND date=?";
                    ps = con.prepareStatement(sql);
                    ps.setString(1,DateConversion.getHMS(nowDate));
                    ps.setInt(2,status);
                    ps.setInt(3,userId);
                    ps.setString(4,date);
                    int result = ps.executeUpdate();
                    return result;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (con != null)
                con.close();
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
        }
        return 0;
    }

    @Override
    public List<ClockCard> getAllClockInformation() throws SQLException {
        con = new DataBaseConnect().getConnection();
        sql = "select * from am_clock_card ORDER BY id DESC ";
        List<ClockCard> allList = new ArrayList<ClockCard>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                ClockCard clockCard = new ClockCard();
                clockCard.setId(rs.getInt(1));
                clockCard.setUserId(rs.getInt(2));
                clockCard.setDate(rs.getString(3));
                clockCard.setStartTime(rs.getString(4));
                clockCard.setEndTime(rs.getString(5));
                clockCard.setStatus(rs.getInt(6));
                allList.add(clockCard);
            }
            return allList;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (con != null)
                con.close();
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
        }
        return null;
    }

}
