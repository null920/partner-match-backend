package com.ycr.partnermatch.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录请求体
 *
 * @author null&&
 * @version 1.0
 * @date 2024/4/11 16:30
 */
@Data
public class UserLoginRequest implements Serializable {
	private static final long serialVersionUID = 428304982759886296L;
	private String userAccount;
	private String userPassword;
}
