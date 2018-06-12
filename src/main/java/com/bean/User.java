package com.bean;

/**
 * 用户表模型
 */
public class User {
    //用户id
    private int userId;

    //用户账号
    private String userName;

    //用户密码
    private String userPassWord;

    //用户性别，M-男，W-女
    private char userSex;

    //用户婚姻状态，0-未婚，1-已婚
    private int userMarriage;

    //用户电话
    private String userPhone;

    //用户邮箱
    private String userMail;

    //用户所在部门，审核员与管理员为null
    private String userDepartment;

    //用户状态，0-员工，1-管理员，2-审核员，默认为0
    private int userState;

    /**
     *user  属性get 和 set 方法
     */

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassWord() {
        return userPassWord;
    }

    public void setUserPassWord(String userPassWord) {
        this.userPassWord = userPassWord;
    }

    public char getUserSex() {
        return userSex;
    }

    public void setUserSex(char userSex) {
        this.userSex = userSex;
    }

    public int getUserMarriage() {
        return userMarriage;
    }

    public void setUserMarriage(int userMarriage) {
        this.userMarriage = userMarriage;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserDepartment() {
        return userDepartment;
    }

    public void setUserDepartment(String userDepartment) {
        this.userDepartment = userDepartment;
    }

    public int getUserState() {
        return userState;
    }

    public void setUserState(int userState) {
        this.userState = userState;
    }

    public User() {
    }

    public User(String userName, String userPassWord, char userSex, int userMarriage, String userPhone, String userMail, String userDepartment, int userState) {
        this.userName = userName;
        this.userPassWord = userPassWord;
        this.userSex = userSex;
        this.userMarriage = userMarriage;
        this.userPhone = userPhone;
        this.userMail = userMail;
        this.userDepartment = userDepartment;
        this.userState = userState;
    }

    public User(String userName, String userMail) {
        this.userName = userName;
        this.userMail = userMail;
    }

    /**
     * user toString()方法
     * @return
     */
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassWord='" + userPassWord + '\'' +
                ", userSex=" + userSex +
                ", userMarriage=" + userMarriage +
                ", userPhone='" + userPhone + '\'' +
                ", userMail='" + userMail + '\'' +
                ", userDepartment='" + userDepartment + '\'' +
                ", userState=" + userState +
                '}';
    }
}
