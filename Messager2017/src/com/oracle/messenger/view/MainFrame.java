package com.oracle.messenger.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import com.oracle.messenger.control.ClientFrameUIConfig;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblUsername;
	private JTextArea txtrAboutDescriptions;
	private JPanel panel_1;
	private JPanel panel_2;
	private JScrollPane scrollPane;
	private JTree tree;
	private JPanel panel;
	private JTabbedPane tabbedPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
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
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBorder(new LineBorder(new Color(128, 128, 128), 1, true));
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(MainFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif")));
		lblNewLabel.setBounds(10, 10, 88, 97);
		contentPane.add(lblNewLabel);
		
		lblUsername = new JLabel("Username");
		lblUsername.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblUsername.setBounds(129, 20, 100, 15);
		contentPane.add(lblUsername);
		
		txtrAboutDescriptions = new JTextArea();
		txtrAboutDescriptions.setEditable(false);
		txtrAboutDescriptions.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		txtrAboutDescriptions.setText("about descriptions");
		txtrAboutDescriptions.setBounds(127, 52, 140, 49);
		contentPane.add(txtrAboutDescriptions);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 117, 274, 525);
		contentPane.add(tabbedPane);
		
		panel = new JPanel();
		tabbedPane.addTab("好友", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));
		
		tree = new JTree();
		scrollPane = new JScrollPane(tree);
		panel.add(scrollPane, BorderLayout.CENTER);
		
		panel_1 = new JPanel();
		tabbedPane.addTab("群组", null, panel_1, null);
		
		panel_2 = new JPanel();
		tabbedPane.addTab("最近联系人", null, panel_2, null);
	}
}
