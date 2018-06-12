package com.JFrame;

import com.bean.User;
import com.mapper.UserMapper;
import com.utils.CheckNull;
import com.utils.Message;
import com.utils.Verify;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

/**
 * 改密窗口
 */
public class ChangePasswordPanel extends JFrame {
    private User user = null;
    private Box baseBox;
    public ChangePasswordPanel(final User user){
        super("考勤管理系统-找回密码-步骤3");
        this.user = user;
        baseBox = Box.createVerticalBox();
        baseBox.add(Box.createVerticalStrut(10));
        baseBox.add(new JLabel("请设置新密码"));
        baseBox.add(Box.createVerticalStrut(10));
        final JPasswordField password1 = new JPasswordField(16);
        baseBox.add(password1);
        baseBox.add(Box.createVerticalStrut(10));
        baseBox.add(new JLabel("再次输入新密码"));
        baseBox.add(Box.createVerticalStrut(10));
        final JPasswordField password2 = new JPasswordField(16);
        baseBox.add(password2);
        baseBox.add(Box.createVerticalStrut(15));
        JButton button = new JButton("确定");
        baseBox.add(button);
        setVisible(true);
        setLayout(new FlowLayout());
        add(baseBox);
        setBounds(500,500,350,250);
        //居中
        setLocationRelativeTo(null);
        //不可更改
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        new Message("验证成功，跳转到步骤3");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String p1 = new String(password1.getPassword());
                String p2 = new String(password2.getPassword());
                if (CheckNull.checkString(p1) || CheckNull.checkString(p2))
                    new Message("密码不能为空");
                else if (!p1.equals(p2))
                    new Message("两次输入的密码不一致");
                else {
                   user.setUserPassWord(p1);
                   if (CheckNull.checkObject(user,null) != null)
                       new Message("违规操作，不予更改");
                   else {
                       try {
                           if (new UserMapper().updateUserById(user) != 1)
                               new Message("系统错误，修改失败");
                           else {
                               new Message("修改成功");
                               dispose();
                           }
                       } catch (SQLException e1) {
                           e1.printStackTrace();
                       }
                   }
                }
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
        User user = new User();
        user.setUserPhone("13648404959");
        new ChangePasswordPanel(user);
    }
}
