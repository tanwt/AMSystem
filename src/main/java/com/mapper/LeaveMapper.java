package com.mapper;

import com.bean.Leave;
import com.dao.LeaveDao;
import com.utils.CheckNull;
import com.utils.DateConversion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LeaveMapper implements LeaveDao {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;
    public LeaveMapper() {
        // TODO Auto-generated constructor stub
        con = new DataBaseConnect().getConnection();
    }
    @Override
    public int addLeave(Leave leave) throws SQLException {
        con = new DataBaseConnect().getConnection();
        sql = "insert into am_leave(user_id,date,start,end,site,type,reason,isExigency) values(?,?,?,?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,leave.getUserId());
            ps.setString(2,leave.getDate());
            ps.setString(3,leave.getStart());
            ps.setString(4,leave.getEnd());
            ps.setString(5,leave.getSite());
            ps.setInt(6,leave.getType());
            ps.setString(7,leave.getReason());
            ps.setInt(8,leave.getIsExigency());
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
    public List<Leave> getAllLeave() throws SQLException {
        con = new DataBaseConnect().getConnection();
        sql = "select * from am_leave order by id";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Leave> allLeave = new ArrayList<Leave>();
            while (rs.next()){
                Leave leave = new Leave();
                leave.setId(rs.getInt(1));
                leave.setUserId(rs.getInt(2));
                leave.setDate(rs.getString(3));
                leave.setStart(rs.getString(4));
                leave.setEnd(rs.getString(5));
                leave.setSite(rs.getString(6));
                leave.setType(rs.getInt(7));
//                if (!CheckNull.checkString(rs.getString(7)))
                    leave.setReason(rs.getString(8));
//                else
//                    leave.setReason("");
                leave.setIsExigency(rs.getInt(9));
//                if (rs.getInt(9) != 0){
                    leave.setAuditorId(rs.getInt(10));
//                }
//                if (!CheckNull.checkString(rs.getString(10)))
                    leave.setRejection(rs.getString(11));
//                else
//                    leave.setRejection("");
                leave.setStatus(rs.getInt(12));
                allLeave.add(leave);
            }
            return allLeave;
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

    @Override
    public int updateLeave(Leave leave) throws SQLException {
        con = new DataBaseConnect().getConnection();
        sql = "update am_leave " +
                "set user_id=?,date=?,start=?,end=?,site=?,type=?,reason=?,isExigency=?,auditor_id=?,rejection=?,status=?" +
                " where id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,leave.getUserId());
            ps.setString(2,leave.getDate());
            ps.setString(3,leave.getStart());
            ps.setString(4,leave.getEnd());
            ps.setString(5,leave.getSite());
            ps.setInt(6, leave.getType());
            ps.setString(7,leave.getReason());
            ps.setInt(8,leave.getIsExigency());
            ps.setInt(9,leave.getAuditorId());
            ps.setString(10, leave.getRejection());
            ps.setInt(11,leave.getStatus());
            ps.setInt(12,leave.getId());
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
    public int deleteLeave(int leaveId) throws SQLException {
        con = new DataBaseConnect().getConnection();
        sql = "delete from am_leave where id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,leaveId);
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
}
