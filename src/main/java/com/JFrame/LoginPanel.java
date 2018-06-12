package com.JFrame;

import com.bean.User;
import com.mapper.UserMapper;
import com.utils.CheckNull;
import com.utils.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class LoginPanel extends JFrame{
    private Box box1,box2,box3,box4,baseBox;
    private JTextField userLoginName;
    private JPasswordField userPassword;
    private JButton register,findPassword,login;
    public LoginPanel(){
        super("考勤管理-登录界面");

        //-------box1
        box1 = Box.createVerticalBox();
        box1.add(Box.createVerticalStrut(50));
        box1.add(new JLabel("用户:"));
        box1.add(Box.createVerticalStrut(20));
        box1.add(new JLabel("密码:"));
        box1.add(Box.createVerticalStrut(20));

        //-------box2
        box2 = Box.createVerticalBox();
        box2.add(Box.createVerticalStrut(50));
        userLoginName = new JTextField("用户名/手机/邮箱",16);
        box2.add(userLoginName);
        box2.add(Box.createVerticalStrut(20));
        userPassword = new JPasswordField(16);
        box2.add(userPassword);
        box2.add(Box.createVerticalStrut(20));

        //box3
        box3 = Box.createVerticalBox();
        box3.add(Box.createVerticalStrut(50));
        register = new JButton("注册账号");
        box3.add(register);
        register.setBorderPainted(false);
        register.setContentAreaFilled(false);
        register.setFocusPainted(false);
        box3.add(Box.createVerticalStrut(20));
        findPassword = new JButton("找回密码");
        findPassword.setBorderPainted(false);
        findPassword.setContentAreaFilled(false);
        findPassword.setFocusPainted(false);
        box3.add(findPassword);
        box3.add(Box.createVerticalStrut(20));

        //-------box4
        box4 = Box.createHorizontalBox();
        box4.add(box1);
        box4.add(Box.createHorizontalStrut(20));
        box4.add(box2);
        box4.add(box3);

        //-------baseBox
//        baseBox = Box.createVerticalBox();
//        baseBox.add(box4);
//        baseBox.add(Box.createVerticalStrut(50));
        Font f=new Font("",Font.BOLD,15);
        login = new JButton("登     录");
        login.setBounds(500,500,20,20);
        login.setFont(f);
        login.setFocusPainted(false);
        setVisible(true);
        setLayout(new FlowLayout());
        add(box4);
        add(login);
        setBounds(500,500,350,250);
        //居中
        setLocationRelativeTo(null);
        //不可更改
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        /**
         * 点击事件
         */
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new RegisterPanel();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        findPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ForgotPasswordPanel();
            }
        });

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String loginName = userLoginName.getText();
                String password = String.valueOf(userPassword.getPassword());
                if (loginName.equals("用户名/手机/邮箱"))
                    new Message("请输入账号后再登录");
                else if (CheckNull.checkString(password))
                    new Message("请您输入密码后在登陆");
                else {
                    User result = new User();
                    try {
                        result = new UserMapper().userLogin(loginName,password);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    if (result == null)
                        new Message("登录失败");
                    else {
                        if (result.getUserState() == 0){
                            try {
                                new EmployeePanel(result);
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                        }else if (result.getUserState() == 2){
                            try {
                                new AdminPanel(result);
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                        }else if (result.getUserState() == 1){
                            try {
                                new AdminPanel(result);
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                        }
                        dispose();
                    }
                }
            }
        });

        /**
         * 焦点事件处理
         */


        /**
         * 邮箱框对应焦点事件
         */
        userLoginName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (userLoginName.getText().equals("用户名/手机/邮箱"))
                    userLoginName.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (userLoginName.getText().equals("") || userLoginName.getText() == null)
                    userLoginName.setText("用户名/手机/邮箱");
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



    public static void main(String[] args) {
        new LoginPanel();
    }
}
