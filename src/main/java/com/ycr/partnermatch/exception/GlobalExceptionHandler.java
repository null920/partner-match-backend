package com.ycr.partnermatch.exception;

import com.ycr.partnermatch.common.BaseResponse;
import com.ycr.partnermatch.common.ErrorCode;
import com.ycr.partnermatch.utils.ReturnResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类
 *
 * @author null&&
 * @version 1.0
 * @date 2024/4/13 21:26
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	@ExceptionHandler(BusinessException.class)
	public <T> BaseResponse<T> businessExceptionHandler(BusinessException e) {
		log.error("BusinessException" + e.getMessage(), e);
		return ReturnResultUtils.error(ErrorCode.SYSTEM_ERROR, e.getMessage(), e.getDesc());
	}

	@ExceptionHandler(RuntimeException.class)
	public <T> BaseResponse<T> businessExceptionHandler(RuntimeException e) {
		log.error("RuntimeException", e);
		return ReturnResultUtils.error(ErrorCode.SYSTEM_ERROR, e.getMessage(), "");
	}
}
