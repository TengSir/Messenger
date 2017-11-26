package com.oracle.messenger.view;

import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.oracle.messenger.control.ClientFrameUIConfig;
import com.oracle.messenger.control.ServerFrameUIConfig;
import com.oracle.messenger.model.MessageBox;
import com.oracle.messenger.model.User;

public class LoginFrame extends JFrame {
	
	//类需要用到的对象用has-a关系在类中定义，在合适的位置实例化
	private Socket  client;
	private ObjectOutputStream  out;
	private  ObjectInputStream  in;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JComboBox comboBox;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		 JFrame.setDefaultLookAndFeelDecorated(true);   
	        SwingUtilities.invokeLater(new Runnable() {   
	            public void run() {   
	                Window w = new LoginFrame();   
	                w.setVisible(true);   
	                //要使窗口透明，您可以使用 AWTUtilities.setWindowOpacity(Window, float) 方法   
	                com.sun.awt.AWTUtilities.setWindowOpacity(w, 0.9f);   
	            }   
	        }); 
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
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
		
		comboBox = new JComboBox(new Object[] {"222","333","111"});
		comboBox.setEditable(true);
		comboBox.setBounds(66, 217, 178, 21);
		contentPane.add(comboBox);
		
		passwordField = new JPasswordField("222");
		passwordField.setBounds(66, 261, 178, 21);
		contentPane.add(passwordField);
		
		btnNewButton = new JButton("登陆");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//当用户点击了登陆按钮后，这个按钮事件要做的业务代码分如下几步
				//1.先做表单验证
				//trim是String类的方法，去字符串的前后空白符
				String  yourINputUsername=comboBox.getSelectedItem().toString().trim();
				String yourInputPassword=passwordField.getText().toString().trim();
				if(yourINputUsername.length()<2) {
					JOptionPane.showMessageDialog(LoginFrame.this, "用户名长度不够!","温馨提示",JOptionPane.ERROR_MESSAGE);
					comboBox.requestFocus();
					return ;
				}else
				{
					if(yourInputPassword.length()<2) {
						JOptionPane.showMessageDialog(LoginFrame.this, "密码长度不够!","温馨提示",JOptionPane.ERROR_MESSAGE);
						passwordField.requestFocus();
						return ;
					}else
					{
						//2.建立和服务器的链接(Socket链接)
						
						
						try {
							if(client==null)
							{
								client=new Socket(ServerFrameUIConfig.serverIP, ServerFrameUIConfig.serverPort);
								  out=new ObjectOutputStream(client.getOutputStream());
								    in=new ObjectInputStream(client.getInputStream());
							}
						} catch (Exception e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(LoginFrame.this, "无法连接服务器，请检查网络!","温馨提示",JOptionPane.ERROR_MESSAGE);
						}
						
						//3.在socket的基础上获取输入输出流，然后用输出流将消息发送给服务器，让服务器校验我们的账号和密码是否成功
						
						try {
							//消息要封装成对象，所以，要传递消息要用Object流
							
							//因为我们将消息封装成特定的类型，所以，每次再给服务器发送消息时，都要封装成特定的消息对象才可以
							
							
							MessageBox  loginMessage=new MessageBox();
							User willLoginUser=new User(yourINputUsername,yourInputPassword);
							loginMessage.setFrom(willLoginUser);
							loginMessage.setType("login");
							
							//封装完消息后使用序列化流将消息对象写向网络网络另外一段
							out.writeObject(loginMessage);
							out.flush();
							
							//当客户端把登陆消息发送出去后，应该立马读取服务器回发的登陆结果消息
							
							MessageBox  result=(MessageBox)in.readObject();
							if(result.getFrom()==null) {
								JOptionPane.showMessageDialog(LoginFrame.this, "登陆失败,请检查用户名和密码!","温馨提示",JOptionPane.ERROR_MESSAGE);
							}else
							{
								User u=result.getFrom();//登陸程序到的用戶資料，存儲在服務器給我發過來的消息裡面的From屬性裡面的
								MainFrame  m=new MainFrame(u);
								m.setVisible(true);
								LoginFrame.this.setVisible(false);
							}
							
							
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						
						
					}
				}
				
			}
		});
		btnNewButton.setBounds(82, 308, 66, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("注册");
		btnNewButton_1.setBounds(158, 308, 66, 23);
		contentPane.add(btnNewButton_1);
	}
}
