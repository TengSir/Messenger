package com.oracle.messenger.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class User implements Serializable{
	private String username;
	private String password;
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	private String sex;
	private int age;
	private String nickname;
	private String signatrue;
	private String imagePath;
	private Map<String,Set<User>>  myGroups=new  HashMap<>();//存储用户有哪些群的属性
	
	
	/**
	 * @return the myGroups
	 */
	public Map<String, Set<User>> getMyGroups() {
		return myGroups;
	}
	/**
	 * @param myGroups the myGroups to set
	 */
	public void setMyGroups(Map<String, Set<User>> myGroups) {
		this.myGroups = myGroups;
	}
	private Map<String,HashSet<User>>  friends=new HashMap<>();//has-a
	
	/**
	 * @return the friends
	 */
	public Map<String, HashSet<User>> getFriends() {
		return friends;
	}
	/**
	 * @param friends the friends to set
	 */
	public void setFriends(Map<String, HashSet<User>> friends) {
		this.friends = friends;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * @return the signatrue
	 */
	public String getSignatrue() {
		return signatrue;
	}
	/**
	 * @return the imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * @param signatrue the signatrue to set
	 */
	public void setSignatrue(String signatrue) {
		this.signatrue = signatrue;
	}
	/**
	 * @param imagePath the imagePath to set
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public User(String username, String password, String sex, int age, String nickname, String signatrue,
			String imagePath) {
		super();
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.age = age;
		this.nickname = nickname;
		this.signatrue = signatrue;
		this.imagePath = imagePath;
	}
	public User() {
		super();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", sex=" + sex + ", age=" + age + ", nickname="
				+ nickname + ", signatrue=" + signatrue + ", imagePath=" + imagePath + "]";
	}
	

}
