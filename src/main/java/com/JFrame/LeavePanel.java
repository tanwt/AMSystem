package com.JFrame;

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
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static javax.swing.BoxLayout.*;

public class LeavePanel extends JFrame{
    private JTextField start,end,site;
    private JRadioButton jRadioButton1,jRadioButton2,jRadioButton3;
    private JTextArea reason;
    private JButton button,alter,delete,pass,reject;
    private Box box1,box2,baseBox;
    private JPanel pSouth,pNorth;
    private Leave leave;
    private User initUser;
    private int leaveType;
    public LeavePanel(User user, final int leaveType){
        super("申请出勤");
        if (leaveType == 1)
            setTitle("申请出差");
        this.leaveType = leaveType;
        leave = new Leave();
        init();
        initUser = user;
        pNorth = new JPanel();
        pNorth.add(baseBox);

        pSouth = new JPanel();
        button = new JButton("提交");
        pSouth.add(button);
        add(pNorth,BorderLayout.NORTH);
        add(pSouth,BorderLayout.SOUTH);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addData();
                List list = null;
                if (leaveType == 1)
                {
                    list = CheckNull.checkObject(leave,new String[]{"id","type","rejection","status"});
                }else
                    list = CheckNull.checkObject(leave,new String[]{"id","site","rejection","status"});
                if (list != null){
                    if (list.get(0).equals("start"))
                        new Message("起始时间不能为空");
                    if (list.get(0).equals("end"))
                        new Message("结束时间不能为空");
                    if (leaveType == 1)
                        if (list.get(0).equals("site"))
                            new Message("地点不能为空");
                    if (list.get(0).equals("reason"))
                        new Message("理由不能为空");
                    if (!Verify.isValidDate(leave.getStart()))
                        new Message("起始时间格式错误");
                    if (!Verify.isValidDate(leave.getEnd()))
                        new Message("结束时间格式错误");
                }else {
                    if (leaveType == 1)
                        leave.setType(2);
                    int reslut = 0;
                    try {
                        reslut = new LeaveMapper().addLeave(leave);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    if (reslut == 1) {
                        new Message("申请成功");
                        dispose();
                    }
                    else
                        new Message("申请失败");
                }

            }
        });

        /**
         * 焦点事件处理
         */

        /**
         * 日期框对应焦点事件
         */
        start.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (start.getText().equals("日期格式：yyyy-MM-dd"))
                    start.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (start.getText().equals("") || start.getText() == null)
                    start.setText("日期格式：yyyy-MM-dd");
            }
        });
        end.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (end.getText().equals("日期格式：yyyy-MM-dd"))
                    end.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (end.getText().equals("") || end.getText() == null)
                    end.setText("日期格式：yyyy-MM-dd");
            }
        });
        reason.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (reason.getText().equals("限100字"))
                    reason.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (reason.getText().equals("") || reason.getText() == null)
                    reason.setText("限100字");
            }
        });
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



    }

    public void addData(){
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
        if (jRadioButton3.isSelected())
            leave.setIsExigency(1);
        else
            leave.setIsExigency(0);
    }

    /**
     * 请假窗体的查看形式
     * @param leave
     */
    //---开始
    public LeavePanel(Leave leave,User user) throws SQLException {
        super("申请详情");
        this.initUser = user;
        this.leave = leave;
        if (leave.getStatus() == 0)
            init2(leave);
        else
            init3(leave);
    }

    /**
     * 未处理的查看形式
     * @param leave
     */
    public void init2(final Leave leave){
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
        alter = new JButton("修改");
        delete = new JButton("撤回");
        pass = new JButton("同意");
        reject = new JButton("驳回");
        if (initUser.getUserState() == 0){
            pSouth.add(alter);
            pSouth.add(delete);
        }if (initUser.getUserState() == 1){
            pSouth.add(pass);
            pSouth.add(reject);
        }
        pSouth.add(button);
        add(pNorth,BorderLayout.NORTH);
        add(pSouth,BorderLayout.SOUTH);

        //不可编辑
        LeavePanelSetEnabled(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!start.isEnabled())
                    dispose();
                else {
                    addData();
                    int reslut = 0;
                    try {
                        reslut = new LeaveMapper().updateLeave(leave);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    if (reslut == 1) {
                        new Message("修改成功");
                        dispose();
                    }
                    else
                        new Message("系统错误");
                }
            }
        });

        alter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LeavePanelSetEnabled(true);
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int reslut = 0;
                try {
                    reslut = new LeaveMapper().deleteLeave(leave.getId());
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                if (reslut == 1) {
                    new Message("撤回成功");
                    dispose();
                }else
                    new Message("系统错误");
            }
        });

        pass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leave.setStatus(1);
                leave.setAuditorId(initUser.getUserId());
                try {
                    if (new LeaveMapper().updateLeave(leave) != 0) {
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
        reject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leave.setStatus(2);
                leave.setAuditorId(initUser.getUserId());
                new RejectPanel(leave);
            }
        });

        setBounds(100,100,500,450);
        //居中
        setLocationRelativeTo(null);
        //不可更改
        setResizable(false);
        setVisible(true);
        validate();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * 改变可编辑
     */
    public void LeavePanelSetEnabled(boolean flag){
            start.setEnabled(flag);
            end.setEnabled(flag);
            if (leave.getType() == 2)
                site.setEnabled(flag);
            else {
                jRadioButton1.setEnabled(flag);
                jRadioButton2.setEnabled(flag);
            }
            reason.setEnabled(flag);
            jRadioButton3.setEnabled(flag);
    }

    /**
     * 已处理查看形式
     * @param leave
     */
    private void init3(Leave leave) throws SQLException {
        if (leave.getType() == 2)
            leaveType = 1;
        else
            leaveType = 0;
        //-----box1
        box1 = Box.createVerticalBox();
        //垂直支撑间距
        int height1 = 16;
        box1.add(new JLabel("申请号："));
        box1.add(Box.createVerticalStrut(height1));
        if (leaveType == 1) {
            box1.add(new JLabel("出差时间:"));
        }
        else {
            box1.add(new JLabel("出勤时间:"));
        }
        box1.add(Box.createVerticalStrut(height1));
        if (leaveType == 1)
            box1.add(new JLabel("出差地点:"));
        else
            box1.add(new JLabel("出勤类型:"));
        box1.add(Box.createVerticalStrut(height1));
        if (leaveType == 1)
            box1.add(new JLabel("出差原因:"));
        else
            box1.add(new JLabel("出勤原因:"));
        box1.add(Box.createVerticalStrut(height1));
        box1.add(new JLabel("审核人:"));
        box1.add(Box.createVerticalStrut(height1));
        box1.add(new JLabel("处理结果:"));
        box1.add(Box.createVerticalStrut(height1));
        if (leave.getStatus() == 2) {
            box1.add(new JLabel("驳回原因:"));
            box1.add(Box.createVerticalStrut(height1));
        }

        //-----box2
        box2 = Box.createVerticalBox();
        box2.add(new JLabel(String.valueOf(leave.getId())));
        box2.add(Box.createVerticalStrut(height1));
        box2.add(new JLabel(leave.getStart() + " 至 " + leave.getEnd()));
        box2.add(Box.createVerticalStrut(height1));
        if (leaveType == 1)
            box2.add(new JLabel(leave.getSite()));
        else{
            if (leave.getType() == 0)
                box2.add(new JLabel("事假"));
            else
                box2.add(new JLabel("病假"));
        }
        box2.add(Box.createVerticalStrut(height1));
        box2.add(new JTextField(leave.getReason(),16));
        box2.add(Box.createVerticalStrut(height1));
        User user = new User();
        user.setUserId(leave.getAuditorId());
        box2.add(new JLabel(new UserMapper().selectUserByNameOREmailORId(user).get(0).getUserName()));
        box2.add(Box.createVerticalStrut(height1));
        String leaveStatus = null;
        if (leave.getStatus() == 1)
            leaveStatus = "通过";
        else
            leaveStatus = "驳回";
        box2.add(new JLabel(leaveStatus));
        box2.add(Box.createVerticalStrut(height1));
        if (leaveStatus.equals("驳回")){
            box2.add(new JTextField(leave.getRejection(),16));
            box2.add(Box.createVerticalStrut(height1));
        }

        //-------basebox
        baseBox = Box.createHorizontalBox();
        baseBox.add(box1);
        baseBox.add(Box.createHorizontalStrut(50));
        baseBox.add(box2);

        pNorth = new JPanel();
        pNorth.add(baseBox);

        pSouth = new JPanel();
        button = new JButton("确定");
        pSouth.add(button);
        add(pNorth,BorderLayout.CENTER);
        add(pSouth,BorderLayout.SOUTH);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setBounds(100,100,500,350);
        //居中
        setLocationRelativeTo(null);
        //不可更改
        setResizable(false);
        setVisible(true);
        validate();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    //---结束
    public static void main(String[] args) throws SQLException {
        User user = new User();
        user.setUserId(4);
//        new LeavePanel(new UserMapper().selectUserByNameOREmailORId(user).get(0),0);
        Leave leave = new LeaveMapper().getAllLeave().get(12);
        new LeavePanel(leave,new UserMapper().selectUserByNameOREmailORId(user).get(0));
    }
}
