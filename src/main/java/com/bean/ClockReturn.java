package com.bean;

/**
 * create by tanwt
 * 2018/5/10 0010 21:08
 **/
public class ClockReturn {

    private ClockCard clockCard;

    private String userName;

    public ClockReturn(ClockCard clockCard, String userName) {
        this.clockCard = clockCard;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "ClockReturn{" +
                "clockCard=" + clockCard +
                ", userName='" + userName + '\'' +
                '}';
    }

    public ClockCard getClockCard() {
        return clockCard;
    }

    public void setClockCard(ClockCard clockCard) {
        this.clockCard = clockCard;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
