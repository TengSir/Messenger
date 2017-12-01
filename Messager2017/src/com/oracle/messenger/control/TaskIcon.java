package com.oracle.messenger.control;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import com.oracle.messenger.view.LoginFrame;

public class TaskIcon {
	//delevep  user guide 
	public static void main(String[] args) {
		//windows 任务栏
		SystemTray  sysTray= SystemTray.getSystemTray();//可以对操作系统任务栏操作的java对象
		
		//定义一个弹出菜单对象
		final PopupMenu  menu=new PopupMenu();
		
		MenuItem  open1=new MenuItem("显式");
		MenuItem  open2=new MenuItem("Yincang");
		MenuItem  open3=new MenuItem("退出");
		MenuItem  open4=new MenuItem("设置");
		MenuItem  open5=new MenuItem("在线");
		
		menu.add(open1);
		menu.addSeparator();
		menu.add(open2);
		menu.addSeparator();
		menu.add(open3);
		menu.addSeparator();
		menu.add(open4);
		menu.addSeparator();
		menu.add(open5);
		
		final TrayIcon  icon=new TrayIcon(Toolkit.getDefaultToolkit().createImage("resources/images/logo.png"),"xx在线中",menu);
		icon.setImageAutoSize(true);
		
		icon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(e.getButton());
				if(e.getButton()==1&&e.getClickCount()==2)
				{
					//左键双击
					LoginFrame  f=new LoginFrame();
					f.setVisible(true);
				}else if(e.getButton()==3&&e.getClickCount()==1) {
					menu.show(null, 50, 50);
				}
			}
		});
		
		Timer  t=new Timer();
		try {
			sysTray.add(icon);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		t.schedule(new TimerTask() {
			int  num=1;
			public void run() {
				icon.setImage(Toolkit.getDefaultToolkit().createImage("resources/images/logo"+(num%2==0?"":"-2")+".png"));
				num++;
			}
		},0,500);

	}

}
