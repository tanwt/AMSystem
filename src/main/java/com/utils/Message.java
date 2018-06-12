package com.utils;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 信息弹出窗
 */
public class Message extends JFrame{
	JLabel label;
	JButton button;
	Box baseBox,box1,box2;
	public Message(String s) {
		// TODO Auto-generated constructor stub
		super("消息提醒");
		setLayout(new FlowLayout());
		setSize(350	, 150);
		setLocationRelativeTo(null);
		label = new JLabel(s);
		button = new JButton("确定");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		box1 = Box.createHorizontalBox();
		box1.add(label);
		box1.add(Box.createHorizontalStrut(20));
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
	}

	public static void main(String[] args) {
		new Message("	为为");
	}
}


