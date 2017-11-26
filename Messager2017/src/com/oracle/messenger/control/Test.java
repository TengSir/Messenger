package com.oracle.messenger.control;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.oracle.messenger.model.User;

public class Test {

	public static void main(String[] args) {
		//业务bean,业务bean里面都是静态方法
//		User user=DBOperator.login("8888", "8888");
//		System.out.println(user);
		try {
			Socket  c=new Socket("localhost", 8888);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
