package com.test.constants;

/**
 * @Classname FindMoreType
 * @Description 发现更多枚举
 * @Date 2020/12/7 10:11
 * @Created by wujc
 */
public enum FindMoreTypeEnum {
	SUPER_GOODS(1,"超值好货"),
	PK_KING(2, "PK之王"),
	NEW_ANCHOR(3,"新秀主播");

	private Integer code;
	private String msg;

	FindMoreTypeEnum(Integer code, String msg) {
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
