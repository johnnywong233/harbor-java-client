package com.madailicai.devops.harbor.model;

/**
 * Created by yangxueying on 2016/11/14.
 */
public class Response {
	private String code;
	private Object messages;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getMessages() {
		return messages;
	}

	public void setMessages(Object messages) {
		this.messages = messages;
	}

	@Override
	public String toString() {
		return "ResponseDto{" + "code='" + code + '\'' + ", messages=" + messages + '}';
	}
}
