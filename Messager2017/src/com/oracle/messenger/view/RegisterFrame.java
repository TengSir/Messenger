package com.oracle.messenger.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import com.oracle.messenger.control.ClientFrameUIConfig;
import com.oracle.messenger.control.ServerFrameUIConfig;
import com.oracle.messenger.model.MessageBox;
import com.oracle.messenger.model.User;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class RegisterFrame extends JFrame {

	private Socket  client;
	private ObjectOutputStream  out;
	private  ObjectInputStream  in;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_1;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_4;
	private JLabel label_5;
	private JTextArea textArea;
	private JComboBox comboBox_1;
	private JComboBox comboBox;
	private JRadioButton radioButton;
	private JRadioButton radioButton_1;
	private JButton button;
	private JButton button_1;
	

	/**
	 * Create the frame.
	 */
	public RegisterFrame(ObjectOutputStream  out, ObjectInputStream  in) {
		this.out=out;
		this.in=in;
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/Inform.gif")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(ClientFrameUIConfig.loginFrameWidth, ClientFrameUIConfig.loginFrameHeight);
		setTitle(ClientFrameUIConfig.loginFrameTitle);
		setLocation(50, 50);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(112, 66, 148, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblNewLabel = new JLabel("用户账号");
		lblNewLabel.setBounds(20, 69, 87, 15);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("账号密码");
		lblNewLabel_1.setBounds(20, 111, 87, 15);
		contentPane.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(112, 108, 148, 21);
		contentPane.add(passwordField);
		
		label = new JLabel("确认密码");
		label.setBounds(20, 151, 87, 15);
		contentPane.add(label);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(112, 148, 148, 21);
		contentPane.add(passwordField_1);
		
		label_1 = new JLabel("用户性别");
		label_1.setBounds(20, 197, 87, 15);
		contentPane.add(label_1);
		
		radioButton = new JRadioButton("男");
		radioButton.setBounds(112, 193, 69, 23);
		contentPane.add(radioButton);
		
		radioButton_1 = new JRadioButton("女");
		radioButton_1.setBounds(183, 193, 77, 23);
		contentPane.add(radioButton_1);
		ButtonGroup  g=new ButtonGroup();
		g.add(radioButton);
		g.add(radioButton_1);
		
		label_2 = new JLabel("用户年龄");
		label_2.setBounds(20, 243, 87, 15);
		contentPane.add(label_2);
		
		comboBox = new JComboBox();
		for (int i = 1; i <101; i++) {
			comboBox.addItem(i+"");
		}
		comboBox.setBounds(112, 240, 148, 21);
		contentPane.add(comboBox);
		
		JLabel label_3 = new JLabel("用户昵称");
		label_3.setBounds(20, 292, 87, 15);
		contentPane.add(label_3);
		
		textField_1 = new JTextField();
		textField_1.setBounds(112, 289, 148, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		label_4 = new JLabel("用户头像");
		label_4.setBounds(20, 345, 87, 15);
		contentPane.add(label_4);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(112, 342, 148, 58);
		contentPane.add(comboBox_1);
		
		label_5 = new JLabel("个性签名");
		label_5.setBounds(20, 434, 77, 15);
		contentPane.add(label_5);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBounds(112, 430, 148, 137);
		contentPane.add(textArea);
		
		button = new JButton("提交");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				System.out.println("点击了提交按钮");
				//1.先提取界面上用户输入的数据
				String yonghuming=textField.getText().trim();
				String mima=passwordField.getText();
				String sex=radioButton.isSelected()?"男":"女";
				int age=Integer.parseInt(comboBox.getSelectedItem().toString());
				
				String nicheng=textField_1.getText().trim();
				String touxiang="images/1.jpg";
				String qianming=textArea.getText().toString();
				//2.表单验证
				
				//3.封装成MessageBox
				User  u=new User(yonghuming, mima, sex, age, nicheng, qianming, touxiang);
				System.out.println(u);
				MessageBox  registerMessage=new MessageBox();
				registerMessage.setFrom(u);
				registerMessage.setType("register");
				
				//4.使用序列化写给服务器，让服务器注册
				try {
					RegisterFrame.this.out.writeObject(registerMessage);
					RegisterFrame.this.out.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.out.println("send end");
				//5.根据服务器给我的回复的注册消息进一步跳转界面
				MessageBox result=null;
				try {
					result = (MessageBox)RegisterFrame.this.in.readObject();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(RegisterFrame.this, "注册"+(result.getContent().equals("true")?"成功":"失败"),"温馨提示",JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		button.setBounds(36, 623, 93, 23);
		contentPane.add(button);
		
		button_1 = new JButton("去登陆");
		button_1.setBounds(154, 623, 93, 23);
		contentPane.add(button_1);
	}
}
