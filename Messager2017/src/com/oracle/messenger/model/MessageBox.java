package com.oracle.messenger.model;

import java.io.Serializable;

/**
 * 这个类是封装消息对象的类型，里面应该定义所有消息应该共有的属性
 * @author TengSir
 *
 */
public class MessageBox  implements Serializable{
	private User  from;
	
	private User to;
	
	private String type;
	
	private String content;
	
	private String time;

	/**
	 * @return the from
	 */
	public User getFrom() {
		return from;
	}

	/**
	 * @return the to
	 */
	public User getTo() {
		return to;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(User from) {
		this.from = from;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(User to) {
		this.to = to;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	public MessageBox(User from, User to, String type, String content, String time) {
		super();
		this.from = from;
		this.to = to;
		this.type = type;
		this.content = content;
		this.time = time;
	}

	public MessageBox() {
		super();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MessageBox [from=" + from + ", to=" + to + ", type=" + type + ", content=" + content + ", time=" + time
				+ "]";
	}
	
	

}
