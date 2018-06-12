/*
package com.test;

public class Text {
    public static void main(String[] args) {
        User user1 = new User();
        user1.setId(1);
        User user2 = user1;
        user2.setId(2);
        System.out.println(user1.getId());
        User user3 = new User();
        user3 = user1;
        user3.setId(3);
        System.out.println(user1.getId());

    }
}
class User{
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                '}';
    }
}*/
/*package com.JFrame;

import com.bean.Leave;
import com.bean.User;
import com.mapper.LeaveMapper;
import com.mapper.UserMapper;
import com.utils.CheckNull;
import com.utils.DateConversion;
import com.utils.Message;
import com.utils.Verify;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Date;
import java.util.List;

import static javax.swing.BoxLayout.*;

public class LeavePanel extends JFrame{
    private JTextField start,end,site;
    private JRadioButton jRadioButton1,jRadioButton2,jRadioButton3;
    private JTextArea reason;
    private JButton button;
    private Box box1,box2,baseBox;
    private JPanel pSouth,pNorth;
    private Leave leave;
    private User initUser;
    private int leaveType;
    public LeavePanel(User user,int leaveType){
        super("申请出勤");
        if (leaveType == 1)
            setTitle("申请出差");
        this.leaveType = leaveType;
        init();
        initUser = user;
        setBounds(100,100,500,450);
        //居中
        setLocationRelativeTo(null);
        //不可更改
        setResizable(false);
        setVisible(true);
        validate();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    public void init(){

        //-----box1
        box1 = Box.createVerticalBox();
        //垂直支撑间距
        int height1 = 16;
        if (leaveType == 1)
            box1.add(new JLabel("出差时间:"));
        else
            box1.add(new JLabel("出勤时间:"));
        box1.add(Box.createVerticalStrut(80));
        if (leaveType == 1)
            box1.add(new JLabel("出差地点:"));
        else
            box1.add(new JLabel("出勤类型:"));
        box1.add(Box.createVerticalStrut(20));
        if (leaveType == 1)
            box1.add(new JLabel("出差原因:"));
        else
            box1.add(new JLabel("出勤原因:"));
        box1.add(Box.createVerticalStrut(200));

        //-----box2
        box2 = Box.createVerticalBox();
        start = new JTextField(DateConversion.getYMD(new Date()),16);
        box2.add(start);
        box2.add(Box.createVerticalStrut(height1));
        end = new JTextField("日期格式：yyyy-MM-dd",16);
        box2.add(end);
        box2.add(Box.createVerticalStrut(height1));
        if (leaveType == 1){
            site = new JTextField(16);
            box2.add(site);
        }
        else{
            JPanel type = new JPanel();
            jRadioButton1 = new JRadioButton("事假",true);
            jRadioButton2 = new JRadioButton("病假");
            ButtonGroup b = new ButtonGroup();
            b.add(jRadioButton1);
            b.add(jRadioButton2);
            type.add(jRadioButton1);
            type.add(jRadioButton2);
            box2.add(type);
        }
        box2.add(Box.createVerticalStrut(height1));
        JScrollPane jp = new JScrollPane();
        reason = new JTextArea("限100字",10,25);
        reason.setLineWrap(true);
        jp.setViewportView(reason);
        box2.add(jp);
        box2.add(Box.createVerticalStrut(height1));
        jRadioButton3 = new JRadioButton("紧急");
        box2.add(jRadioButton3);

        //-------basebox
        baseBox = Box.createHorizontalBox();
        baseBox.add(box1);
        baseBox.add(Box.createHorizontalStrut(30));
        baseBox.add(box2);

        pNorth = new JPanel();
        pNorth.add(baseBox);

        pSouth = new JPanel();
        button = new JButton("提交");
        pSouth.add(button);


    }

    public void addData(){
        leave = new Leave();
        leave.setUserId(initUser.getUserId());
        leave.setDate(DateConversion.getYMD(new Date()));
        if (!CheckNull.checkString(start.getText()) && !start.getText().equals("日期格式：yyyy-MM-dd"))
            leave.setStart(start.getText());
        if (!CheckNull.checkString(end.getText()) && !end.getText().equals("日期格式：yyyy-MM-dd")) {
            leave.setEnd(end.getText());
        }
        if (!CheckNull.checkString(reason.getText()) && !reason.getText().equals("限100字"))
            leave.setReason(reason.getText());
        if (leaveType != 1){
            if (jRadioButton1.isSelected())
                leave.setType(0);
            else
                leave.setType(1);
        }else {
            if (!CheckNull.checkString(site.getText())){
                leave.setSite(site.getText());
            }
        }
        if (leaveType == 1){
            if (!CheckNull.checkString(site.getText()))
                leave.setSite(site.getText());
        }
        if (jRadioButton3.isSelected())
            leave.setIsExigency(1);
        else
            leave.setIsExigency(0);
    }

    public LeavePanel(Leave leave){
        super("申请详情");

        if (leave.getType() == 2)
            leaveType = 1;
        else
            leaveType = 0;
        //-----box1
        box1 = Box.createVerticalBox();
        //垂直支撑间距
        int height1 = 16;
        box1.add(new JLabel("申请号："));
        box1.add(Box.createVerticalStrut(10));
        if (leaveType == 1) {
            box1.add(new JLabel("出差时间:"));
        }
        else {
            box1.add(new JLabel("出勤时间:"));
        }
        box1.add(Box.createVerticalStrut(80));
        if (leaveType == 1)
            box1.add(new JLabel("出差地点:"));
        else
            box1.add(new JLabel("出勤类型:"));
        box1.add(Box.createVerticalStrut(20));
        if (leaveType == 1)
            box1.add(new JLabel("出差原因:"));
        else
            box1.add(new JLabel("出勤原因:"));
        box1.add(Box.createVerticalStrut(200));

        //-----box2
        box2 = Box.createVerticalBox();
        box2.add(new JLabel(String.valueOf(leave.getId())));
        box2.add(Box.createVerticalStrut(height1));
        start = new JTextField(leave.getStart(),16);
        box2.add(start);
        box2.add(Box.createVerticalStrut(height1));
        end = new JTextField(leave.getEnd(),16);
        box2.add(end);
        box2.add(Box.createVerticalStrut(height1));
        if (leaveType == 1){
            site = new JTextField(leave.getSite(),16);
            box2.add(site);
        }
        else{
            JPanel type = new JPanel();
            jRadioButton1 = new JRadioButton("事假");
            jRadioButton2 = new JRadioButton("病假");
            if (leave.getType() == 0)
                jRadioButton1.setSelected(true);
            else
                jRadioButton2.setSelected(true);
            ButtonGroup b = new ButtonGroup();
            b.add(jRadioButton1);
            b.add(jRadioButton2);
            type.add(jRadioButton1);
            type.add(jRadioButton2);
            box2.add(type);
        }
        box2.add(Box.createVerticalStrut(height1));
        JScrollPane jp = new JScrollPane();
        reason = new JTextArea(leave.getReason(),10,25);
        reason.setLineWrap(true);
        jp.setViewportView(reason);
        box2.add(jp);
        box2.add(Box.createVerticalStrut(height1));
        jRadioButton3 = new JRadioButton("紧急");
        if (leave.getIsExigency() == 1)
            jRadioButton3.setSelected(true);
        box2.add(jRadioButton3);

        //-------basebox
        baseBox = Box.createHorizontalBox();
        baseBox.add(box1);
        baseBox.add(Box.createHorizontalStrut(30));
        baseBox.add(box2);

        pNorth = new JPanel();
        pNorth.add(baseBox);

        pSouth = new JPanel();
        button = new JButton("确定");
        pSouth.add(button);


        setBounds(100,100,500,450);
        //居中
        setLocationRelativeTo(null);
        //不可更改
        setResizable(false);
        setVisible(true);
        validate();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public static void main(String[] args) {
        User user = new User();
        user.setUserId(4);
        new LeavePanel(new UserMapper().selectUserByNameOREmailORId(user).get(0),1);
    }
}*/
