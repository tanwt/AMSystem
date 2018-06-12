package com.JFrame;

import com.bean.Leave;
import com.bean.User;
import com.mapper.LeaveMapper;
import com.mapper.UserMapper;
import com.utils.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class LeaveShowPanel extends JFrame implements ActionListener {
    private JTextField enter;
    Object a[][] ;
    private JTable table;
    private JPanel pSouth,pNorth;
    private JButton flush;
    private User user;
    public LeaveShowPanel(User user) throws SQLException {
        super("申请进度查询");
        this.user = user;
        init();
        enter.addActionListener(this);
        flush.addActionListener(this);
        add(pNorth, BorderLayout.NORTH);
        add(new JScrollPane(table),BorderLayout.SOUTH);
        setBounds(100,100,600,500);
        //居中
        setLocationRelativeTo(null);
        //不可更改
        setResizable(false);
        setVisible(true);
        validate();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    /**
     * 管理者模式
     * @param user
     */
    public LeaveShowPanel(final User user, int userStatus) throws SQLException {
        super("申请进度查询");
        this.user = user;
        init1();
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = 0;
                try {
                    id = Integer.parseInt(enter.getText());
                }catch (Exception e1){
                    new Message("请输入正确的申请号");
                }
                List<Leave> leaves = null;
                try {
                    leaves = new LeaveMapper().getAllLeave();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                Leave leave = null;
                for (Leave temp:leaves){
                    if (id == temp.getId())
                        leave = temp;
                }
                if (leave == null)
                    new Message("无相关信息");
                else {
                    try {
                        new LeavePanel(leave,user);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        flush.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    update2();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        add(pNorth, BorderLayout.NORTH);
        add(new JScrollPane(table),BorderLayout.SOUTH);
        setBounds(100,100,600,500);
        //居中
        setLocationRelativeTo(null);
        //不可更改
        setResizable(false);
        setVisible(true);
        validate();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public void init() throws SQLException {
        Object name[] = {"申请号","申请时间","申请理由","类型","申请状态"};
        pNorth = new JPanel();
        flush = new JButton("刷新");
        pNorth.add(new JLabel("输入出勤号，回车查看详情: "));
        enter = new JTextField(16);
        pNorth.add(enter);
        pNorth.add(flush);
        List<Leave> leaves = new LeaveMapper().getAllLeave();
        List<Leave> userLeave = new LinkedList<Leave>();
        for (Leave leave:leaves
             ) {
            if (leave.getUserId() == user.getUserId())
                userLeave.add(leave);
        }
        int count = 0;
        a = new Object[userLeave.size()+12][5];
        count = userLeave.size();
        table = new JTable(a,name);
        table.setRowHeight(20);
        for (int i=0 ; i < count; i++){
            Leave temp = userLeave.get(i);
            table.setValueAt(temp.getId(),i,0);
            table.setValueAt(temp.getDate(),i,1);
            table.setValueAt(temp.getReason(),i,2);
            if (temp.getType() == 0) {
                table.setValueAt("病假",i,3);
            }
            else if(temp.getType() == 1) {
                table.setValueAt("事假",i,3);
            }
            else if(temp.getType() == 2) {
                table.setValueAt("出差",i,3);
            }
            if (temp.getStatus() == 0)
                table.setValueAt("未处理",i,4);
            if (temp.getStatus() == 1)
                table.setValueAt("通过",i,4);
            if (temp.getStatus() == 2)
                table.setValueAt("驳回",i,4);

        }

    }
    public void init1() throws SQLException {
        Object name[] = {"申请号","申请人","申请时间","申请理由","类型","申请状态"};
        pNorth = new JPanel();
        flush = new JButton("刷新");
        pNorth.add(new JLabel("输入出勤号，回车查看详情: "));
        enter = new JTextField(16);
        pNorth.add(enter);
        pNorth.add(flush);
        List<Leave> leaves = new LeaveMapper().getAllLeave();
        List<Leave> userLeave = new LinkedList<Leave>();
        List<User> users = new UserMapper().getUserIDByDepartment(user.getUserDepartment());
        for (Leave leave:leaves
                ) {
            for (User temp:users
                 ) {
                if (temp.getUserId() != user.getUserId () && leave.getUserId() == temp.getUserId()){
                        userLeave.add(leave);
                }
            }
        }
        int count = 0;
        a = new Object[userLeave.size()+12][6];
        count = userLeave.size();
        table = new JTable(a,name);
        table.setRowHeight(20);
        for (int i=0 ; i < count; i++){
            Leave temp = userLeave.get(i);
            User userTemp = new User();
            userTemp.setUserId(temp.getUserId());
            table.setValueAt(temp.getId(),i,0);
            table.setValueAt(new UserMapper().selectUserByNameOREmailORId(userTemp).get(0).getUserName(),i,1);
            table.setValueAt(temp.getDate(),i,2);
            table.setValueAt(temp.getReason(),i,3);
            if (temp.getType() == 0) {
                table.setValueAt("病假",i,4);
            }
            else if(temp.getType() == 1) {
                table.setValueAt("事假",i,4);
            }
            else if(temp.getType() == 2) {
                table.setValueAt("出差",i,4);
            }
            if (temp.getStatus() == 0)
                table.setValueAt("未处理",i,5);
            if (temp.getStatus() == 1)
                table.setValueAt("通过",i,5);
            if (temp.getStatus() == 2)
                table.setValueAt("驳回",i,5);

        }

    }

    public void update() throws SQLException {
        List<Leave> leaves = new LeaveMapper().getAllLeave();
        List<Leave> userLeave = new LinkedList<Leave>();
        for (Leave leave:leaves
                ) {
            if (leave.getUserId() == user.getUserId()){
                userLeave.add(leave);
            }
        }
        int count = 0, all = 0;
        count = userLeave.size();
        all = count + 12;
        System.out.println(count);
        for (int i=0 ; i < all; i++){
            if (i<count){
                Leave temp = userLeave.get(i);
                table.setValueAt(temp.getId(),i,0);
                table.setValueAt(temp.getDate(),i,1);
                table.setValueAt(temp.getReason(),i,2);
                if (temp.getType() == 0) {
                    table.setValueAt("病假",i,3);
                }
                else if(temp.getType() == 1) {
                    table.setValueAt("事假",i,3);
                }
                else if(temp.getType() == 2) {
                    table.setValueAt("出差",i,3);
                }
                if (temp.getStatus() == 0)
                    table.setValueAt("未处理",i,4);
                if (temp.getStatus() == 1)
                    table.setValueAt("通过",i,4);
                if (temp.getStatus() == 2)
                    table.setValueAt("驳回",i,4);
            }else{
                table.setValueAt("",i,0);
                table.setValueAt("",i,1);
                table.setValueAt("",i,2);
                table.setValueAt("",i,3);
                table.setValueAt("",i,4);
            }
        }
    }
    public void update2() throws SQLException {
        List<Leave> leaves = new LeaveMapper().getAllLeave();
        List<Leave> userLeave = new LinkedList<Leave>();
        List<User> users = new UserMapper().getUserIDByDepartment(user.getUserDepartment());
        for (Leave leave:leaves
                ) {
            for (User temp:users
                    ) {
                if (temp.getUserId() != user.getUserId () && leave.getUserId() == temp.getUserId()){
                    userLeave.add(leave);
                }
            }
        }
        int count = 0, all = 0;
        count = userLeave.size();
        all = count + 12;
        System.out.println(count);
        for (int i=0 ; i < all; i++){
            if (i<count){
                Leave temp = userLeave.get(i);
                User userTemp = new User();
                userTemp.setUserId(temp.getUserId());
                table.setValueAt(temp.getId(),i,0);
                table.setValueAt(new UserMapper().selectUserByNameOREmailORId(userTemp).get(0).getUserName(),i,1);
                table.setValueAt(temp.getDate(),i,2);
                table.setValueAt(temp.getReason(),i,3);
                if (temp.getType() == 0) {
                    table.setValueAt("病假",i,4);
                }
                else if(temp.getType() == 1) {
                    table.setValueAt("事假",i,4);
                }
                else if(temp.getType() == 2) {
                    table.setValueAt("出差",i,4);
                }
                if (temp.getStatus() == 0)
                    table.setValueAt("未处理",i,5);
                if (temp.getStatus() == 1)
                    table.setValueAt("通过",i,5);
                if (temp.getStatus() == 2)
                    table.setValueAt("驳回",i,5);
            }else{
                table.setValueAt("",i,0);
                table.setValueAt("",i,1);
                table.setValueAt("",i,2);
                table.setValueAt("",i,3);
                table.setValueAt("",i,4);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enter){
            int id = 0;
            try {
                id = Integer.parseInt(enter.getText());
            }catch (Exception e1){
                new Message("请输入正确的申请号");
            }
            List<Leave> leaves = null;
            try {
                leaves = new LeaveMapper().getAllLeave();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            Leave leave = null;
            for (Leave temp:leaves){
                if (id == temp.getId())
                    leave = temp;
            }
            if (leave == null)
                new Message("无相关信息");
            else {
                try {
                    new LeavePanel(leave,user);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
        if (e.getSource() == flush){
            try {
                update();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        User user = new User();
        user.setUserId(2);
        new LeaveShowPanel(new UserMapper().selectUserByNameOREmailORId(user).get(0),1);
    }
}
