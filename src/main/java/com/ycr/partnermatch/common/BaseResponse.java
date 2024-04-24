package com.ycr.partnermatch.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回类
 *
 * @author null&&
 * @version 1.0
 * @date 2024/4/13 18:54
 */
@Data
@AllArgsConstructor
public class BaseResponse<T> implements Serializable {
	private static final long serialVersionUID = 4155443873547533001L;
	private int code;
	private T data;
	private String message;
	private String desc;

	public BaseResponse(int code, T data, String message) {
		this(code, data, message, "");
	}

	public BaseResponse(int code, T data) {
		this(code, data, "", "");
	}

	public BaseResponse(int code, String message, String desc) {
		this(code, null, message, desc);
	}

	public BaseResponse(ErrorCode errorCode) {
		this(errorCode.getCode(), null, errorCode.getMessage(), errorCode.getDesc());
	}
}
