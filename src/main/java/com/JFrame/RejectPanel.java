package com.JFrame;

import com.bean.Leave;
import com.mapper.LeaveMapper;
import com.utils.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class RejectPanel extends JFrame{
    JLabel label;
    JTextField jTextField;
    JButton button;
    Box baseBox,box1,box2;
    public RejectPanel(final Leave leave) {
        // TODO Auto-generated constructor stub
        super("消息提醒");
        setLayout(new FlowLayout());
        setSize(350	, 150);
        setLocationRelativeTo(null);
        label = new JLabel("请输入驳回理由:");
        jTextField = new JTextField(16);
        button = new JButton("确定");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                leave.setRejection(jTextField.getText());
                try {
                    if (new LeaveMapper().updateLeave(leave) != 0){
                        new Message("操作成功");
                        dispose();
                    }
                    else
                        new Message("系统错误");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        box1 = Box.createHorizontalBox();
        box1.add(label);
        box1.add(Box.createHorizontalStrut(20));
        box1.add(jTextField);
        box2 = Box.createHorizontalBox();
        box2.add(button);
        baseBox = Box.createVerticalBox();
        baseBox.add(Box.createVerticalStrut(25));
        baseBox.add(box1);
        baseBox.add(Box.createVerticalStrut(25));
        baseBox.add(box2);
        add(baseBox);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) throws SQLException {
        new RejectPanel(new LeaveMapper().getAllLeave().get(2));
    }
}
