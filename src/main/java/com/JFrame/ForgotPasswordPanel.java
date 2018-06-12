package com.JFrame;

import com.bean.User;
import com.dao.UserDao;
import com.mapper.DepartmentMapper;
import com.mapper.UserMapper;
import com.utils.CheckNull;
import com.utils.MailUtil;
import com.utils.Message;
import com.utils.Verify;
import sun.swing.UIAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

/**
 * 找回密码主窗口
 */
public class ForgotPasswordPanel extends JFrame{
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
    private User user = new User();
    private String verify;
    private boolean flag = false;
    public ForgotPasswordPanel(){
        super("考勤管理系统-找回密码-步骤1");

        //创建列式盒子box2布局--->输入框
        box2 = Box.createVerticalBox();
        //垂直支撑间距
        int height2 = 8;

        box2.add(new JLabel("邮箱:"));
        box2.add(Box.createVerticalStrut(height2));
        mailText = new JTextField("---请输入您的邮箱---",16);
        box2.add(mailText);
        box2.add(Box.createVerticalStrut(height2));

        box2.add(new JLabel("验证码:"));
        box2.add(Box.createVerticalStrut(height2));
        verifyText = new JTextField("---点击获取验证码---",16);
        box2.add(verifyText);
        box2.add(Box.createVerticalStrut(height2));


        //创建行式盒子box4布局--->添加按钮组件
        box4 = Box.createHorizontalBox();
        verifyButton = new JButton("获取验证码");
        box4.add(verifyButton);
        box4.add(Box.createHorizontalStrut(10));
        registerButton = new JButton("验证");
        box4.add(registerButton);

        //创建列式盒子baseBox布局--->添加合并布局box3,box4
        baseBox = Box.createVerticalBox();
        baseBox.add(box2);
        baseBox.add(Box.createVerticalStrut(30));
        baseBox.add(box4);
        setVisible(true);
        setLayout(new FlowLayout());
        add(baseBox);
        setBounds(500,500,350,250);
        //居中
        setLocationRelativeTo(null);
        //不可更改
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        /**
         * 按钮事件处理
         */

        /**
         * 获取验证码按钮
         */
        verifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取组件数据，判读各种不合格的情况
                String mail = mailText.getText();
                if (CheckNull.checkString(mail) || mail.equals("---请输入您的邮箱---"))
                    new Message("邮件不能为空");
                else if (!Verify.verifyEmailFormat(mail)){
                    new Message("邮件格式有误");
                }else
                //没问题就发送邮箱
                {
                    user.setUserMail(mail);
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
         * 验证按钮事件
         */
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!flag){
                    new Message("请获取验证码");
                }else if (user.getUserMail() == null || CheckNull.checkString(verify)){
                    new Message("请正确填写信息");
                }
                else{
                    if (!verifyText.getText().equals(verify)){
                        new Message("验证码不匹配");
                        verifyText.setText("---点击获取验证码---");
                        flag = false;
                    }
                    else {
                        System.out.println(user);
                        List<User> userIsExist = null;
                        try {
                            userIsExist = userDao.selectUserByNameOREmailORId(user);
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        System.out.println(userIsExist);
                        if (!userIsExist.isEmpty()){
                            new VerifyPhonePanel(userIsExist.get(0));
                            dispose();

                        }else {
                            new Message("该邮箱未注册");
                            verifyText.setText("---点击获取验证码---");
                            flag = false;
                        }
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
        mailText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (mailText.getText().equals("---请输入您的邮箱---"))
                    mailText.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (mailText.getText().equals("") || mailText.getText() == null)
                    mailText.setText("---请输入您的邮箱---");
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
       /* addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                dispose();
            }
        });*/
    }




    public static void main(String[] args) {
        new ForgotPasswordPanel();
    }
}
