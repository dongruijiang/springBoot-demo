package com.sb.demo.common.exception;

/**
 * 工具类 ---通用异常 
 * 工具类异常
 * 
 * @date 2020年3月26日 下午11:26:58
 * @author jdr
 */
public class UtilException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UtilException(Throwable e) {
		super(e.getMessage(), e);
	}

	public UtilException(String message) {
		super(message);
	}

	public UtilException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
