package com.utils;

import com.bean.User;
import com.mapper.UserMapper;
import org.junit.Test;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证码生成工具
 */

public class Verify {

    /**
     * 生成指定位数验证码
     */
    public static String getVerify(int number){
        String temp = String.valueOf((int)(Math.random()*Math.pow(10,number)));
        return temp;
    }

    /**
     * 验证邮箱格式
     * @param email
     * @return
     */
    public static boolean verifyEmailFormat(String email){
        String regex = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * 验证电话格式
     * @param phone
     * @return
     */
    public static boolean verifyPhoneFormat(String phone){
        boolean flag = false;
        try{
            Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(17[0-9])|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
            Matcher matcher = regex.matcher(phone);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }

    public static boolean isValidDate(String str) {
        boolean convertSuccess=true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
            } catch (Exception e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess=false;
        }
        return convertSuccess;
    }

    /**
     * 验证user格式
     * @param user
     * @return
     */
    public static String vaildUser(User user){
        if (!verifyEmailFormat(user.getUserMail()))
            return "邮箱格式出错";
        if (!verifyPhoneFormat(user.getUserPhone()))
            return "电话格式有误";
        return null;
    }

    public static void main(String[] args) throws SQLException {
//        System.out.println(Verify.verifyPhoneFormat("13648404959"));
//        System.out.println(Verify.isValidDate("2017-2-29"));
        User user = new User();
        user.setUserId(2);
        user = new UserMapper().selectUserByNameOREmailORId(user).get(0);
        user.setUserPhone("13648404959");
        user.setUserMail("799957684@qq.com");
        System.out.println(Verify.vaildUser(user));
    }
}
