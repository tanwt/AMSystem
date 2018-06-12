package com.bean;

/**
 * create by tanwt
 * 2018/5/10 0010 21:09
 **/
public class LeaveReturn {

    private Leave leave;

    private String auditorName;

    private String userName;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LeaveReturn(Leave leave,  String userName,String auditorName) {
        this.leave = leave;
        this.auditorName = auditorName;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "LeaveReturn{" +
                "leave=" + leave +
                ", auditorName='" + auditorName + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    public Leave getLeave() {
        return leave;
    }

    public void setLeave(Leave leave) {
        this.leave = leave;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public String getUserName() {
        return userName;
    }
}
