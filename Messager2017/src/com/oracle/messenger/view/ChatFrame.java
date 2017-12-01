package com.oracle.messenger.view;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.oracle.messenger.model.MessageBox;
import com.oracle.messenger.model.User;
public class ChatFrame extends JFrame {
	private ObjectInputStream  in;
	private ObjectOutputStream  out;
	private User my,your;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JScrollPane scrollPane_1;
	private JTextArea textArea_1;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private String qunMing;

	

	/**
	 * @return the textArea
	 */
	public JTextArea getTextArea() {
		return textArea;
	}
	private ChatFrame() {
		setBounds(100, 100, 784, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(10, 10, 600, 206);
		contentPane.add(scrollPane);
		
		textArea_1 = new JTextArea();
		scrollPane_1 = new JScrollPane(textArea_1);
		scrollPane_1.setBounds(10, 288, 600, 121);
		contentPane.add(scrollPane_1);
		
		btnNewButton = new JButton("send");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//这里面是处理当用户点击发送按钮式要执行的业务代码
				//1.先获取编辑文本框里面的输入的要发送的文本
				String  willSendMessage=textArea_1.getText();
				
				
				//2.先将消息封装成MessageBox发送给服务器
				MessageBox  b=new MessageBox();
				b.setContent(willSendMessage);
				b.setFrom(ChatFrame.this.my);
				
				if(ChatFrame.this.your==null)//判断是群聊窗口
				{	String qunming;
					User user=new User();
					user.setUsername(qunMing);
					b.setTo(user);
					b.setType("qunMessage");
				}else
				{
					b.setType("textMessage");
					b.setTo(ChatFrame.this.your);
				}
				
				//3.用从登录界面传过来的输出流写给服务器，让服务器帮我转发给我我的好友
				try {
					ChatFrame.this.out.writeObject(b);
					ChatFrame.this.out.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				//4.接受服务器给我回发的时间
				
//					MessageBox  time=(MessageBox)ChatFrame.this.in.readObject();
//					
					//5.将即将发送的消息设置到上面的这个聊天信息框里面
					
					String nowTime=new Date().toLocaleString();
					
					textArea.append(ChatFrame.this.my.getNickname()+"  :  "+nowTime+"\t\t\r\n"+willSendMessage+"\r\n\r\n");
				//6.将消息发送狂里面的内容清空
				textArea_1.setText("");
				
				
			}
		});
		btnNewButton.setBounds(60, 440, 93, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("close");
		btnNewButton_1.setBounds(248, 440, 93, 23);
		contentPane.add(btnNewButton_1);
		
		button = new JButton("抖动");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				MessageBox  m=new MessageBox();
				
				m.setFrom(ChatFrame.this.my);
				m.setTo(ChatFrame.this.your);
				m.setType("shakeMessage");
				shakeWindow();
			}
		});
		button.setBounds(39, 244, 93, 23);
		contentPane.add(button);
		
		button_1 = new JButton("表情");
		button_1.setBounds(213, 244, 93, 23);
		contentPane.add(button_1);
		
		button_2 = new JButton("发送文件");
		button_2.setBounds(365, 244, 93, 23);
		contentPane.add(button_2);
		
		button_3 = new JButton("屏幕截图");
		button_3.setBounds(503, 244, 93, 23);
		contentPane.add(button_3);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		lblNewLabel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage("resources/images/your.jpg").getScaledInstance( 138, 198, Image.SCALE_DEFAULT)));
		lblNewLabel.setBounds(620, 15, 138, 198);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(620, 293, 138, 183);
		lblNewLabel_1.setBorder(BorderFactory.createLineBorder(Color.black));
		lblNewLabel_1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage("resources/images/self.jpg").getScaledInstance( 138, 183, Image.SCALE_DEFAULT)));
		contentPane.add(lblNewLabel_1);
	}


	public ChatFrame(User my,String qunMing,ObjectInputStream  in,ObjectOutputStream  out) {
		this();
		this.in=in;
		this.out=out;
		this.my=my;
		this.qunMing=qunMing;
		this.setTitle(qunMing+"群聊中");
	}
	/**
	 * Create the frame.
	 */
	public ChatFrame(User my,User your,ObjectInputStream  in,ObjectOutputStream  out) {
		this();
		this.in=in;
		this.out=out;
		this.my=my;
		this.your=your;
		
		
	}
	
	public void  shakeWindow() {
		new Thread() {
			public void run() {
				int pianyi=3;
				int waitTime=50;
				int lastX=ChatFrame.this.getX();
				int lasty=ChatFrame.this.getY();
				for(int n=0;n<100;n++)
				{
					ChatFrame.this.setLocation(lastX-pianyi, lasty);
					try {
						Thread.sleep(waitTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ChatFrame.this.setLocation(lastX, lasty-pianyi);
					try {
						Thread.sleep(waitTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ChatFrame.this.setLocation(lastX+pianyi, lasty);
					try {
						Thread.sleep(waitTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ChatFrame.this.setLocation(lastX, lasty+pianyi);
					try {
						Thread.sleep(waitTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
				ChatFrame.this.setLocation(lastX, lasty);
			};
			
		}.start();
	}
}
