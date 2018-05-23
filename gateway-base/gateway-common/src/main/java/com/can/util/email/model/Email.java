package com.can.util.email.model;

/**
 * @description:
 * @author: LCN
 * @date: 2018-05-23 16:08
 */

public class Email<T> {

	/** 接收者的邮箱地址 */
	private String receiverEmail;

	/** 邮件的主题 */
	private String subject;

	/** 模板的名称 */
	private String templeName;

	/** 邮件的内容 */
	private T content;

	/**
	 * T(value) 对应的key值
	 *
	 * 如果上面的T为普通的数据类型，
	 * 需要设置这个值，指明他在模板文件里面显示的key
	 * 如果是JavaBean，这个值不要设置，让其为null
	 */
	private String key;

	public Email() {
	}

	public String getReceiverEmail() {
		return receiverEmail;
	}

	public void setReceiverEmail(String receiverEmail) {
		this.receiverEmail = receiverEmail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTempleName() {
		return templeName;
	}

	public void setTempleName(String templeName) {
		this.templeName = templeName;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
