package com.madailicai.devops.harbor.model;

/**
 * Created by yangxueying on 2016/11/14.
 */
public class HarborResponse {
	private String code;
	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return String.format("HarborResponse [code=%s, message=%s]", code, message);
	}

}
