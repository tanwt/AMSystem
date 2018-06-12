package com.JFrame;

import com.bean.User;
import com.dao.UserDao;
import com.mapper.DepartmentMapper;
import com.mapper.UserMapper;
import com.utils.CheckNull;
import com.utils.MailUtil;
import com.utils.Message;
import com.utils.Verify;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.util.List;

public class ModifyPersonalInformationPanel  extends JFrame{

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
    private User user = null;
    private boolean flag = false;
    public ModifyPersonalInformationPanel(final User inputUser) throws SQLException {
        super("修改信息");
        user = inputUser;
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

        //创建列式盒子box2布局--->输入框
        box2 = Box.createVerticalBox();
        nameText = new JTextField(inputUser.getUserName(), 16);
        box2.add(nameText);
        //垂直支撑间距
        int height2 = 8;
        box2.add(Box.createVerticalStrut(0));
        //----添加性别单选框
        JPanel sex = new JPanel();
        jRadioButton1 = new JRadioButton("男");
        jRadioButton2 = new JRadioButton("女");
        if (inputUser.getUserSex() == 'M')
            jRadioButton1.setSelected(true);
        else
            jRadioButton2.setSelected(true);
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
        jRadioButton3 = new JRadioButton("未婚");
        jRadioButton4 = new JRadioButton("已婚");
        if (inputUser.getUserMarriage() == 0)
            jRadioButton3.setSelected(true);
        else
            jRadioButton4.setSelected(true);
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
        jComboBox.setSelectedItem(inputUser.getUserDepartment());
        box2.add(jComboBox);
        box2.add(Box.createVerticalStrut(height2));
        //添加结束

        phoneText = new JTextField(inputUser.getUserPhone(), 16);
        box2.add(phoneText);
        box2.add(Box.createVerticalStrut(height2));
        mailText = new JTextField(inputUser.getUserMail(),16);
        box2.add(mailText);
        box2.add(Box.createVerticalStrut(height2));

        //创建行式盒子box3布局--->合并box1 box2
        box3 = Box.createHorizontalBox();
        box3.add(box1);
        box3.add(Box.createVerticalStrut(10));
        box3.add(box2);

        //创建行式盒子box4布局--->添加按钮组件
        box4 = Box.createHorizontalBox();
        verifyButton = new JButton("修改密码");
        box4.add(verifyButton);
        box4.add(Box.createHorizontalStrut(10));
        registerButton = new JButton("完成");
        box4.add(registerButton);

        //创建列式盒子baseBox布局--->添加合并布局box3,box4
        baseBox = Box.createVerticalBox();
        baseBox.add(box3);
        baseBox.add(Box.createVerticalStrut(10));
        baseBox.add(box4);
        setVisible(true);
        setLayout(new FlowLayout());
        add(baseBox);
        setBounds(500,500,300,300);
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
                new ForgotPasswordPanel();
            }
        });

        /**
         * 注册按钮事件
         */
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User temp = new User();
                temp.setUserId(user.getUserId());
                user = addData(user);
                if (CheckNull.checkObject(user,null) != null ){
                    new Message("请正确填写信息");
                    try {
                        user = userDao.selectUserByNameOREmailORId(temp).get(0);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                }
                else{
                    List<User> userIsExist = null;
                    try {
                        userIsExist = userDao.selectUserByNameOREmailORId(user);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    if (userIsExist.size() > 1){
                        new Message("该用户名,手机号或邮箱已存在");
                        try {
                            user = userDao.selectUserByNameOREmailORId(temp).get(0);
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }else {
                        try {
                            if (userDao.updateUserById(user) == 1){
                                new Message("修改成功");
                                dispose();
                            }
                            else{
                                new Message("系统错误");
                            }
                        } catch (SQLException e1) {
                            e1.printStackTrace();
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


    }

    /**
     * 获取组件数据
     * @return
     */
    public  User addData(User user){
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

        return user;
    }


    public static void main(String[] args) throws SQLException {
        User user = new User();
        user.setUserId(4);
        new ModifyPersonalInformationPanel(new UserMapper().selectUserByNameOREmailORId(user).get(0));
    }
}
