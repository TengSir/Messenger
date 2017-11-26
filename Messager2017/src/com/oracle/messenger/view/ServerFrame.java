package com.oracle.messenger.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.oracle.messenger.control.DBOperator;
import com.oracle.messenger.control.ServerFrameUIConfig;
import com.oracle.messenger.model.MessageBox;
import com.oracle.messenger.model.User;

public class ServerFrame extends JFrame {
	
	//封装，根据实际情况，我们为了编程的便利性，我们将UI和后台控制Socket的代码整合到这个一个类中，
	
	private ServerSocket  server;

	private AllButtonListener  listener;//内部类监听对象
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel;
	private JTable table;
	private TableModel  model;
	private JScrollPane scrollPane_1;
	private JTextArea textArea;
	private JLabel lblNewLabel_1;
	private Panel panel_1;
	private Panel panel;
	private JButton button;
	private JButton button_3;
	private JButton button_2;
	private JButton button_1;
	private JButton btnNewButton;
	private JButton button_4;
	
	//动态代码块是另外一个补充型的在类构造对象时执行初始化的结构，
	
	//定义第一个动态代码块，做一些其他的初始化业务，比如说初始化本类需要使用的监听对象(除了ui组件的之外的活)
	{
		
		listener=new AllButtonListener();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerFrame frame = new ServerFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ServerFrame() {//异常
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ServerFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(ServerFrameUIConfig.serverFrameTitle);
		setSize(ServerFrameUIConfig.serverFrameWidth, ServerFrameUIConfig.serverFrameHeight);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("在线用户列表");
		lblNewLabel.setBorder(BorderFactory.createLineBorder(Color.gray));
		lblNewLabel.setBounds(18, 21, 241, 15);
		contentPane.add(lblNewLabel);
		
		model=new DefaultTableModel(new Object[]{"登陆IP","用户昵称"},0) ;
		table = new JTable(model);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(18, 48, 241, 296);
		contentPane.add(scrollPane);
		
		lblNewLabel_1 = new JLabel("所有用户发送的消息列表");
		lblNewLabel_1.setBorder(BorderFactory.createLineBorder(Color.gray));
		lblNewLabel_1.setBounds(298, 21, 675, 15);
		contentPane.add(lblNewLabel_1);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane_1 = new JScrollPane(textArea);
		scrollPane_1.setBounds(298, 48, 675, 296);
		contentPane.add(scrollPane_1);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(18, 392, 955, 170);
		contentPane.add(tabbedPane);
		
		panel_1 = new Panel();
		tabbedPane.addTab("基本控制", null, panel_1, null);
		panel_1.setLayout(null);
		
		button = new JButton("启动服务器");
		button.addActionListener(listener);
		button.setBounds(72, 21, 93, 23);
		panel_1.add(button);
		
		button_1 = new JButton("停止服务器");
		button_1.addActionListener(listener);
		button_1.setBounds(196, 21, 93, 23);
		panel_1.add(button_1);
		
		button_2 = new JButton("断开所有用户");
		button_2.addActionListener(listener);
		button_2.setBounds(325, 21, 116, 23);
		panel_1.add(button_2);
		
		button_3 = new JButton("修改服务端端口");
		button_3.addActionListener(listener);
		button_3.setBounds(481, 21, 145, 23);
		panel_1.add(button_3);
		
		btnNewButton = new JButton("移除指定用户");
		btnNewButton.addActionListener(listener);
		btnNewButton.setBounds(636, 21, 116, 23);
		panel_1.add(btnNewButton);
		
		button_4 = new JButton("中断所有消息传输");
		button_4.setBounds(783, 21, 157, 23);
		button_4.addActionListener(listener);
		panel_1.add(button_4);
		
		panel = new Panel();
		tabbedPane.addTab("高级控制", null, panel, null);
		
		
		setLocationRelativeTo(null);
	}
	
	
	
	
	//本类中有若干个按钮要实现监听事件，所以，我可以定义一个内部类来实现监听，体现了更好的封装
	class AllButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==button) {
					try {
						server=new ServerSocket(ServerFrameUIConfig.serverPort);
						button.setEnabled(false);//设置启动按钮为不可用
						//启动按钮除了要创建server对象之外，还要开启对外服务，accpet
						while(true)
						{
							Socket  c=server.accept();
							ObjectOutputStream  out=new ObjectOutputStream(c.getOutputStream());
							ObjectInputStream  in=new ObjectInputStream(c.getInputStream());
							//应该在有一个客户端链接进来之后，我就开启一个线程，针对他单独和服务器通讯
							
							ClientMessageReciveThread  thisClientThread=new ClientMessageReciveThread(out, in);
							thisClientThread.start();//启动这个线程，让他独立运行
						}
					} catch (IOException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(ServerFrame.this, "啊奥，服务器启动失败!", "温馨提示", JOptionPane.ERROR_MESSAGE);
						
					}
			}else if(e.getSource()==button_1) {
				System.out.println("stop");
			}else if(e.getSource()==button_2) {
				System.out.println("change");
			}
		}
	}
	//server  many  thread  service for  all  client 多线程针对多个客户端服务的这样服务模式
	//定义一个独立的线程类，这个类处理某一个客户端和服务器的通讯
	class ClientMessageReciveThread extends Thread{
		private ObjectOutputStream  out;
		private ObjectInputStream in;
		
		public ClientMessageReciveThread(ObjectOutputStream out, ObjectInputStream in) {
			super();
			this.out = out;
			this.in = in;
		}

		//服务端的代码都在这里了，
		@Override
		public void run() {
			try {
				while(true)//不停的读取客户端发送过来的消息
				{
					MessageBox  m=(MessageBox)in.readObject();//当前这个线程接收到这个客户端发送过来的一个Message对象
					
					if(m.getType().equals("login")) {
						//链接数据库判断用户登陆信息是否正确
						User loginedUser=DBOperator.login(m.getFrom().getUsername(), m.getFrom().getPassword());
						
						//当服务器根据传过来的用户名和密码查询完数据库之后，无论登陆成功还失败都要给用户回一个消息(都要封装成MessageBox)
						
						MessageBox  loginResult=new MessageBox();
						loginResult.setFrom(loginedUser);
						loginResult.setType("loginResult");
						out.writeObject(loginResult);
						out.flush();
					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
	}
	
}
