package com.JFrame;

import com.bean.User;
import com.utils.CheckNull;
import com.utils.Message;
import com.utils.Verify;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 验证电话窗口
 */
public class VerifyPhonePanel extends JFrame {
    private User user = null;
    private Box baseBox;
    public VerifyPhonePanel(final User user){
        super("考勤管理系统-找回密码-步骤2");
        this.user = user;
        baseBox = Box.createVerticalBox();
        baseBox.add(Box.createVerticalStrut(10));
        baseBox.add(new JLabel("请输入绑定的密保手机"));
        baseBox.add(Box.createVerticalStrut(10));
        baseBox.add(new JLabel(user.getUserPhone().substring(0,3) + "********"));
        baseBox.add(Box.createVerticalStrut(10));
        final JTextField phoneNumber = new JTextField(16);
        baseBox.add(phoneNumber);
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
        new Message("验证成功，跳转到步骤2");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phone = phoneNumber.getText();
                if (!Verify.verifyPhoneFormat(phone))
                    new Message("请输入正确的手机号码");
                else {
                    if (phone.equals(user.getUserPhone())){
                        new ChangePasswordPanel(user);
                        dispose();
                    }else {
                        new Message("手机号码不匹配");
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
        new VerifyPhonePanel(user);
    }
}
