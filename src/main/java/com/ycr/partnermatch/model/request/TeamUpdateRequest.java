package com.ycr.partnermatch.model.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 修改队伍请求参数
 *
 * @author null&&
 * @version 1.0
 * @date 2024/4/28 19:55
 */
@Data
public class TeamUpdateRequest implements Serializable {
	private static final long serialVersionUID = -6128087400842377528L;

	/**
	 * id
	 */
	private Long id;

	/**
	 * 队伍名称
	 */
	private String teamName;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 过期时间
	 */
	private Date expireTime;

	/**
	 * 队伍状态 0-公开，1-私有，2-加密
	 */
	private Integer status;

	/**
	 * 密码
	 */
	private String password;
}
