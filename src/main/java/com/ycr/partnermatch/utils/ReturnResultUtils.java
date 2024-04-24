package com.ycr.partnermatch.utils;

import com.ycr.partnermatch.common.BaseResponse;
import com.ycr.partnermatch.common.ErrorCode;

/**
 * 返回结果工具类
 *
 * @author null&&
 * @version 1.0
 * @date 2024/4/13 19:11
 */

public class ReturnResultUtils {
	/**
	 * 成功
	 *
	 * @param data 数据
	 * @param <T>  泛型
	 * @return 响应
	 */
	public static <T> BaseResponse<T> success(T data) {
		return new BaseResponse<>(20000, data, "ok");
	}

	/**
	 * 失败
	 *
	 * @param errorCode 错误码
	 * @param <T>       泛型
	 * @return 响应
	 */
	public static <T> BaseResponse<T> error(ErrorCode errorCode) {
		return new BaseResponse<>(errorCode);
	}

	public static <T> BaseResponse<T> error(int code, String message, String desc) {
		return new BaseResponse<>(code, message, desc);
	}

	public static <T> BaseResponse<T> error(ErrorCode errorCode, String message, String desc) {
		return new BaseResponse<>(errorCode.getCode(), message, desc);
	}

	public static <T> BaseResponse<T> error(ErrorCode errorCode, String desc) {
		return new BaseResponse<>(errorCode.getCode(), errorCode.getMessage(), desc);
	}
}
