package com.JFrame;

import com.bean.User;
import com.dao.UserDao;
import com.mapper.DepartmentMapper;
import com.mapper.UserMapper;
//import com.service.Impl.UserServiceImpl;
import com.service.UserService;
import com.sun.xml.internal.bind.v2.model.core.ID;
import com.utils.CheckNull;
import com.utils.MailUtil;
import com.utils.Message;
import com.utils.Verify;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.awt.Component.LEFT_ALIGNMENT;

public class RegisterPanel extends JFrame{

    //用户处理接口对象
    UserDao userDao = new UserMapper();

    //各种布局
    private Box box1,box2,box3,box4,baseBox;
    //user对应的组件
    private JTextField nameText,mailText,verifyText,phoneText;
    private JPasswordField passwordText,surePasswordText;
    private JRadioButton jRadioButton1,jRadioButton2,jRadioButton3,jRadioButton4;
    private JComboBox jComboBox;
    private JButton verifyButton,registerButton;
    private  User user = null;
    private String verify;
    private boolean flag = false;
    public RegisterPanel() throws SQLException {
        super("考勤管理系统-注册");

        //创建列式盒子box1布局--->输入框文字
        box1 = Box.createVerticalBox();
        //垂直支撑间距
        int height1 = 16;
        box1.add(new JLabel("姓名:"));
        box1.add(Box.createVerticalStrut(height1));
        box1.add(new JLabel("性别:"));
        box1.add(Box.createVerticalStrut(height1));
        box1.add(new JLabel("婚姻:"));
        box1.add(Box.createVerticalStrut(height1));
        box1.add(new JLabel("部门:"));
        box1.add(Box.createVerticalStrut(height1));
        box1.add(new JLabel("电话:"));
        box1.add(Box.createVerticalStrut(height1));
        box1.add(new JLabel("邮箱:"));
        box1.add(Box.createVerticalStrut(height1));
        box1.add(new JLabel("密码:"));
        box1.add(Box.createVerticalStrut(height1));
        box1.add(new JLabel("确认密码:"));
        box1.add(Box.createVerticalStrut(height1));
        box1.add(new JLabel("验证码:"));
        box1.add(Box.createVerticalStrut(height1));

        //创建列式盒子box2布局--->输入框
        box2 = Box.createVerticalBox();
        nameText = new JTextField("---请输入您的姓名---", 16);
        box2.add(nameText);
        //垂直支撑间距
        int height2 = 8;
        box2.add(Box.createVerticalStrut(0));
        //----添加性别单选框
        JPanel sex = new JPanel();
        jRadioButton1 = new JRadioButton("男",true);
        jRadioButton2 = new JRadioButton("女");
        ButtonGroup b = new ButtonGroup();
        b.add(jRadioButton1);
        b.add(jRadioButton2);
        sex.add(jRadioButton1);
        sex.add(jRadioButton2);
        box2.add(sex);
        box2.add(Box.createVerticalStrut(0));
        //----添加结束

        //添加婚姻单选框
        JPanel marriage = new JPanel();
        jRadioButton3 = new JRadioButton("未婚",true);
        jRadioButton4 = new JRadioButton("已婚");
        ButtonGroup g = new ButtonGroup();
        g.add(jRadioButton3);
        g.add(jRadioButton4);
        marriage.add(jRadioButton3);
        marriage.add(jRadioButton4);
        box2.add(marriage);
        box2.add(Box.createVerticalStrut(0));
        //添加结束

        //添加部门列表框
        DepartmentMapper departmentMapper = new DepartmentMapper();
        List<String> departmentList = departmentMapper.getAllDepartment();
        jComboBox = new JComboBox();
        for(String department: departmentList){
            jComboBox.addItem(department);
        }
        box2.add(jComboBox);
        box2.add(Box.createVerticalStrut(height2));
        //添加结束

        phoneText = new JTextField("---请输入您的电话号码---", 16);
        box2.add(phoneText);
        box2.add(Box.createVerticalStrut(height2));
        mailText = new JTextField("---一名用户限绑一个邮箱---",16);
        box2.add(mailText);
        box2.add(Box.createVerticalStrut(height2));
        passwordText = new JPasswordField(16);
        box2.add(passwordText);
        box2.add(Box.createVerticalStrut(height2));
        surePasswordText = new JPasswordField(16);
        box2.add(surePasswordText);
        box2.add(Box.createVerticalStrut(height2));
        verifyText = new JTextField("---点击获取验证码---",16);
        box2.add(verifyText);
        box2.add(Box.createVerticalStrut(height2));

        //创建行式盒子box3布局--->合并box1 box2
        box3 = Box.createHorizontalBox();
        box3.add(box1);
        box3.add(Box.createVerticalStrut(10));
        box3.add(box2);

        //创建行式盒子box4布局--->添加按钮组件
        box4 = Box.createHorizontalBox();
        verifyButton = new JButton("获取验证码");
        box4.add(verifyButton);
        box4.add(Box.createHorizontalStrut(10));
        registerButton = new JButton("注册");
        box4.add(registerButton);

        //创建列式盒子baseBox布局--->添加合并布局box3,box4
        baseBox = Box.createVerticalBox();
        baseBox.add(box3);
        baseBox.add(Box.createVerticalStrut(10));
        baseBox.add(box4);
        setVisible(true);
        setLayout(new FlowLayout());
        add(baseBox);
        setBounds(500,500,300,400);
        //居中
        setLocationRelativeTo(null);
        //不可更改
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        /**
         * 按钮事件处理
         */

        /**
         * 验证按钮
         */
        verifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取组件数据，判读各种不合格的情况
                user = addData();
                List<String> list = CheckNull.checkObject(user,null);
                if (list != null){
                    if (list.get(0).equals("userName")) {
                        new Message("用户名为空");
                    }
                    if (list.get(0).equals("userPhone")) {
                        new Message( "电话输入有误");
                    }
                    if (list.get(0).equals("userMail")) {
                        new Message( "邮箱格式有误");
                    }
                    if (list.get(0).equals("userPassWord")) {
                        new Message( "密码设置失败");
                    }
                }else
                    //没问题就发送邮箱
                    {
                        //获得验证码
                        verify = Verify.getVerify(5);
//                        if (new MailUtil(user.getUserMail(),verify).sendMail().equals("1"))
                        new Thread(new MailUtil(user.getUserMail(),verify)).start();
                        new Message("验证码已发送,请注意查收");
                        flag = true;
                }
            }
        });

        /**
         * 注册按钮事件
         */
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CheckNull.checkObject(user,null) != null || CheckNull.checkString(verify)){
                    new Message("请正确填写信息");
                }else if(!flag){
                    new Message("请获取验证码");
                }
                else{
                    if (!verifyText.getText().equals(verify)){
                        new Message("验证码不匹配");
                        verifyText.setText("---点击获取验证码---");
                        flag = false;
                    }
                    else {
//                        System.out.println(user);
//                        System.out.println(userService);
//                        System.out.println(userService.userIsExist(user));
                        List<User> userIsExist = null;
                        try {
                            System.out.println(user);
                            userIsExist = userDao.selectUserByNameOREmailORId(user);
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        System.out.println(userIsExist);
                        if (!userIsExist.isEmpty()){
                            new Message("该用户名,手机号或邮箱已注册");
                            verifyText.setText("---点击获取验证码---");
                            flag = false;
                        }else {
                            int temp = 0;
                            try {
                                temp = userDao.userRegister(user);
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                            if (temp == 1) {
                                new Message("注册成功");
                                dispose();
                            }
                            else{
                                new Message("系统异常，无法注册");
                                verifyText.setText("---点击获取验证码---");
                                flag = false;
                            }
                        }
                    }
                }
            }
        });


        /**
         * 焦点事件处理
         */

        /**
         * 姓名框对应焦点事件
         */
        nameText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (nameText.getText().equals("---请输入您的姓名---"))
                    nameText.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nameText.getText().equals("") || nameText.getText() == null)
                    nameText.setText("---请输入您的姓名---");
            }
        });

        /**
         * 电话框对应焦点事件
         */
        phoneText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (phoneText.getText().equals("---请输入您的电话号码---"))
                    phoneText.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (phoneText.getText().equals("") || phoneText.getText() == null)
                    phoneText.setText("---请输入您的电话号码---");
            }
        });

        /**
         * 邮箱框对应焦点事件
         */
        mailText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (mailText.getText().equals("---一名用户限绑一个邮箱---"))
                    mailText.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (mailText.getText().equals("") || mailText.getText() == null)
                    mailText.setText("---一名用户限绑一个邮箱---");
            }
        });
        /**
         * 验证框对应焦点事件
         */
        verifyText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (verifyText.getText().equals("---点击获取验证码---"))
                    verifyText.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (verifyText.getText().equals("") || verifyText.getText() == null)
                    verifyText.setText("---点击获取验证码---");
            }
        });

        /**
         * 窗体事件
         */
        /*addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                dispose();
            }
        });*/
    }

    /**
     * 获取组件数据
     * @return
     */
    public  User addData(){
        User user = new User();
        if (nameText.getText().equals("---请输入您的姓名---"))
            user.setUserName("");
        else
            user.setUserName(nameText.getText());

        if (mailText.getText().equals("---一名用户限绑一个邮箱---") || !Verify.verifyEmailFormat(mailText.getText())) {
            user.setUserMail("");
        }
        else
            user.setUserMail(mailText.getText());

        if (phoneText.getText().equals("---请输入您的电话号码---") || !Verify.verifyPhoneFormat(phoneText.getText())) {
            user.setUserPhone("");
        }
        else
            user.setUserPhone(phoneText.getText());

        if (jRadioButton1.isSelected())
            user.setUserSex('M');
        else
            user.setUserSex('W');

        if (jRadioButton3.isSelected())
            user.setUserMarriage(0);
        else
            user.setUserMarriage(1);

        user.setUserDepartment((String) jComboBox.getSelectedItem());

        String password1 = new String(passwordText.getPassword());
        String password2 = new String(surePasswordText.getPassword());
        if (CheckNull.checkString(password1) || !password1.equals(password2))
            user.setUserPassWord("");
        else
            user.setUserPassWord(password1);
        return user;
    }


    public static void main(String[] args) throws SQLException {
        new RegisterPanel();
    }
}
