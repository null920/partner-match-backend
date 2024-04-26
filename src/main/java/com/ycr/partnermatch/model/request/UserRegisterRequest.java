package com.ycr.partnermatch.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求体
 *
 * @author null&&
 * @version 1.0
 * @date 2024/4/11 11:34
 */
@Data
public class UserRegisterRequest implements Serializable {
	private static final long serialVersionUID = 5387837336884201601L;
	private String userAccount;
	private String userPassword;
	private String checkPassword;
}
