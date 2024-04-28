package com.ycr.partnermatch.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author null&&
 * @version 1.0
 * @date 2024/4/28 21:53
 */
@Data
public class TeamJoinRequest implements Serializable {
	private static final long serialVersionUID = 9002288695267211966L;

	/**
	 * id
	 */
	private Long teamId;

	/**
	 * 密码
	 */
	private String password;
}

