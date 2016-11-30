package com.madailicai.devops.harbor;

/**
 * @author Grissom
 *
 */
public class HarborClientException extends Exception {

	String code;

	String harborMessage;

	/**
	 * 
	 */
	private static final long serialVersionUID = -4418648527977181482L;

	/**
	 * 
	 */
	public HarborClientException() {
	}

	public HarborClientException(String code, String harborMessage) {
		this.code = code;
		this.harborMessage = harborMessage;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getHarborMessage() {
		return harborMessage;
	}

	public void setHarborMessage(String harborMessage) {
		this.harborMessage = harborMessage;
	}

	@Override
	public String toString() {
		return String.format("HarborClientException [code=%s, harborMessage=%s, super=%s]", code, harborMessage,
				super.toString());
	}

}
