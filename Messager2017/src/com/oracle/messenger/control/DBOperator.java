package com.oracle.messenger.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.oracle.messenger.model.User;
//本项目数据库操作功能比较少，单一，基本都是对用户的操作，所以，我们就设计一个类
//来提供所有的对于User对象的数据库操作业务
//由于该类是提供业务方法类，所以从功能性来说，它属于一个业务bean(我们里面的方法最好设计成静态的)
public class DBOperator {
	//五要素====  修饰符，返回值，方法名，参数，方法体
	public static boolean   register(User user) {
		File  data=new File("databases/"+user.getUsername()+".qq");
		if(data.exists())return false;
		
		return updateProfile(user);
	}
	
	public static boolean  updateProfile(User user) {
		try {
			ObjectOutputStream  out=new ObjectOutputStream(new FileOutputStream("databases/"+user.getUsername()+".qq"));
			out.writeObject(user);
			out.flush();
			out.close();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 这是登陆的方法，根据用户传入的用户名和密码判断该用户是否存在，存在返回该User对象，不存在返回null
	 * @param username
	 * @param password
	 * @return
	 */
	public static  User  login(String username,String password) {
		File  data=new File("databases/"+username+".qq");
		if(!data.exists()) {
			return null;
		}else
		{
			
			try {
				ObjectInputStream  in=new ObjectInputStream(new FileInputStream("databases/"+username+".qq"));
				User dbUser=(User)in.readObject();
				if(password.equals(dbUser.getPassword()))
				{
					return dbUser;
				}else
				{
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
	}
	
	//public searchFriendsByCondition();
	
	//public addFriend();
	

	public static void main(String[] args) {
		
		/*try {
			ObjectInputStream  in=new ObjectInputStream(new FileInputStream("databases/111.qq"));
			System.out.println(in.readObject());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		//准备几条数据存储到数据库里面当做测试数据
		User  user=new User("111","111","男",18,"老王","只要锄头轮的好，没有挖不到的墙角","resources/images/logo.jpg");
		User  user1=new User("222","222","女",28,"老老王","只要锄头轮的好，有挖不到的墙角","resources/images/logo.jpg");
		User  user2=new User("333","333","男",38,"姥姥老王","只要锄头轮的好，挖不到的墙角","resources/images/logo.jpg");
		User  user3=new User("444","444","女",48,"老老老老王","只要锄头轮的好，的墙角","resources/images/logo.jpg");
		User  user4=new User("123","123","男",18,"小王","只要锄头轮的好，的墙角啊","resources/images/logo.jpg");
		User  user5=new User("234","234","男",28,"小王吧","只要锄头轮的好，的墙角玩儿","resources/images/logo.jpg");
		User  user6=new User("345","345","女",58,"你是小王吧","只要锄头轮的好，的墙角是否放AA大","resources/images/logo.jpg");
		
		Map<String,Set<User>>  myGroups=new HashMap<>();
		
		Set<User>  qun1Friends=new HashSet<>();
		
		qun1Friends.add(user1);
		qun1Friends.add(user2);
		
		myGroups.put("吹牛皮群",qun1Friends);
		
		
		
		Set<User>  qun2Friends=new HashSet<>();
		
		qun2Friends.add(user5);
		qun2Friends.add(user6);
		
		myGroups.put("扯淡群",qun2Friends);
		user.setMyGroups(myGroups);
		
		
		//给user用户封装一个好友列表信息
		Map<String,HashSet<User>>  friends=new HashMap<>();
		HashSet<User>  f1s=new HashSet<>();
				f1s.add(user1);
				f1s.add(user2);
				f1s.add(user3);
				
				friends.put("大学室友", f1s);
				
				HashSet<User>  f2s=new HashSet<>();
				f2s.add(user4);
				f2s.add(user5);
				
				friends.put("大学损友", f2s);
				
				HashSet<User>  f3s=new HashSet<>();
				f3s.add(user6);
				
				friends.put("暗恋的对象", f3s);
				
				user.setFriends(friends);
				
				
				
				Map<String,HashSet<User>>  friends1=new HashMap<>();
				HashSet<User>  f1s1=new HashSet<>();
						f1s1.add(user);
						f1s1.add(user3);
						
						friends1.put("好盆友", f1s1);
						user4.setFriends(friends1);
				
				
		try {
			ObjectOutputStream  out=new ObjectOutputStream(new FileOutputStream("databases/"+user.getUsername()+".qq"));
			out.writeObject(user);
			out.flush();
			out.close();
			
			out=new ObjectOutputStream(new FileOutputStream("databases/"+user1.getUsername()+".qq"));
			out.writeObject(user1);
			out.flush();
			out.close();
			
			out=new ObjectOutputStream(new FileOutputStream("databases/"+user2.getUsername()+".qq"));
			out.writeObject(user2);
			out.flush();
			out.close();
			
			out=new ObjectOutputStream(new FileOutputStream("databases/"+user3.getUsername()+".qq"));
			out.writeObject(user3);
			out.flush();
			out.close();
			
			out=new ObjectOutputStream(new FileOutputStream("databases/"+user4.getUsername()+".qq"));
			out.writeObject(user4);
			out.flush();
			out.close();
			out=new ObjectOutputStream(new FileOutputStream("databases/"+user5.getUsername()+".qq"));
			out.writeObject(user5);
			out.flush();
			out.close();
			out=new ObjectOutputStream(new FileOutputStream("databases/"+user6.getUsername()+".qq"));
			out.writeObject(user6);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
