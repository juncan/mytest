package com.constant;

public enum GuardTypeEnum {

	/**
	 * 包月守护
	 */
	MONTHLY(0,"包月守护"),

	/**
	 * 包季守护
	 */
	SEASON(1,"包季守护"),

	/**
	 * 永久守护
	 */
	FOREVER(2,"永久守护"),
	;

	private Integer code;
	private String msg;

	GuardTypeEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
