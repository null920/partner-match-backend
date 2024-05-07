package com.ycr.partnermatch.common;

/**
 * 错误码
 *
 * @author null&&
 * @version 1.0
 * @date 2024/4/13 19:27
 */
public enum ErrorCode {

    SUCCESS(20000, "OK", ""),
    PARAMS_ERROR(40000, "请求参数错误", ""),
    NULL_ERROR(40001, "请求数据为空", ""),
    NOT_LOGIN(40100, "未登录", ""),
    NO_AUTH(40101, "无权限", ""),
    FORBIDDEN(40300, "禁止操作", ""),
    SYSTEM_ERROR(50000, "系统内部异常", ""),
    LOCK_TIMEOUT(50400, "抢锁超时", ""),
    DATABASE_ERROR(60000, "数据库异常", "");

    /**
     * 状态码
     */
    private final int code;
    /**
     * 状态码信息
     */
    private final String message;
    /**
     * 状态码描述
     */
    private final String desc;

    ErrorCode(int code, String message, String desc) {
        this.code = code;
        this.message = message;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDesc() {
        return desc;
    }
}
