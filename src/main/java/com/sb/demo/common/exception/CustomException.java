package com.sb.demo.common.exception;

/**
 * 工具类 ---通用异常 
 * 自定义异常
 * 
 * @date 2020年3月26日 下午11:25:33
 * @author jdr
 */
public class CustomException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private Integer code;

	private String message;

	public CustomException(String message) {
		this.message = message;
	}

	public CustomException(String message, Integer code) {
		this.message = message;
		this.code = code;
	}

	public CustomException(String message, Throwable e) {
		super(message, e);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public Integer getCode() {
		return code;
	}
}
