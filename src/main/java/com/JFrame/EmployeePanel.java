package com.JFrame;

import com.bean.ClockCard;
import com.bean.Leave;
import com.bean.User;
import com.mapper.ClockCardMapper;
import com.mapper.UserMapper;
import com.utils.CheckStatus;
import com.utils.Message;
import sun.swing.SwingUtilities2;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeePanel extends JFrame implements ActionListener{
    Object a[][] ;
    Object name[] = {"日期","上班时间","下班时间","出勤状态"};
    JButton attendanceSchedule, applyForAttendance, startWork,endWork,modifyPersonalInformation,flush,evection;
    JTextField inputPowsNumber;
    JPanel pSouth,pNorth;
    JTable table;
    JLabel id,userName,sex,marriage,phone,maill,department,status;
    Box box1,box2,box3,box4,box5,box6,baseBox;
    User initUser;
    public EmployeePanel(User user) throws SQLException {
        super(user.getUserName());
        initUser = user;
        init(user);
        startWork.addActionListener(this);
        endWork.addActionListener(this);
        modifyPersonalInformation.addActionListener(this);
        flush.addActionListener(this);
        applyForAttendance.addActionListener(this);
        evection.addActionListener(this);
        attendanceSchedule.addActionListener(this);
        setBounds(100,100,500,500);
        //居中
        setLocationRelativeTo(null);
        //不可更改
        setResizable(false);
        setVisible(true);
        validate();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void init(User user) throws SQLException {
        //----初始化开始
        id = new JLabel(String.valueOf(user.getUserId()));
        userName = new JLabel(user.getUserName());
        if (user.getUserSex() == 'M')
            sex = new JLabel("男");
        else
            sex = new JLabel("女");
        if (user.getUserMarriage() == 0)
            marriage = new JLabel("未婚");
        else
            marriage = new JLabel("已婚");
        phone = new JLabel(user.getUserPhone());
        maill = new JLabel(user.getUserMail());
        department = new JLabel(user.getUserDepartment());
        status = new JLabel(new CheckStatus().getStatus(user.getUserState()));
        //-----初始化结束

        //-----box1
        box1 = Box.createVerticalBox();
        int hight1 = 8;
        box1.add(Box.createVerticalStrut(hight1));
        box1.add(new JLabel("工号:"));
        box1.add(Box.createVerticalStrut(hight1));
        box1.add(new JLabel("姓名:"));
        box1.add(Box.createVerticalStrut(hight1));
        box1.add(new JLabel("性别:"));
        box1.add(Box.createVerticalStrut(hight1));
        box1.add(new JLabel("婚姻:"));
        box1.add(Box.createVerticalStrut(hight1));

        //-----box2
        box2 = Box.createVerticalBox();
        int hight2 = 8;
        box2.add(Box.createVerticalStrut(hight2));
        box2.add(id);
        box2.add(Box.createVerticalStrut(hight2));
        box2.add(userName);
        box2.add(Box.createVerticalStrut(hight2));
        box2.add(sex);
        box2.add(Box.createVerticalStrut(hight2));
        box2.add(marriage);
        box2.add(Box.createVerticalStrut(hight2));

        //------box3
        box3 = Box.createVerticalBox();
        box3.add(Box.createVerticalStrut(hight1));
        box3.add(new JLabel("电话:"));
        box3.add(Box.createVerticalStrut(hight1));
        box3.add(new JLabel("邮箱:"));
        box3.add(Box.createVerticalStrut(hight1));
        box3.add(new JLabel("部门:"));
        box3.add(Box.createVerticalStrut(hight1));

        //------box4
        box4 = Box.createVerticalBox();
        box4.add(Box.createVerticalStrut(hight2));
        box4.add(phone);
        box4.add(Box.createVerticalStrut(hight2));
        box4.add(maill);
        box4.add(Box.createVerticalStrut(hight2));
        box4.add(department);
        box4.add(Box.createVerticalStrut(hight2));

        //------box5
        box5 = Box.createHorizontalBox();
        box5.add(Box.createHorizontalStrut(50));
        box5.add(box1);
        box5.add(Box.createHorizontalStrut(20));
        box5.add(box2);
        box5.add(Box.createHorizontalStrut(20));
        box5.add(box3);
        box5.add(Box.createHorizontalStrut(20));
        box5.add(box4);

        //------box6
        box6 = Box.createHorizontalBox();
        int wright = 10;
        box6.add(Box.createHorizontalStrut(wright));
        startWork = new JButton("上班打卡");
        box6.add(startWork);
        box6.add(Box.createHorizontalStrut(wright));
        endWork = new JButton("下班打卡");
        box6.add(endWork);
        box6.add(Box.createHorizontalStrut(wright));
        modifyPersonalInformation = new JButton("修改个人信息");
        box6.add(modifyPersonalInformation);
        box6.add(Box.createHorizontalStrut(wright));

        //-------baseBox
        baseBox = Box.createVerticalBox();
        baseBox.add(box5);
        baseBox.add(box6);
        baseBox.add(Box.createVerticalStrut(20));
        Box box = Box.createHorizontalBox();
        Font font=new Font("宋体", Font.BOLD,16);
        JLabel jLabel = new JLabel("历史打卡信息 ");
        jLabel.setFont(font);
//        baseBox.add(jLabel);
        box.add(jLabel);
        baseBox.add(box);


        attendanceSchedule = new JButton("查看申请进度");
        applyForAttendance = new JButton("申请出勤");
        evection = new JButton("申请出差");
        flush = new JButton("刷新");

        //----table
        List<ClockCard> addCC = new ClockCardMapper().getAllClockInformation();
        List<ClockCard> userList = new ArrayList<ClockCard>();
        for (ClockCard c :addCC
                ) {
            if (c.getUserId() == user.getUserId())
                userList.add(c);
        }
        int count = 0;
        a = new Object[userList.size()+12][4];
        count = userList.size();
        table = new JTable(a,name);
        table.setRowHeight(20);
        for (int i=0 ; i < count; i++){
            ClockCard temp = userList.get(i);
            table.setValueAt(temp.getDate(),i,0);
            table.setValueAt(temp.getStartTime(),i,1);
            table.setValueAt(temp.getEndTime(),i,2);
            if (temp.getStatus() == 0)
                table.setValueAt("出勤",i,3);
            else if(temp.getStatus() == 1)
                table.setValueAt("迟到",i,3);
            else if(temp.getStatus() == 2)
                table.setValueAt("旷工",i,3);
            else if(temp.getStatus() == 3)
                table.setValueAt("迟到    旷工",i,3);
        }

        pSouth = new JPanel();
        pSouth.add(applyForAttendance);
        pSouth.add(evection);
        pSouth.add(attendanceSchedule);
        pSouth.add(flush);
        add(pSouth, BorderLayout.SOUTH);
        add(baseBox,BorderLayout.NORTH);
        add(new JScrollPane(table),BorderLayout.CENTER);
    }
    public void update(User user) throws SQLException {
        id.setText(String.valueOf(user.getUserId()));
        userName.setText(user.getUserName());
        if (user.getUserSex() == 'M')
            sex.setText("男");
        else
            sex.setText("女");
        if (user.getUserMarriage() == 0)
            marriage.setText("未婚");
        else
            marriage.setText("已婚");
        phone.setText(user.getUserPhone());
        maill.setText(user.getUserMail());
        department.setText(user.getUserDepartment());

        //----table
        List<ClockCard> addCC = new ClockCardMapper().getAllClockInformation();
        List<ClockCard> userList = new ArrayList<ClockCard>();
        for (ClockCard c :addCC
                ) {
            if (c.getUserId() == user.getUserId())
                userList.add(c);
        }
        int count = 0;
        count = userList.size();
        for (int i=0 ; i < count; i++){
            ClockCard temp = userList.get(i);
            table.setValueAt(temp.getDate(),i,0);
            table.setValueAt(temp.getStartTime(),i,1);
            table.setValueAt(temp.getEndTime(),i,2);
            if (temp.getStatus() == 0)
                table.setValueAt("出勤",i,3);
            else if(temp.getStatus() == 1)
                table.setValueAt("迟到",i,3);
            else if(temp.getStatus() == 2)
                table.setValueAt("旷工",i,3);
            else if(temp.getStatus() == 3)
                table.setValueAt("迟到 旷工",i,3);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == modifyPersonalInformation){
            try {
                new ModifyPersonalInformationPanel(initUser);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        if (e.getSource() == flush){
            User temp = new User();
            temp.setUserId(initUser.getUserId());
            try {
                update(new UserMapper().selectUserByNameOREmailORId(temp).get(0));
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        if (e.getSource() == startWork){
            try {
                if (new ClockCardMapper().startWork(initUser.getUserId()) != 1){
                    new Message("今日已打卡");
                }else {
                    new Message("打卡成功");
                    update(initUser);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        if (e.getSource() == endWork){
            try {
                if (new ClockCardMapper().endWork(initUser.getUserId()) != 1){
                    new Message("今日已打卡或未打上班卡");
                }else {
                    new Message("打卡成功");
                    update(initUser);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        if (e.getSource() == applyForAttendance){
            new LeavePanel(initUser,0);
        }
        if (e.getSource() == evection){
            new LeavePanel(initUser,1);
        }
        if (e.getSource() == attendanceSchedule){
            try {
                new LeaveShowPanel(initUser);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        User user = new User();
        user.setUserId(2);
        new EmployeePanel(new UserMapper().selectUserByNameOREmailORId(user).get(0));
    }
}
