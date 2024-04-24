package com.ycr.partnermatch.exception;

import com.ycr.partnermatch.common.ErrorCode;

/**
 * 自定义业务异常类
 *
 * @author null&&
 * @version 1.0
 * @date 2024/4/13 21:03
 */
public class BusinessException extends RuntimeException {
	private final int code;
	private final String desc;

	public BusinessException(String message, int code, String desc) {
		super(message);
		this.code = code;
		this.desc = desc;
	}

	public BusinessException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.code = errorCode.getCode();
		this.desc = errorCode.getDesc();
	}

	public BusinessException(ErrorCode errorCode, String desc) {
		super(errorCode.getMessage());
		this.code = errorCode.getCode();
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
