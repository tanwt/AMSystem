package com.bean;

public class Leave {

    private int id;

    private int userId;

    private String date;

    private String start;

    private String end;

    private String site;

    private int type;

    private String reason;

    private int isExigency;

    private int auditorId;

    private String rejection;

    private int status;


    public Leave() {
    }

    @Override
    public String toString() {
        return "Leave{" +
                "id=" + id +
                ", userId=" + userId +
                ", date='" + date + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", site='" + site + '\'' +
                ", type=" + type +
                ", reason='" + reason + '\'' +
                ", isExigency=" + isExigency +
                ", auditorId=" + auditorId +
                ", rejection='" + rejection + '\'' +
                ", status=" + status +
                '}';
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getIsExigency() {
        return isExigency;
    }

    public void setIsExigency(int isExigency) {
        this.isExigency = isExigency;
    }

    public int getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(int auditorId) {
        this.auditorId = auditorId;
    }

    public String getRejection() {
        return rejection;
    }

    public void setRejection(String rejection) {
        this.rejection = rejection;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
